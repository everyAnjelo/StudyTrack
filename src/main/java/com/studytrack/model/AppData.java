package com.studytrack.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class AppData {
    public static ObservableList<Course> courses = FXCollections.observableArrayList();
    public static ObservableList<Task> tasks = FXCollections.observableArrayList();
    public static ObservableList<StudySession> studySessions = FXCollections.observableArrayList();
}
