package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import java.io.File;

public class Main extends Application {
    //create table and list of values
    private TableView<Student> table = new TableView<Student>();
    private final ObservableList<Student> data = FXCollections.observableArrayList(
            new Student("123", "Assignment 1", "Sr. Uceda","Completed",100,null),
            new Student("1234", "Assignment 2", "Sr. Uceda","Missing",0,null)
    );


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Assignment Page");
        GridPane grid = new GridPane();

        grid.setVgap(10.0);
        grid.setPadding(new Insets(30, 20, 30, 25));

        primaryStage.setWidth(800);
        primaryStage.setHeight(500);


        //Top Heading Label
        Label heading = new Label("NOW VIEWING: Home Page");
        grid.add(heading,0,0,2,1);

        table.setEditable(true);

        TableColumn schoolCol = new TableColumn("School: Bronx Science");

        //making table columns and setting a value to the column
        TableColumn assignIDCol = new TableColumn("Assignment ID");
        assignIDCol.setPrefWidth(150);
        assignIDCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("assignId"));

        TableColumn assignNameCol = new TableColumn("Assignment Name");
        assignNameCol.setPrefWidth(150);
        assignNameCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("assignName"));

        TableColumn instructorCol = new TableColumn("Instructor");
        instructorCol.setPrefWidth(150);
        instructorCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("instructor"));

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setPrefWidth(100);
        statusCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("status"));

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setPrefWidth(100);
        gradeCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("grade"));

        TableColumn<Student, String> workCol = new TableColumn<Student, String>("Work");
        //TableColumn workCol = new TableColumn("Work");
        workCol.setPrefWidth(100);
        workCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("Work"));

        //organizing columns
        table.getColumns().addAll(schoolCol);
        schoolCol.getColumns().addAll(assignIDCol,assignNameCol,instructorCol,statusCol,gradeCol,workCol);

        //connecting the column to value in list
        assignIDCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("assignID")
        );
        assignNameCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("assignName")
        );
        instructorCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("instructor")
        );
        statusCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("status")
        );
        gradeCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("grade")
        );
        workCol.setCellValueFactory(
                new PropertyValueFactory<Student,String>("work")
        );

        //Adding file
        FileChooser fileChooser = new FileChooser();

        Button addFile = new Button("Add file");
        addFile.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                    new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                    new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                    new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                    new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
            );

            if(selectedFile.getAbsolutePath()!=null) {
                String filePath = selectedFile.getAbsolutePath();
                System.out.println(filePath);
            }
        });

        grid.add(addFile,0,3);

        //double-clicking function
        Label idLabel = new Label();
        Label nameLabel = new Label();
        Label instructorLabel = new Label();
        Label statusLabel = new Label();
        Label gradeLabel = new Label();
        Label workLabel = new Label();
        Label closed = new Label();
        closed.setText("not closed");
        table.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Student rowData = row.getItem();
                    if (!(idLabel.getText().indexOf("Assignment")==0)){
                        Stage dataStage = new Stage();
                        dataStage.setTitle("Student info");
                        GridPane dataGrid = new GridPane();

                        dataGrid.setVgap(20.0);
                        dataGrid.setHgap(10.0);
                        dataGrid.setPadding(new Insets(30, 30, 30, 30));

                        dataStage.setWidth(400);
                        dataStage.setHeight(400);
                        Scene dataScene = new Scene(dataGrid, 350, 350);
                        dataStage.setScene(dataScene);

                        dataStage.show();

                        System.out.println("Double click on: " + rowData.getAssignID());
                        idLabel.setText("Assignment ID: " + rowData.getAssignID());
                        nameLabel.setText("Assignment Name: " + rowData.getAssignName());
                        instructorLabel.setText("Instructor Name: " + rowData.getInstructor());
                        statusLabel.setText("Assignment Status: " + rowData.getStatus());
                        gradeLabel.setText("Assignment Grade: " + rowData.getGrade());
                        workLabel.setText("Work submitted: " + rowData.getWork());

                        dataGrid.add(idLabel, 0, 0);
                        dataGrid.add(nameLabel, 0, 1);
                        dataGrid.add(instructorLabel, 0, 2);
                        dataGrid.add(statusLabel, 1, 0);
                        dataGrid.add(gradeLabel, 1, 1);
                    }
                    else{
                        System.out.println("Double click on: " + rowData.getAssignID());
                        idLabel.setText("Assignment ID: " + rowData.getAssignID());
                        nameLabel.setText("Assignment Name: " + rowData.getAssignName());
                        instructorLabel.setText("Instructor Name: " + rowData.getInstructor());
                        statusLabel.setText("Assignment Status: " + rowData.getStatus());
                        gradeLabel.setText("Assignment Grade: " + rowData.getGrade());
                        workLabel.setText("Work submitted: " + rowData.getWork());
                    }
                }
            });
            return row ;
        });

        table.setItems(data);
        grid.add(table,0,1,3,1);

        //Images
        /*Image assignmentImage = new Image("https://i.pinimg.com/originals/56/b4/9f/56b49f8fe357deecf54ad7805209d79e.png",
                170,140, true,true);
        grid.add(new ImageView(assignmentImage), 0, 4,2,1);

        Image assignmentImage2 = new Image("https://www.clker.com/cliparts/Y/l/P/U/K/D/talking-bubble-no-shadow-md.png",
                200,100, false,true);
        grid.add(new ImageView(assignmentImage2), 2, 3,3,2);*/

        Scene scene = new Scene(grid, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}



