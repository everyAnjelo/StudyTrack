module org.example.studytrack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.studytrack to javafx.fxml;
    exports com.studytrack;
}