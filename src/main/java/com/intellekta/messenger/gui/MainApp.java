package com.intellekta.messenger.gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Messenger - Регистрация");

        // Главный контейнер
        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(40));
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color: #f2f2f2;");

        // Заголовок
        Label titleLabel = new Label("Регистрация / Вход");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Поля для ввода имени пользователя и пароля
        TextField usernameField = new TextField();
        usernameField.setPromptText("Имя пользователя");
        usernameField.setStyle("-fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 4px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Пароль");
        passwordField.setStyle("-fx-padding: 8px; -fx-border-color: #ccc; -fx-border-radius: 4px;");

        // Кнопки для регистрации и входа
        Button registerButton = new Button("Зарегистрироваться");
        Button loginButton = new Button("Войти");

        registerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px;");
        loginButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 8px 16px; -fx-border-radius: 4px;");

        // Контейнер для кнопок
        HBox buttonContainer = new HBox(10, registerButton, loginButton);
        buttonContainer.setAlignment(Pos.CENTER);

        // Сообщение об ошибках
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red; -fx-border-color: red; -fx-padding: 10px;");

        // События кнопок (здесь можно добавить логику обработки регистрации и входа)
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Пожалуйста, заполните все поля.");
            } else {
                messageLabel.setText("");  // Очистка сообщения об ошибке после успешной проверки
                // Реализуйте логику регистрации
            }
        });

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Пожалуйста, заполните все поля.");
            } else {
                messageLabel.setText("");  // Очистка сообщения об ошибке после успешной проверки
                // Реализуйте логику входа
            }
        });

        // Добавление элементов в основной контейнер
        mainContainer.getChildren().addAll(titleLabel, usernameField, passwordField, buttonContainer, messageLabel);

        // Создание сцены
        Scene scene = new Scene(mainContainer, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


