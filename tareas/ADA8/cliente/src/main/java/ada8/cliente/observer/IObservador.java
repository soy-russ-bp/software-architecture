package ada8.cliente.observer;

import ada8.cliente.data_model.ListaProductos;
import ada8.cliente.data_model.Producto;

import java.util.List;


public interface IObservador {
    void init();
    void actualizarVista();
}
