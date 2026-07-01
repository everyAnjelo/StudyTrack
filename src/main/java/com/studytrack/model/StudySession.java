package com.studytrack.model;

import java.time.LocalDate;

public class StudySession {

    String taskTitle;
    String courseCode;
    int durationSeconds;
    LocalDate date;

    public StudySession(String taskTitle, String courseCode, int durationSeconds, LocalDate date){
        this.taskTitle = taskTitle;
        this.courseCode = courseCode;
        this.durationSeconds = durationSeconds;
        this.date = date;

    }
    public String getTaskTitle(){
        return taskTitle;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public  int getDurationSeconds(){
        return durationSeconds;
    }
    public LocalDate getDate(){
        return date;
    }

}
