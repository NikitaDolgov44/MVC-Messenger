package com.intellekta.messenger.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginWindow {
    private final Stage stage;

    public LoginWindow(Stage stage) {
        this.stage = stage;
        initializeUI();
    }

    // Set up the login UI
    private void initializeUI() {
        VBox loginContainer = new VBox(20);
        loginContainer.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Вход");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");

        Button loginButton = new Button("Войти");

        loginButton.setOnAction(event -> {
            // Simulate successful login
            boolean loginSuccessful = true;
            if (loginSuccessful) {
                // Pass the current stage to the NicknameWindow
                NicknameWindow nicknameWindow = new NicknameWindow(stage);
                nicknameWindow.show();
            }
        });

        loginContainer.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton);

        Scene scene = new Scene(loginContainer, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/static/css/login.css").toExternalForm());
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}



