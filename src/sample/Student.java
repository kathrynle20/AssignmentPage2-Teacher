package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

public class Student {
    private final SimpleStringProperty assignID;
    private final SimpleStringProperty assignName;
    private final SimpleStringProperty instructor;
    private final SimpleStringProperty status;
    private final SimpleIntegerProperty grade;
    private final SimpleStringProperty work;
    //private File workfile;


    Student(String aID, String aName, String iName, String stat, int g, String workFile) {
        super();
        this.assignID = new SimpleStringProperty(aID);
        this.assignName = new SimpleStringProperty(aName);
        this.instructor = new SimpleStringProperty(iName);
        this.status = new SimpleStringProperty(stat);
        this.grade = new SimpleIntegerProperty(g);
        /*work = new SimpleStringProperty();
        work.set(workFile.getName());
        this.workfile = workFile;*/
        this.work = new SimpleStringProperty(workFile);
    }

    public String getAssignID() {
        return assignID.get();
    }
    public void setAssignID(String aID) {
        assignID.set(aID);
    }

    public String getAssignName() {
        return assignName.get();
    }
    public void setAssignName(String aName) {
        assignName.set(aName);
    }

    public String getInstructor() {
        return instructor.get();
    }
    public void setInstructor(String iName) {
        instructor.set(iName);
    }

    public String getStatus(){
        return status.get();
    }
    public void setStatus(String stat){
        status.set(stat);
    }

    public int getGrade(){
        return grade.get();
    }
    public void setGrade(int g){
        grade.set(g);
    }

    public String getWork() {
        return work.get();
    }
    public void setWork(String workFile) {
        work.set(workFile);
    }
    /*public SimpleStringProperty workProperty(){
        return work;
    }
    public String getPathString(){
        return workfile.getAbsolutePath();
    }*/
}

