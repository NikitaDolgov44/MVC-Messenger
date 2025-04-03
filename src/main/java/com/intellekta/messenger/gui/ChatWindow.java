package com.intellekta.messenger.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatWindow {
    public void start(Stage chatStage, String nickname) {
        chatStage.setTitle("Messages");

        VBox chatContainer = new VBox(10);
        chatContainer.setAlignment(Pos.CENTER);
        chatContainer.getStyleClass().add("chat-container");

        Label titleLabel = new Label("Сообщения");
        titleLabel.getStyleClass().add("title");

        Button backButton = new Button("Назад");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(event -> {
            // Переход к предыдущему экрану
            NicknameWindow nicknameWindow = new NicknameWindow(chatStage);
            chatStage.close();
        });

        HBox backButtonContainer = new HBox(backButton);
        backButtonContainer.setAlignment(Pos.TOP_LEFT);

        VBox messageContainer = new VBox(5);
        messageContainer.getStyleClass().add("chat-messages");

        ScrollPane chatScroll = new ScrollPane(messageContainer);
        chatScroll.setFitToWidth(true);
        chatScroll.getStyleClass().add("chat-window");

        TextField messageField = new TextField();
        messageField.setPromptText("Type your message...");
        messageField.getStyleClass().add("input-field");

        Button sendButton = new Button("Отправить");
        sendButton.getStyleClass().add("submit-button");
        sendButton.setOnAction(event -> {
            String messageText = messageField.getText();
            if (!messageText.isEmpty()) {
                Label messageLabel = new Label(nickname + ": " + messageText);
                messageLabel.getStyleClass().add("message");
                messageContainer.getChildren().add(messageLabel);
                messageField.clear();
            }
        });

        HBox messageBox = new HBox(10, messageField, sendButton);
        messageBox.setAlignment(Pos.CENTER);

        chatContainer.getChildren().addAll(titleLabel, backButtonContainer, chatScroll, messageBox);

        Scene scene = new Scene(chatContainer, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/static/css/chat.css").toExternalForm());
        chatStage.setScene(scene);
        chatStage.show();
    }
}
