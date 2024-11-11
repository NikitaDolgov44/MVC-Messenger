package com.intellekta.messenger.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Messenger");

        // Отображаем экран регистрации при запуске
        showRegistrationScreen();
    }

    // Метод для отображения экрана регистрации
    // Метод для отображения экрана регистрации
    private void showRegistrationScreen() {
        VBox registrationContainer = new VBox(20);
        registrationContainer.getStylesheets().add(getClass().getResource("/static/css/registration.css").toExternalForm());

        registrationContainer.setAlignment(Pos.CENTER);  // Центрируем содержимое
        registrationContainer.setPadding(new Insets(40));

        Label titleLabel = new Label("Регистрация");
        titleLabel.getStyleClass().add("title");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        usernameField.getStyleClass().add("input-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.getStyleClass().add("input-field");

        Button registerButton = new Button("Зарегистрироваться");
        registerButton.getStyleClass().add("primary-button");

        Label loginLink = new Label("Вход");
        loginLink.getStyleClass().add("link");
        loginLink.setOnMouseClicked(event -> showLoginScreen());

        registerButton.setOnAction(event -> {
            // Логика регистрации
            boolean registrationSuccessful = true;  // заменить на реальную проверку
            if (registrationSuccessful) {
                showLoginScreen();
            }
        });

        registrationContainer.getChildren().addAll(titleLabel, usernameField, passwordField, registerButton, loginLink);
        primaryStage.setScene(new Scene(registrationContainer, 400, 600));
        primaryStage.show();
    }

    // Метод для отображения экрана входа
    private void showLoginScreen() {
        VBox loginContainer = new VBox(20);
        loginContainer.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());

        loginContainer.setAlignment(Pos.CENTER);  // Центрируем содержимое
        loginContainer.setPadding(new Insets(40));

        Label titleLabel = new Label("Вход");
        titleLabel.getStyleClass().add("title");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        usernameField.getStyleClass().add("input-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.getStyleClass().add("input-field");

        Button loginButton = new Button("Войти");
        loginButton.getStyleClass().add("primary-button");

        loginButton.setOnAction(event -> {
            // Логика входа
            boolean loginSuccessful = true;  // заменить на реальную проверку
            if (loginSuccessful) {
                // Переход к следующему окну или главному экрану
            }
        });

        loginContainer.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton);
        primaryStage.setScene(new Scene(loginContainer, 400, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


