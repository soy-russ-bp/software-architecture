package com.example.ada7_base.observer;

import java.util.ArrayList;
import java.util.List;

import com.example.ada7_base.data_model.ListaProductos;
import com.example.ada7_base.observer.IObservador;

public class Observable {
    private List<IObservador> observadores = new ArrayList<>();

    public void agregarObservador(IObservador observador,ListaProductos productos) {
        observadores.add(observador);
        observador.init(productos);
    }

    public void eliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String productoVotado) {
        for (IObservador observador : observadores) {
            observador.actualizarGrafica(productoVotado);
        }
    }
}
