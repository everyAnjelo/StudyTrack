package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import com.studytrack.model.AppData;
import com.studytrack.model.Course;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseController {
    @FXML private TextField courseCodeTextField, courseNameTextField, lecturerTextField, semesterTextField;
    @FXML private Button addCourseBtn,updateBtn, deleteBtn, clearBtn;
    @FXML private Button dashboardBtn, coursesBtn, tasksBtn, studyTimerBtn, gradesBtn, logoutBtn;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> courseCodeColumn;
    @FXML private TableColumn<Course, String> courseNameColumn;
    @FXML private TableColumn<Course, String> lecturerColumn;
    @FXML private TableColumn<Course, String> semesterColumn;

    @FXML
    private void initialize() {
        setupNavigation();
        setupTable();
        setupButtons();
        setupSelection();
    }

    private void setupNavigation() {
        coursesBtn.setOnAction(event -> SceneManager.showCourses());
        tasksBtn.setOnAction(event -> SceneManager.showTasks());
        dashboardBtn.setOnAction(event -> SceneManager.showDashboard());
    }

    private void setupTable() {
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        lecturerColumn.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

        courseTable.setItems(AppData.courses);
    }

    private void setupButtons() {
        addCourseBtn.setOnAction(event -> addCourse());
        deleteBtn.setOnAction(event -> deleteCourse());
        updateBtn.setOnAction(event -> updateCourse());
        clearBtn.setOnAction(event -> clearFields());

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    private void addCourse() {
        String courseCode = courseCodeTextField.getText().trim();
        String courseName = courseNameTextField.getText().trim();
        String lecturer = lecturerTextField.getText().trim();
        String semester = semesterTextField.getText().trim();

        if (courseCode.isEmpty() || courseName.isEmpty()) {
            showError("Course code and course name are required.");
            return;
        }

        Course course = new Course(courseCode, courseName, lecturer, semester);
        AppData.courses.add(course);

        clearFields();

    }

    private void clearFields() {
        courseCodeTextField.clear();
        courseNameTextField.clear();
        lecturerTextField.clear();
        semesterTextField.clear();

        courseTable.getSelectionModel().clearSelection();
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void setupSelection(){
        courseTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelection, selectedCourse) -> {
                    if (selectedCourse != null) {
                        courseCodeTextField.setText(selectedCourse.getCourseCode());
                        courseNameTextField.setText(selectedCourse.getCourseName());
                        lecturerTextField.setText(selectedCourse.getLecturer());
                        semesterTextField.setText(selectedCourse.getSemester());

                        updateBtn.setDisable(false);
                        deleteBtn.setDisable(false);
                    }
                }
        );
    }

    private void updateCourse(){
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

        if(selectedCourse == null){
            showError("Please select a course to update");
            return;
        }

        String courseCode = courseCodeTextField.getText().trim();
        String courseName = courseNameTextField.getText().trim();
        String lecturer = lecturerTextField.getText().trim();
        String semester = semesterTextField.getText().trim();

        if(courseCode.isEmpty() || courseName.isEmpty()){
            showError("Course code and course name are required");
            return;
        }

        selectedCourse.setCourseCode(courseCode);
        selectedCourse.setCourseName(courseName);
        selectedCourse.setLecturer(lecturer);
        selectedCourse.setSemester(semester);

        courseTable.refresh();
        clearFields();courseTable.getSelectionModel().clearSelection();
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);


    }
    private void deleteCourse() {
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

        if (selectedCourse == null) {
            showError("Please select a course to delete.");
            return;
        }

        boolean confirmed = showConfirmation("Are you sure you want to delete this course?");

        if (!confirmed) {
            return;
        }

        AppData.courses.remove(selectedCourse);
        clearFields();
    }

    private boolean showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText(message);

        return alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .isPresent();
    }
}
