package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainControlador {

    private static final Logger logger = LogManager.getLogger(MainControlador.class);
    private static final double mainY = 400;
    private static final double mainX = 912;

    @FXML
    private VBox productosVBox;
    private Stage escenarioPastel;
    private Stage escenarioBarras;

    private static ListaProductos listaProductos = new ListaProductos();
    Map<String, Label> etiquetasVotos = new HashMap<>();
    private GraficaPastelControlador controladorPastel;
    private GraficaBarrasControlador controladorBarras;

    @FXML
    public void initialize() {

         for (Producto producto : listaProductos.getProductos()) {
         Image imagen = new Image(getClass().getResourceAsStream( producto.getImageUrl() ) );
         ImageView imagenView = new ImageView(imagen);
         imagenView.setFitWidth(50);imagenView.setFitHeight(50);imagenView.setPreserveRatio(true);
         productosVBox.getChildren().add(imagenView);

         Label conteo = new Label("conteo de votos por" + producto.getNombre() + ": " +
         producto.getTotalVotos() );
         etiquetasVotos.put(producto.getNombre(), conteo);
         productosVBox.getChildren().add(conteo);

         Button button = new Button("vota por: " + producto.getNombre() );
         button.setOnAction(event -> {
            listaProductos.votarProducto(producto.getNombre());
            actualizarNumeros(producto.getNombre());
         });
         productosVBox.getChildren().add(button);
         }


        Button button1 = new Button("Gráfica de pastel");
        button1.setOnAction(event -> {
            try {
                mostrarPastel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Button button2 = new Button("Gráfica de barras");
        button2.setOnAction(event -> {
            try {
                mostrarBarras();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(button1, button2);

        productosVBox.getChildren().add(buttonBox);

        productosVBox.setAlignment(Pos.TOP_CENTER);

        logger.info("Vista principal inicializada");

        this.escenarioPastel = new Stage();
        this.escenarioBarras = new Stage();
    }

    void mostrarPastel() throws IOException {
        System.out.println("abriendo grafica de pastel");
        logger.info("Mostrando gráfica de pastel");

        if(this.escenarioPastel == null || !this.escenarioPastel.isShowing()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/example/ada7_base/Vista_GraficaPastel.fxml"));
            Parent root = fxmlLoader.load();

            this.controladorPastel = fxmlLoader.getController();
            this.controladorPastel.init(listaProductos);
            this.escenarioPastel.setScene(new Scene(root));
            this.escenarioPastel.setX(mainX-700);
            this.escenarioPastel.setY(mainY);
        }
        this.escenarioPastel.show();
    }

    void mostrarBarras() throws IOException {
        System.out.println("abriendo grafica de barras");
        logger.info("Mostrando gráfica de barras");

        if(this.escenarioBarras == null || !this.escenarioBarras.isShowing()) {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass()
                    .getResource("/com/example/ada7_base/Vista_GraficaBarras.fxml"));
            Parent root = fxmlLoader.load();
            this.controladorBarras = fxmlLoader.getController();
            this.controladorBarras.init(listaProductos);
            this.escenarioBarras.setScene(new Scene(root));
            this.escenarioBarras.setX(mainX);
            this.escenarioBarras.setY(mainY);
        }
        this.escenarioBarras.show();
    }

    void actualizarNumeros(String productoVotado){
        Producto producto = listaProductos.encontrarProducto(productoVotado);

        Label conteoLabel = etiquetasVotos.get(productoVotado);

        if (conteoLabel != null) {
            conteoLabel.setText("Conteo de votos por " + producto.getNombre() + ": " + producto.getTotalVotos());
        }
        if (!(this.controladorBarras == null ||  this.controladorPastel == null)){
            this.controladorBarras.actualizarBarras(productoVotado);
            this.controladorPastel.actualizarPastel(productoVotado);
        }

    }
}

