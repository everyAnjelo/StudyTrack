package com.studytrack.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void showDashboard() {
        loadScene("/fxml/dashboard.fxml");
    }

    public static void showCourses() {
        loadScene("/fxml/course.fxml");
    }

    public static void showTasks() {
        loadScene("/fxml/task.fxml");
    }

    public static void showStudyTimer() {
        loadScene("/fxml/studyTimer.fxml");
    }


    private static void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = new Scene(root, 1000, 650);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Could not load " + fxmlPath, e);
        }
    }
}