package com.csc3402.lab.crypto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Main class to launch the JavaFX application
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // UI components
        CheckBox encodeButton = new CheckBox("Encrypt");
        CheckBox decodeButton = new CheckBox("Decrypt");
        TextArea inputSentence = new TextArea();
        inputSentence.setPromptText("Enter sentence...");
        TextArea inputKey = new TextArea();
        inputKey.setPromptText("Enter key...");
        TextArea outputSentence = new TextArea();
        outputSentence.setPromptText("Output will appear here...");
        outputSentence.setEditable(false);
        Button startButton = new Button("Start");
        Button clearButton = new Button("Clear");
        Button copyButton = new Button("Copy");

        // Handle encode button action
        encodeButton.setOnAction(event -> {
            if (encodeButton.isSelected()) {
                decodeButton.setDisable(true);
            } else {
                decodeButton.setDisable(false);
            }
        });

        // Handle decode button action
        decodeButton.setOnAction(event -> {
            if (decodeButton.isSelected()) {
                encodeButton.setDisable(true);
            } else {
                encodeButton.setDisable(false);
            }
        });

        // Handle start button action
        startButton.setOnAction(event -> {
            boolean encodeSelected = encodeButton.isSelected();
            boolean decodeSelected = decodeButton.isSelected();
            int option = encodeSelected ? 1 : decodeSelected ? 2 : 0;
            Cipher cipher = new Cipher();
            String sentence = inputSentence.getText().toLowerCase().trim();
            String key = inputKey.getText().toLowerCase().trim().replace(" ", "");
            String result = cipher.runApplication(sentence, key, option);
            outputSentence.setText(result);
        });

        // Handle clear button action
        clearButton.setOnAction(event -> {
            encodeButton.setSelected(false);
            decodeButton.setSelected(false);
            encodeButton.setDisable(false);
            decodeButton.setDisable(false);
            inputSentence.clear();
            inputKey.clear();
            outputSentence.clear();
        });

        // Handle copy button action
        copyButton.setOnAction(event -> {
            String output = outputSentence.getText();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(output);
            clipboard.setContent(content);
        });

        // Arrange UI components in a VBox
        VBox vbox = new VBox(10, encodeButton, decodeButton, inputSentence, inputKey, startButton, clearButton, copyButton, outputSentence);
        vbox.setPadding(new Insets(10));

        // Set up the scene and stage
        Scene scene = new Scene(vbox, 400, 500);
        primaryStage.setTitle("Vigenère Cipher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
