package com.example.ada7_base.data_model;

import java.io.IOException;
import java.util.ArrayList;

import com.example.ada7_base.Cliente;

import utils.Mensaje;
import utils.MensajeTipo;
import utils.Variable;

public class ListaProductos {
    private ArrayList<Producto> productos = new ArrayList<Producto>();


    public ArrayList<Producto> obtenerProductos() {
        return productos;
    }

    public Producto encontrarProducto(String nombre) {
        for (Producto producto : this.productos) {
            if (nombre.equals(producto.obtenerNombre())) {
                return producto;
            }
        }
        return null;
    }

    public void votarProducto(String nombre) throws IOException {
        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);
        solicitud.setServicio("votar");
        solicitud.setNumeroVariables(1);
        solicitud.addVariable(new Variable("variable1", nombre));
        solicitud.addVariable(new Variable("valor1", "1")); // Un voto

        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);
    }

    private void contarProductos() throws IOException{
        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);
        solicitud.setServicio("contar");
        solicitud.setNumeroVariables(0);

        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);

        for (int i = 1; i <= respuesta.getNumeroVariables(); i++) {
            Producto producto = new Producto(null, i, null);

            producto.colocarNombre(respuesta.getVariable(i-1).getNombre());
            producto.colocarTotalVotos(Integer.parseInt(respuesta.getVariable(i - 1).getValor()));
            producto.colocarImageUrl(producto.obtenerNombre() + ".txt");
            
            productos.add(producto);
        }
    }
    
}