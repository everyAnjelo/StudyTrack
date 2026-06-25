module org.example.studytrack {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.studytrack to javafx.fxml;
    opens com.studytrack.controller to javafx.fxml;
    opens com.studytrack.model to javafx.base;

    exports com.studytrack;
    exports com.studytrack.app;
    exports com.studytrack.controller;
    exports com.studytrack.model;
}