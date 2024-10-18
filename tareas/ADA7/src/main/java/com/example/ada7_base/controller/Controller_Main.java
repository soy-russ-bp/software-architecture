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

public class Controller_Main {

    private static final Logger logger = LogManager.getLogger(Controller_Main.class);
    private static final double mainY = 400;
    private static final double mainX = 912;

    @FXML
    private VBox productosVBox;
    private Stage stagePie;
    private Stage stageBar;

    private static ListaProductos listaProductos = new ListaProductos();
    Map<String, Label> labelsDeVotos = new HashMap<>();
    private PieChartController pieChartController;
    private BarChartController barCharController;

    @FXML
    public void initialize() {

         for (Producto producto : listaProductos.getProductos()) {
         Image imagen = new Image(getClass().getResourceAsStream( producto.getImageUrl() ) );
         ImageView imagenView = new ImageView(imagen);
         imagenView.setFitWidth(50);imagenView.setFitHeight(50);imagenView.setPreserveRatio(true);
         productosVBox.getChildren().add(imagenView);

         Label conteo = new Label("conteo de votos por" + producto.getNombre() + ": " +
         producto.getTotalVotos() );
         labelsDeVotos.put(producto.getNombre(), conteo);
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

        this.stagePie = new Stage();
        this.stageBar = new Stage();
    }

    void mostrarPastel() throws IOException {
        System.out.println("abriendo grafica de pastel");
        logger.info("Mostrando gráfica de pastel");

        if(this.stagePie == null || !this.stagePie.isShowing()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                                    "/com/example/ada7_base/View_GraphPastel.fxml"));
            Parent root = fxmlLoader.load();

            this.pieChartController = fxmlLoader.getController();
            this.pieChartController.init(listaProductos);
            this.stagePie.setScene(new Scene(root));
            this.stagePie.setX(mainX-700);
            this.stagePie.setY(mainY);
        }
        this.stagePie.show();
    }

    void mostrarBarras() throws IOException {
        System.out.println("abriendo grafica de barras");
        logger.info("Mostrando gráfica de barras");

        if(this.stageBar == null || !this.stageBar.isShowing()) {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass()
                    .getResource("/com/example/ada7_base/View_GraphBarras.fxml"));
            Parent root = fxmlLoader.load();
            this.barCharController = fxmlLoader.getController();
            this.barCharController.init(listaProductos);
            this.stageBar.setScene(new Scene(root));
            this.stageBar.setX(mainX+820);
            this.stageBar.setY(mainY);
        }
        this.stageBar.show();
    }

    void actualizarNumeros(String productVoted){
        Producto producto = listaProductos.encontrarProducto(productVoted);

        Label conteoLabel = labelsDeVotos.get(productVoted);

        if (conteoLabel != null) {
            conteoLabel.setText("Conteo de votos por " + producto.getNombre() + ": " + producto.getTotalVotos());
        }
        if (!(this.barCharController == null ||  this.pieChartController == null)){
            this.barCharController.updateBarChart(productVoted);
            this.pieChartController.updatePieChart(productVoted);
        }

    }
}

