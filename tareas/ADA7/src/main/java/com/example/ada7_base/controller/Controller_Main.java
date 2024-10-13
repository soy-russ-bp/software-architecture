package com.example.ada7_base.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller_Main {

    private static final Logger logger = LogManager.getLogger(Controller_Main.class);

    @FXML
    private VBox productosVBox;

    // provisional
    private List<String> listaProductos;

    // private ArrayList listaProductos = ListaProductos.getProductos();

    @FXML
    public void initialize() {
        logger.info("Inicializando la vista principal");
        // provisional
        listaProductos = new ArrayList<>();
        listaProductos.add("maruchan");
        listaProductos.add("principes");
        listaProductos.add("higado encebollado");
        Image imagen = new Image(getClass().getResourceAsStream("/images/maruchan.jpg"));

        for (String producto : listaProductos) {
            ImageView imagenView = new ImageView(imagen);
            imagenView.setFitWidth(100);
            imagenView.setFitHeight(100);
            imagenView.setPreserveRatio(true);
            productosVBox.getChildren().add(imagenView);

            Label conteo = new Label("conteo de votos por" + producto + ": ");
            productosVBox.getChildren().add(conteo);

            Button button = new Button("vota por: " + producto);
            button.setOnAction(event -> {
                // producto.votar();
                System.out.println("votaste por: " + producto);
                logger.info(producto + " votado");
            });
            productosVBox.getChildren().add(button);
        }

        /*
         * version del codigo de iteración dinámica en momento de integración
         * 
         * for (Producto producto : listaProductos) {
         * Image imagen = new Image(getClass().getResourceAsStream(producto.getURL());
         * ImageView imagenView = new ImageView(imagen);
         * imagenView.setFitWidth(50);imagenView.setFitHeight(50);imagenView.
         * setPreserveRatio(true);
         * productosVBox.getChildren().add(imagenView);
         * 
         * Label conteo = new Label("conteo de votos por" + producto + ": " +
         * producto.getVotos() );
         * productosVBox.getChildren().add(conteo);
         * 
         * Button button = new Button("vota por: " + producto.getNombre();
         * button.setOnAction(event -> {
         * //producto.votar();
         * });
         * productosVBox.getChildren().add(button);
         * }
         */

        Button button1 = new Button("Gráfica de pastel");
        button1.setOnAction(event -> {
            mostrarPastel();
        });
        Button button2 = new Button("Gráfica de barras");
        button2.setOnAction(event -> {
            mostrarBarras();
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10); // Espaciado entre los botones
        buttonBox.setAlignment(Pos.CENTER); // Alinea los botones en el centro
        buttonBox.getChildren().addAll(button1, button2);

        productosVBox.getChildren().add(buttonBox);

        productosVBox.setAlignment(Pos.TOP_CENTER);

        logger.info("Vista principal inicializada");
    }

    void mostrarPastel() {
        System.out.println("abriendo grafica de pastel");
        logger.info("Mostrando gráfica de pastel");
    }

    void mostrarBarras() {
        System.out.println("abriendo grafica de barras");
        logger.info("Mostrando gráfica de barras");
    }
}