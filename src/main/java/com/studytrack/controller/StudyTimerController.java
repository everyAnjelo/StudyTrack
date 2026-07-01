package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import com.studytrack.model.AppData;
import com.studytrack.model.StudySession;
import com.studytrack.model.Task;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDate;

public class StudyTimerController {
    @FXML
    private Button coursesBtn, tasksBtn, dashboardBtn, studyTimerBtn, gradesBtn, logoutBtn;

    @FXML
    private Label timerLabel, statusLabel, totalTimeLabel, completedSessionsLabel, lastStudiedTaskLabel;

    @FXML
    private ComboBox<Task> taskComboBox;

    @FXML
    private Button startBtn, pauseBtn, resetBtn, saveSessionBtn;

    private int elapsedSeconds = 0;
    private Timeline timer;
    private boolean isRunning = false;

    @FXML
    public void initialize() {
        setNavigation();
        setupComboBox();
        setupButtons();
        updateTimerLabel();
        updateStudySummary();

    }

    private void updateStudySummary() {
        LocalDate today = LocalDate.now();

        int totalSecondsToday = 0;
        int completedSessionsToday = 0;

        for (StudySession session : AppData.studySessions) {
            if (session.getDate().isEqual(today)) {
                totalSecondsToday += session.getDurationSeconds();
                completedSessionsToday++;
            }
        }

        int hours = totalSecondsToday / 3600;
        int minutes = (totalSecondsToday % 3600) / 60;
        int seconds = totalSecondsToday % 60;

        totalTimeLabel.setText(String.format("Total Time Today: %02d:%02d:%02d", hours, minutes, seconds));
        completedSessionsLabel.setText("Sessions Completed: " + completedSessionsToday);

        if (AppData.studySessions.isEmpty()) {
            lastStudiedTaskLabel.setText("Last Studied Task: None");
        } else {
            StudySession lastSession = AppData.studySessions.get(AppData.studySessions.size() - 1);
            lastStudiedTaskLabel.setText("Last Studied Task: " + lastSession.getTaskTitle());
        }
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
        saveSessionBtn.setOnAction(event -> saveSession());
    }

    private void saveSession() {
        Task selectedTask = taskComboBox.getValue();

        if (selectedTask == null) {
            showError("Please select a task first.");
            return;
        }

        if(elapsedSeconds <= 0){
            showError("not started yet");
            return;
        }
        StudySession studySession = new StudySession(selectedTask.getTaskTitle(),selectedTask.getCourseCode()
                ,elapsedSeconds, LocalDate.now());

        AppData.studySessions.add(studySession);

        lastStudiedTaskLabel.setText(AppData.studySessions.getLast().getTaskTitle());
        updateStudySummary();

        resetTimer();
    }
    public void totalTimeToday(){
        LocalDate today = LocalDate.now();
        int totalSeconds = 0;

        for(StudySession session: AppData.studySessions){
            if (session.getDate().isEqual(today)){
                totalSeconds += session.getDurationSeconds();
            }
        }
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        totalTimeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

    }

    public void completedSessionToday(){
        LocalDate today = LocalDate.now();
        int count = 0;

        for(StudySession session: AppData.studySessions){
            if(session.getDate().isEqual(today)){
                count++;
            }

        }
        completedSessionsLabel.setText(String.valueOf(count));
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