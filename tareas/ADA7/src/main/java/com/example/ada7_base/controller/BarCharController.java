package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BarCharController {

    private static final Logger logger = LogManager.getLogger(BarCharController.class);

    @FXML
    private StackedBarChart<String, Number> stackedBarChart;
    private List<XYChart.Series<String, Number>> dataSeries;
    private ListaProductos modeloListaProductos;

    public void init(ListaProductos listaProductos) {
        this.modeloListaProductos = listaProductos;
        // Definir los ejes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Producto");
        xAxis.setCategories(FXCollections.observableArrayList());
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Votos");

        // Crear el BarChart
        stackedBarChart.setTitle("Resultados de Votaciones");

        // Crear una serie de datos
        this.dataSeries = new ArrayList<>();
        this.generateBars(this.modeloListaProductos.getProductos());

        // Agregar los datos al gráfico
        stackedBarChart.getData().addAll(dataSeries);

        logger.info("Gráfico de barras inicializado");
    }

    public void updateBarChart(String productVoted) {
        System.out.println("Actualizando grafica de barras");
        // Iterar hasta encontrar el producto que se votó
        for (XYChart.Series<String, Number> serie : dataSeries) {
            XYChart.Data<String, Number> dataSerie = serie.getData().getFirst();
            if (dataSerie.getXValue().equals(productVoted)) {
                int currentVotes = dataSerie.getYValue().intValue();
                dataSerie.setYValue(currentVotes + 1);
                break;
            }

        }
        logger.info("Gráfico de barras actualizado");
    }

    // Crea las barras del grafico de barras, se considera a cada producto como una
    // serie que contiene un solo dato (sus numeros de votos)
    private void generateBars(List<Producto> productos) {
        for (Producto producto : productos) {
            XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
            XYChart.Data<String, Number> dataSerie = new XYChart.Data<String, Number>(producto.getNombre(),
                    producto.getTotalVotos());
            serie.getData().add(dataSerie);
            dataSerie.getNode();
            serie.setName(producto.getNombre());
            this.dataSeries.add(serie);
        }
        logger.info("Barras generadas");
    }
}
