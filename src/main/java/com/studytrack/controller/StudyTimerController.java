package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import com.studytrack.model.AppData;
import com.studytrack.model.Task;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class StudyTimerController {
    @FXML
    private Button coursesBtn, tasksBtn, dashboardBtn, studyTimerBtn, gradesBtn, logoutBtn;

    @FXML
    private Label timerLabel, statusLabel, totalTimeLabel, completedSessionsLabel, lastStudiedTaskLabel;

    @FXML
    private ComboBox<Task> taskComboBox;

    @FXML
    private Button startBtn, pauseBtn, resetBtn;

    private int elapsedSeconds = 0;
    private Timeline timer;
    private boolean isRunning = false;

    @FXML
    public void initialize() {
        setNavigation();
        setupComboBox();
        setupButtons();
        updateTimerLabel();

        statusLabel.setText("Status: Ready");
        totalTimeLabel.setText("Total Time Today: 00:00:00");
        completedSessionsLabel.setText("Sessions Completed: 0");
        lastStudiedTaskLabel.setText("Last Studied Task: None");
    }

    public void setNavigation() {
        coursesBtn.setOnAction(event -> SceneManager.showCourses());
        tasksBtn.setOnAction(event -> SceneManager.showTasks());
        dashboardBtn.setOnAction(event -> SceneManager.showDashboard());
        studyTimerBtn.setOnAction(event -> SceneManager.showStudyTimer());
    }

    public void setupComboBox() {
        taskComboBox.setItems(AppData.tasks);
    }

    private void setupButtons() {
        startBtn.setOnAction(event -> startTimer());
        pauseBtn.setOnAction(event -> pauseTimer());
        resetBtn.setOnAction(event -> resetTimer());
    }

    private void startTimer() {
        Task selectedTask = taskComboBox.getValue();

        if (selectedTask == null) {
            showError("Please select a task first.");
            return;
        }

        if (isRunning) {
            return;
        }

        isRunning = true;
        statusLabel.setText("Studying");

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            elapsedSeconds++;
            updateTimerLabel();
        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void pauseTimer() {
        if (!isRunning) {
            return;
        }

        if (timer != null) {
            timer.pause();
        }

        isRunning = false;
        statusLabel.setText("Status: Paused");
    }

    private void resetTimer() {
        if (timer != null) {
            timer.stop();
        }

        elapsedSeconds = 0;
        isRunning = false;

        updateTimerLabel();
        statusLabel.setText("Status: Ready");
    }

    private void updateTimerLabel() {
        int hours = elapsedSeconds / 3600;
        int minutes = (elapsedSeconds % 3600) / 60;
        int seconds = elapsedSeconds % 60;

        timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}