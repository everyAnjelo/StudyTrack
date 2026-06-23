package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CourseController {
    @FXML private TextField courseCodeTextField, courseNameTextField, lecturerTextField, semesterTextField;
    @FXML private Button dashboardBtn, coursesBtn, tasksBtn, studyTimerBtn, gradesBtn;
    public void initialize(){

        coursesBtn.setOnAction(event -> SceneManager.showCourses());
        tasksBtn.setOnAction(event -> SceneManager.showTasks());
        dashboardBtn.setOnAction(event -> SceneManager.showDashboard());
    }


}
