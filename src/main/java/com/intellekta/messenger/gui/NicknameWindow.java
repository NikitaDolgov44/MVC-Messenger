package com.intellekta.messenger.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NicknameWindow {
    private final Stage stage;

    // Constructor that takes a Stage as a parameter
    public NicknameWindow(Stage stage) {
        this.stage = stage;
        initializeUI();
    }

    // Set up the UI components
    private void initializeUI() {
        VBox nicknameContainer = new VBox(20);

        Label titleLabel = new Label("Введите ваш никнейм");
        TextField nicknameField = new TextField();
        nicknameField.setPromptText("Никнейм");

        Button saveButton = new Button("Сохранить");

        nicknameContainer.getChildren().addAll(titleLabel, nicknameField, saveButton);

        Scene scene = new Scene(nicknameContainer, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/static/css/nickname.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Выбор никнейма");
    }

    // Method to display the window
    public void show() {
        stage.show();
    }
}

