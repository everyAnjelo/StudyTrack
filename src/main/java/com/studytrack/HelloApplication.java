package com.studytrack;

import com.studytrack.app.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        SceneManager.showDashboard();
    }

    public static void main(String[] args) {
        launch();
    }
}