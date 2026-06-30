package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import com.studytrack.model.AppData;
import com.studytrack.model.Priority;
import com.studytrack.model.Status;
import com.studytrack.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class DashboardController {
    @FXML
    private Button dashboardBtn, coursesBtn, tasksBtn, studyTimerBtn, gradesBtn;
    @FXML
    private Label totalCourseLabel, pendingTasksLabel, completedTasksLabel, overdueTasksLabel,
            upcomingDeadlineLabel,recentTasksLabel;
    public void initialize(){
        setNavigation();
        updateDashboard();

    }

    private void updateDashboard() {
        totalCourseLabel.setText(String.valueOf(AppData.courses.size()));
        pendingTasksLabel.setText(String.valueOf(statusCounter(Status.PENDING) +
                statusCounter(Status.IN_PROGRESS)));
        completedTasksLabel.setText(String.valueOf(statusCounter(Status.COMPLETED)));
        overdueTasksLabel.setText(String.valueOf(overdueTaskCounter()));
        upcomingDeadlineLabel.setText(upcomingDeadline());
        recentTasksLabel.setText(recentAddedTask());
    }

    public void setNavigation() {
        coursesBtn.setOnAction(event -> SceneManager.showCourses());
        tasksBtn.setOnAction(event -> SceneManager.showTasks());
        dashboardBtn.setOnAction(event -> SceneManager.showDashboard());
        studyTimerBtn.setOnAction(event -> SceneManager.showStudyTimer());
    }
    private int priorityCounter(Priority priority){
        int total = 0;

        for(Task task: AppData.tasks){
            if(task.getPriority() == priority){
                total++;
            }

        }
        return total;
    }
    private int statusCounter(Status status){
        int total = 0;

        for(Task task: AppData.tasks){
            if(task.getStatus() == status){
                total++;
            }

        }
        return total;
    }
    private int overdueTaskCounter(){
        int total = 0;

        for(Task task: AppData.tasks){
            if(task.getDueDate().isBefore(LocalDate.now()) && task.getStatus() != Status.COMPLETED){
                total++;
            }

        }
        return total;
    }
    private String upcomingDeadline() {
        LocalDate today = LocalDate.now();

        Task nearestTask = null;

        for (Task task : AppData.tasks) {
            boolean notCompleted = task.getStatus() != Status.COMPLETED;
            boolean todayOrFuture = task.getDueDate().isEqual(today) || task.getDueDate().isAfter(today);

            if (notCompleted && todayOrFuture) {
                if (nearestTask == null) {
                    nearestTask = task;
                } else if (task.getDueDate().isBefore(nearestTask.getDueDate())) {
                    nearestTask = task;
                }
            }
        }

        if (nearestTask == null) {
            return "No upcoming deadline";
        }

        return nearestTask.getTaskTitle();
    }
    private String recentAddedTask() {
        if (AppData.tasks.isEmpty()) {
            return "No tasks added yet";
        }

        Task recentTask = AppData.tasks.getLast();

        return recentTask.getTaskTitle();
    }


}