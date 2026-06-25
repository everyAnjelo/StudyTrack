package com.studytrack.model;

public class Course {

    String courseCode;
    String courseName;
    String lecturer;
    String semester;

    public Course(String courseCode, String courseName, String lecturer, String semester){
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.semester = semester;

    }
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    public void setSemester(String semester){
        this.semester = semester;
    }


    public String getCourseName(){
        return courseName;
    }
    public String getSemester(){
        return semester;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getLecturer(){
        return lecturer;
    }


}