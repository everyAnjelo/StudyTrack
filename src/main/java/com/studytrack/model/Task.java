package com.studytrack.model;

import java.time.LocalDate;
import java.util.Locale;

public class Task {

    String taskTitle;
    //String description;
    String courseCode;
    LocalDate dueDate;
    Priority priority;
    Status status;

    public Task(String taskTitle , String courseCode, LocalDate dueDate, Priority priority, Status status){
        this.taskTitle = taskTitle;
        //this.description = description;
        this.courseCode  = courseCode;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;

    }

    public String getTaskTitle(){return this.taskTitle;}
    // public String getDescription(){return this.description;}
    public String getCourseCode(){return this.courseCode;}
    public LocalDate getDueDate(){return this.dueDate;}
    public Priority getPriority(){return this.priority;}
    public Status getStatus(){return this.status;}

    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }
    public void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
    public void setPriority(Priority priority){
        this.priority = priority;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public String toString() {
        return taskTitle;
    }
}