package com.example.ada7_base.observer;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.data_model.Producto;

import java.util.List;


public interface IObservador {
    void init(ListaProductos productos);
    void rellenarGrafica(List<Producto> productos);
    void actualizarGrafica(String productoVotado);

}
