package com.example.ada7_base.controller;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import com.example.ada7_base.observer.IObservador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.List;


public class GraficaPastelControlador implements IObservador {

    @FXML
    private PieChart pastelBase;
    private ObservableList<PieChart.Data> rebanadas;

    public void init() {
        this.rebanadas = FXCollections.observableArrayList();
        this.rellenarGrafica();
        pastelBase.setData(this.rebanadas);
        pastelBase.setTitle("Resumen de votos por grafica de pasteles");
    }

    @Override
    public void actualizarVista() {
        rellenarGrafica();
    }

    public void rellenarGrafica() {
        limpiarGrafica();
        for (Producto producto : ListaProductos.obtenerProductos()) {
            PieChart.Data datos = new PieChart.Data(producto.obtenerNombre() + " = " + producto.obtenerTotalVotos(),
                    producto.obtenerTotalVotos());
            this.rebanadas.add(datos);

        }
    }

    public void limpiarGrafica() {
        this.rebanadas.clear();
    }
}