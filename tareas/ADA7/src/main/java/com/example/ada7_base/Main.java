package com.example.ada7_base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage escenario) throws IOException {
        FXMLLoader cargadorFXML = new FXMLLoader(Main.class.getResource("Vista_Main.fxml"));
        Scene escena = new Scene(cargadorFXML.load(), 720, 720);
        escenario.setTitle("Sistema de votaciones");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest((WindowEvent evento) -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}