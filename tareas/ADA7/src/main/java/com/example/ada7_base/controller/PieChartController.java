package com.example.ada7_base.controller;
import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.List;


public class PieChartController{
    @FXML
    private PieChart pieChart;
    private ObservableList<PieChart.Data> slices;
    //Data model que contiene la lista de productos
    private ListaProductos modeloListaProductos;


    public void init(ListaProductos listaProductos){
        this.modeloListaProductos = listaProductos;
        this.slices = FXCollections.observableArrayList();
        this.generateSlices(this.modeloListaProductos.getProductos());
        pieChart.setData(this.slices);
        pieChart.setTitle("Resumen de votos por grafica de pasteles");
    }
    public void updatePieChart(String productVoted) {
        System.out.println("Actualizando grafica de pasteles");
        // Iterar sobre la lista hasta encontrar el producto votado
        for (PieChart.Data slice : this.pieChart.getData()) {
            if (slice.getName().contains(productVoted)) {
                int oldValue = (int)slice.getPieValue();
                int newValue = oldValue+1;
                slice.setPieValue(newValue);
                String name = slice.getName();
                slice.setName(name.replace(String.valueOf(oldValue),String.valueOf(newValue)));
                break;
            }
        }
    }
        //Genera las rebanadas de la grafica de pasteles
        private void generateSlices(List<Producto> productos){
            for (Producto producto : productos){
                PieChart.Data data = new PieChart.Data(producto.getNombre() + " = " + producto.getTotalVotos(), producto.getTotalVotos());
                this.slices.add(data);

            }
        }
}
