package com.studytrack.controller;

import com.studytrack.app.SceneManager;
import com.studytrack.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class TaskController {
    @FXML
    private Button dashboardBtn, coursesBtn, tasksBtn, studyTimerBtn, gradesBtn;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private ComboBox<Priority> priorityComboBox;
    @FXML
    private ComboBox<Status> statusComboBox;
    @FXML
    private TextField titleTextField;
    @FXML
    private DatePicker dueDateDatePicker;
    @FXML
    private Button addBtn, doneBtn, deleteBtn, clearBtn;
    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, String> courseColumn;
    @FXML private TableColumn<Task, String> titleColumn;
    @FXML private TableColumn<Task, Priority> priorityColumn;
    @FXML private TableColumn<Task, Status> statusColumn;
    @FXML private TableColumn<Task, LocalDate> dueDateColumn;


    public void initialize(){
        setupNavigation();
        setupComboBoxes();
        setupTable();
        setupButtons();

    }

    private void setupButtons() {

        addBtn.setOnAction(event -> addTask());
        clearBtn.setOnAction(event -> clearField());
    }

    private void clearField() {
        titleTextField.clear();
        courseComboBox.getSelectionModel().clearSelection();
        dueDateDatePicker.setValue(null);

        priorityComboBox.setValue(Priority.MEDIUM);
        statusComboBox.setValue(Status.PENDING);

        taskTableView.getSelectionModel().clearSelection();

        doneBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    private void addTask() {
        String title = titleTextField.getText().trim();
        String courseCode = courseComboBox.getValue();
        LocalDate dueDate = dueDateDatePicker.getValue();
        Priority priority = priorityComboBox.getValue();
        Status status = statusComboBox.getValue();

        if (title.isEmpty()) {
            showError("Task title is required.");
            return;
        }

        if (courseCode == null) {
            showError("Please select a course.");
            return;
        }

        if (dueDate == null) {
            showError("Please select a due date.");
            return;
        }

        if (priority == null) {
            showError("Please select a priority.");
            return;
        }

        if (status == null) {
            showError("Please select a status.");
            return;
        }

        Task task = new Task(title, courseCode, dueDate, priority, status);
        AppData.tasks.add(task);

        clearField();
    }

    private void setupTable() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        taskTableView.setItems(AppData.tasks);
    }

    private void setupComboBoxes() {
        courseComboBox.getItems().clear();

        for (Course course : AppData.courses) {
            courseComboBox.getItems().add(course.getCourseCode());
        }

        priorityComboBox.getItems().setAll(Priority.values());
        statusComboBox.getItems().setAll(Status.values());

        priorityComboBox.setValue(Priority.MEDIUM);
        statusComboBox.setValue(Status.PENDING);
    }

    public void setupNavigation(){
        coursesBtn.setOnAction(event -> SceneManager.showCourses());
        tasksBtn.setOnAction(event -> SceneManager.showTasks());
        dashboardBtn.setOnAction(event -> SceneManager.showDashboard());
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
