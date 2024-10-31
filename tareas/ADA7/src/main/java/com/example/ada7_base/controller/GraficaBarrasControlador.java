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

    private static final Logger regisrador = LogManager.getLogger(GraficaBarrasControlador.class);

    @FXML
    private StackedBarChart<String, Number> pilaBarras;
    private List<XYChart.Series<String, Number>> seriesDatos;
    private ListaProductos modeloListaProductos;

    public void init(ListaProductos listaProductos) {

        this.modeloListaProductos = listaProductos;
        CategoryAxis ejeX = new CategoryAxis();
        ejeX.setLabel("Producto");
        ejeX.setCategories(FXCollections.observableArrayList());
        NumberAxis ejeY = new NumberAxis();
        ejeY.setLabel("Votos");

        pilaBarras.setTitle("Resultados de Votaciones");

        this.seriesDatos = new ArrayList<>();
        this.generarBarras(this.modeloListaProductos.obtenerProductos());

        pilaBarras.getData().addAll(seriesDatos);

        regisrador.info("Gráfico de barras inicializado");
    }

    public void actualizarBarras(String productoVotado) {
        for (XYChart.Series<String, Number> serie : seriesDatos) {
            XYChart.Data<String, Number> serieDatos = serie.getData().getFirst();
            if (serieDatos.getXValue().equals(productoVotado)) {
                int votosActual = serieDatos.getYValue().intValue();
                serieDatos.setYValue(votosActual + 1);
                break;
            }

        }
        regisrador.info("Gráfico de barras actualizado");
    }

    // Crea las barras del grafico de barras, se considera a cada producto como una
    // serie que contiene un solo dato (sus numeros de votos)
    private void generarBarras(List<Producto> productos) {
        for (Producto producto : productos) {
            XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
            XYChart.Data<String, Number> seriedatos = new XYChart.Data<String, Number>(producto.obtenerNombre(),
                    producto.obtenerTotalVotos());
            serie.getData().add(seriedatos);
            seriedatos.getNode();
            serie.setName(producto.obtenerNombre());
            this.seriesDatos.add(serie);
        }
        regisrador.info("Barras generadas");
    }
}
