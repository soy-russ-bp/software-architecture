package com.example.ada7_base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller_Main {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}