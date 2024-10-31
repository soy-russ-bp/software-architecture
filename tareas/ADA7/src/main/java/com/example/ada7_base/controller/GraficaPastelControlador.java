package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GraficaPastelControlador {
    private static final Logger logger = LogManager.getLogger(MainControlador.class);

    @FXML
    private PieChart pastelBase;
    private ObservableList<PieChart.Data> rebanadas;
    // Data model que contiene la lista de productos
    private ListaProductos modeloListaProductos;

    public void init(ListaProductos listaProductos) {
        logger.info("Inicializando grafica de pasteles");
        this.modeloListaProductos = listaProductos;
        this.rebanadas = FXCollections.observableArrayList();
        this.generarRebanadas(this.modeloListaProductos.getProductos());
        pastelBase.setData(this.rebanadas);
        pastelBase.setTitle("Resumen de votos por grafica de pasteles");
        logger.info("Grafica de pasteles inicializada");
    }

    public void actualizarPastel(String productoVotado) {
        logger.info("Actualizando grafica de pasteles");
        System.out.println("Actualizando grafica de pasteles");
        // Iterar sobre la lista hasta encontrar el producto votado
        for (PieChart.Data slice : this.pastelBase.getData()) {
            if (slice.getName().contains(productoVotado)) {
                int oldValue = (int) slice.getPieValue();
                int newValue = oldValue + 1;
                slice.setPieValue(newValue);
                String name = slice.getName();
                slice.setName(name.replace(String.valueOf(oldValue), String.valueOf(newValue)));
                break;
            }
        }
        logger.info("Grafica de pasteles actualizada");
    }

    // Genera las rebanadas de la grafica de pasteles
    private void generarRebanadas(List<Producto> productos) {
        logger.info("Generando rebanadas de la grafica de pasteles");
        for (Producto producto : productos) {
            PieChart.Data data = new PieChart.Data(producto.getNombre() + " = " + producto.getTotalVotos(),
                    producto.getTotalVotos());
            this.rebanadas.add(data);

        }
        logger.info("Rebanadas de la grafica de pasteles generadas");
    }
}
