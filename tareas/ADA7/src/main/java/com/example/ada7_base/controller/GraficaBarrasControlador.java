package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import com.example.ada7_base.observer.IObservador;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GraficaBarrasControlador implements IObservador {

    @FXML
    private StackedBarChart<String, Number> pilaBarras;
    private List<XYChart.Series<String, Number>> seriesDatos;

    public void init() {

        CategoryAxis ejeX = new CategoryAxis();
        ejeX.setLabel("Producto");
        ejeX.setCategories(FXCollections.observableArrayList());
        NumberAxis ejeY = new NumberAxis();
        ejeY.setLabel("Votos");

        pilaBarras.setTitle("Resultados de Votaciones");

        this.seriesDatos = new ArrayList<>();
        this.rellenarGrafica();

        pilaBarras.getData().addAll(seriesDatos);

    }
    @Override
    public void actualizarVista() {
        rellenarGrafica();
    }

    public void rellenarGrafica() {
        limpiarGrafica();
        for (Producto producto : ListaProductos.obtenerProductos()) {
            XYChart.Series<String, Number> serie = new XYChart.Series<String, Number>();
            XYChart.Data<String, Number> seriedatos = new XYChart.Data<String, Number>(producto.obtenerNombre(),
                    producto.obtenerTotalVotos());
            serie.getData().add(seriedatos);
            seriedatos.getNode();
            serie.setName(producto.obtenerNombre());
            this.seriesDatos.add(serie);
        }
    }

    public void limpiarGrafica() {
        for (XYChart.Series<String, Number> serie : seriesDatos) {
            for (XYChart.Data<String, Number> serieDatos : serie.getData()) {
                serieDatos.setYValue(0);
            }
        }
    }
}
