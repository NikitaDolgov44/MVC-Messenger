package com.intellekta.messenger.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginWindow {

    public void start(Stage loginStage) {
        loginStage.setTitle("Messenger - Вход");

        // Main container for the login form
        VBox loginContainer = new VBox(20);
        loginContainer.setPadding(new Insets(40));
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.getStyleClass().add("login-container"); // CSS class for styling

        // Title
        Label titleLabel = new Label("Вход");
        titleLabel.getStyleClass().add("title");

        // Username and Password fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        usernameField.getStyleClass().add("input-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.getStyleClass().add("input-field");

        // Login button
        Button loginButton = new Button("Войти");
        loginButton.getStyleClass().add("primary-button");

        // Error message label, hidden initially
        Label errorLabel = new Label();
        errorLabel.setManaged(false); // Not visible initially
        errorLabel.setVisible(false);
        errorLabel.getStyleClass().add("error-message");

        // Event handler for login button (example logic)
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Пожалуйста, заполните все поля.");
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
            } else {
                errorLabel.setVisible(false);
                errorLabel.setManaged(false);
                // Implement actual login logic here
            }
        });

        // Adding elements to the main container
        loginContainer.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, errorLabel);

        // Set the scene
        Scene scene = new Scene(loginContainer, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());
        loginStage.setScene(scene);
        loginStage.show();
    }
}

