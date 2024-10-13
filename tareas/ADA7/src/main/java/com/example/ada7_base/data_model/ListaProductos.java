package com.example.ada7_base.data_model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ada7_base.controller.Controller_Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListaProductos {

    private static final Logger logger = LogManager.getLogger(Controller_Main.class);
    private List<Producto> productos = new ArrayList<Producto>();
   

    public ListaProductos(){
        logger.info("Inicializando lista de productos");
        //crear productos dentro del constructor:
        Producto agua = new Producto("agua", 0, "https://www.google.com");
        Producto galletas = new Producto("galletas", 0, "https://www.google.com");
        Producto papas = new Producto("papas", 0, "https://www.google.com");

        //agregar productos a la lista
        this.productos.add(agua);
        this.productos.add(galletas);
        this.productos.add(papas);
        logger.info("Lista de productos inicializada");
    }
    
    //identifica por qué productos se vota y aumenta el total de votos de ese producto
    public void votarProducto(String nombre) {
    boolean encontrado = false; // Bandera para saber si se encontró el producto

    

    for (Producto producto : this.productos) {
        if (nombre.equals(producto.getNombre())) {
            producto.setTotalVotos(producto.getTotalVotos() + 1);
            try {
                producto.addVotosArchivo(); // Agregar voto al archivo
            } catch (IOException e) {
                e.printStackTrace();
            }
            encontrado = true; // Marcar que se encontró el producto
            break; // Salir del ciclo una vez que se ha encontrado y votado
        }
    }


    if (!encontrado) {
        System.out.println("Producto no encontrado");
    }
    logger.info("Voto registrado para el producto: " + nombre);
}


    public void agregarProducto(String nombre, String imageUrl){
        //implementar
    }

    public void eliminarProducto(String nombre){
        //implementar
    }


    public ArrayList<Producto> getProductos(){
        return null;
    }

    public void leerProductos(){
        //implementar
    }

    public void leerVotos(){
        //implementar
    }
}
