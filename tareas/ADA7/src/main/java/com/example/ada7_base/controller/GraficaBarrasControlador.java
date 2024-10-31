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

public class GraficaBarrasControlador {

    private static final Logger logger = LogManager.getLogger(GraficaBarrasControlador.class);

    @FXML
    private StackedBarChart<String, Number> pilaBarras;
    private List<XYChart.Series<String, Number>> seriesDatos;
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
        pilaBarras.setTitle("Resultados de Votaciones");

        // Crear una serie de datos
        this.seriesDatos = new ArrayList<>();
        this.generarBarras(this.modeloListaProductos.getProductos());

        // Agregar los datos al gráfico
        pilaBarras.getData().addAll(seriesDatos);

        logger.info("Gráfico de barras inicializado");
    }

    public void actualizarBarras(String productoVotado) {
        System.out.println("Actualizando grafica de barras");
        // Iterar hasta encontrar el producto que se votó
        for (XYChart.Series<String, Number> serie : seriesDatos) {
            XYChart.Data<String, Number> dataSerie = serie.getData().getFirst();
            if (dataSerie.getXValue().equals(productoVotado)) {
                int currentVotes = dataSerie.getYValue().intValue();
                dataSerie.setYValue(currentVotes + 1);
                break;
            }

        }
        logger.info("Gráfico de barras actualizado");
    }

    // Crea las barras del grafico de barras, se considera a cada producto como una
    // serie que contiene un solo dato (sus numeros de votos)
    private void generarBarras(List<Producto> productos) {
        for (Producto producto : productos) {
            XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
            XYChart.Data<String, Number> dataSerie = new XYChart.Data<String, Number>(producto.getNombre(),
                    producto.getTotalVotos());
            serie.getData().add(dataSerie);
            dataSerie.getNode();
            serie.setName(producto.getNombre());
            this.seriesDatos.add(serie);
        }
        logger.info("Barras generadas");
    }
}
