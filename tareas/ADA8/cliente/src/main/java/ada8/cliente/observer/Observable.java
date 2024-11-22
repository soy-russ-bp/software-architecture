package ada8.cliente.observer;

import java.util.ArrayList;
import java.util.List;

import ada8.cliente.data_model.ListaProductos;
import ada8.cliente.observer.IObservador;

public class Observable {
    private List<IObservador> observadores = new ArrayList<>();

    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
        observador.init();
    }

    public void eliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String productoVotado) {
        for (IObservador observador : observadores) {
            observador.actualizarVista();
        }
    }
}
