package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {
    @FXML
    private Button dashboardBtn, coursesBtn, tasksBtn, studyTimerBtn, gradesBtn;
    public void initialize(){
        setNavigation();

    }

    public void setNavigation(){
        coursesBtn.setOnAction(event -> SceneManager.showCourses());
        tasksBtn.setOnAction(event -> SceneManager.showTasks());
        dashboardBtn.setOnAction(event -> SceneManager.showDashboard());
    }

}
