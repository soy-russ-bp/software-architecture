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
    private static final Logger registrador = LogManager.getLogger(MainControlador.class);

    @FXML
    private PieChart pastelBase;
    private ObservableList<PieChart.Data> rebanadas;
    private ListaProductos modeloListaProductos;

    public void init(ListaProductos listaProductos) {
        registrador.info("Inicializando gráfica de pasteles");
        this.modeloListaProductos = listaProductos;
        this.rebanadas = FXCollections.observableArrayList();
        this.generarRebanadas(this.modeloListaProductos.obtenerProductos());
        pastelBase.setData(this.rebanadas);
        pastelBase.setTitle("Resumen de votos por grafica de pasteles");
        registrador.info("Gráfica de pasteles inicializada");
    }

    public void actualizarPastel(String productoVotado) {
        registrador.info("Actualizando grafica de pasteles");
        System.out.println("Actualizando grafica de pasteles");
        for (PieChart.Data rebanada : this.pastelBase.getData()) {
            if (rebanada.getName().contains(productoVotado)) {
                int valorAnterior = (int) rebanada.getPieValue();
                int valorNuevo = valorAnterior + 1;
                rebanada.setPieValue(valorNuevo);
                String nombreRebanada = rebanada.getName();
                rebanada.setName(nombreRebanada.replace(String.valueOf(valorAnterior), String.valueOf(valorNuevo)));
                break;
            }
        }
        registrador.info("Gráfica de pasteles actualizada");
    }

    private void generarRebanadas(List<Producto> productos) {
        registrador.info("Generando rebanadas de la gráfica de pasteles");
        for (Producto producto : productos) {
            PieChart.Data datos = new PieChart.Data(producto.obtenerNombre() + " = " + producto.obtenerTotalVotos(),
                    producto.obtenerTotalVotos());
            this.rebanadas.add(datos);

        }
        registrador.info("Rebanadas de la gráfica de pasteles generadas");
    }
}
