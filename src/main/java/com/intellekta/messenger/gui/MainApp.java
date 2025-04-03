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
        showRegistrationScreen();
    }

    private void showRegistrationScreen() {
        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(40));
        container.getStylesheets().add(getClass().getResource("/static/css/registration.css").toExternalForm());

        Label title = new Label("Регистрация");
        title.getStyleClass().add("title");

        TextField username = new TextField();
        username.setPromptText("Имя пользователя");
        username.getStyleClass().add("input-field");

        PasswordField password = new PasswordField();
        password.setPromptText("Пароль");
        password.getStyleClass().add("input-field");

        Button registerBtn = new Button("Зарегистрироваться");
        registerBtn.getStyleClass().add("primary-button");

        Hyperlink loginLink = new Hyperlink("Уже есть аккаунт? Войти");
        loginLink.getStyleClass().add("link");

        registerBtn.setOnAction(e -> {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                showAlert("Ошибка", "Заполните все поля");
                return;
            }

            boolean success = true; // Заглушка для логики регистрации
            if (success) {
                showAlert("Успех", "Регистрация завершена!");
                showLoginScreen();
            }
        });

        loginLink.setOnAction(e -> showLoginScreen());

        container.getChildren().addAll(title, username, password, registerBtn, loginLink);
        primaryStage.setScene(new Scene(container, 400, 600));
        primaryStage.show();
    }

    private void showLoginScreen() {
        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(40));
        container.getStylesheets().add(getClass().getResource("/static/css/login.css").toExternalForm());

        Label title = new Label("Вход");
        title.getStyleClass().add("title");

        TextField username = new TextField();
        username.setPromptText("Имя пользователя");
        username.getStyleClass().add("input-field");

        PasswordField password = new PasswordField();
        password.setPromptText("Пароль");
        password.getStyleClass().add("input-field");

        Button loginBtn = new Button("Войти");
        loginBtn.getStyleClass().add("primary-button");

        Hyperlink registerLink = new Hyperlink("Нет аккаунта? Зарегистрироваться");
        registerLink.getStyleClass().add("link");

        loginBtn.setOnAction(e -> {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                showAlert("Ошибка", "Заполните все поля");
                return;
            }

            boolean success = true; // Заглушка для проверки учетных данных
            if (success) {
                showChatWindow(username.getText());
            } else {
                showAlert("Ошибка", "Неверные данные");
            }
        });

        registerLink.setOnAction(e -> showRegistrationScreen());

        container.getChildren().addAll(title, username, password, loginBtn, registerLink);
        primaryStage.setScene(new Scene(container, 400, 600));
    }

    private void showChatWindow(String username) {
        BorderPane root = new BorderPane();
        root.getStylesheets().add(getClass().getResource("/static/css/chat.css").toExternalForm());

        // Шапка чата
        HBox header = new HBox();
        header.getStyleClass().add("chat-header");
        header.setPadding(new Insets(10, 20, 10, 20));

        Label title = new Label("Чат: " + username);
        title.getStyleClass().add("title");
        header.getChildren().add(title);

        // Список сообщений
        ListView<String> messageList = new ListView<>();
        messageList.setCellFactory(lv -> new MessageListCell());
        messageList.getStyleClass().add("message-list");

        // Автоматическая прокрутка к новым сообщениям
        messageList.itemsProperty().addListener((obs, old, newValue) -> {
            if (newValue.size() > 0) {
                messageList.scrollTo(newValue.size() - 1);
            }
        });

        // Панель ввода
        HBox inputPanel = new HBox(10);
        inputPanel.setPadding(new Insets(10));
        inputPanel.setStyle("-fx-background-color: #f0f0f0;");

        TextField messageField = new TextField();
        messageField.setPromptText("Введите сообщение...");
        messageField.getStyleClass().add("input-field");
        HBox.setHgrow(messageField, Priority.ALWAYS);

        Button sendBtn = new Button("➤");
        sendBtn.getStyleClass().add("send-button");

        sendBtn.setOnAction(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                messageList.getItems().add("Вы: " + message);
                messageField.clear();
            }
        });

        inputPanel.getChildren().addAll(messageField, sendBtn);
        root.setTop(header);
        root.setCenter(messageList);
        root.setBottom(inputPanel);

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
    }

    // Класс для кастомных сообщений
    class MessageListCell extends ListCell<String> {
        private final Label messageLabel = new Label();
        private final HBox container = new HBox();
        private final int MAX_BUBBLE_WIDTH = 400;

        public MessageListCell() {
            messageLabel.setWrapText(true);
            messageLabel.setMaxWidth(MAX_BUBBLE_WIDTH);
            messageLabel.setPadding(new Insets(8, 12, 8, 12));
            messageLabel.setStyle("-fx-background-color: #DCF8C6; " +
                    "-fx-background-radius: 15px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 5, 0, 0, 3px);");

            container.getChildren().add(messageLabel);
            container.setAlignment(Pos.CENTER_RIGHT);
            HBox.setHgrow(messageLabel, Priority.NEVER);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                messageLabel.setText(item.replace("Вы: ", ""));
                setGraphic(container);
                setAlignment(Pos.CENTER_RIGHT);
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}




