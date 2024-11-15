package com.example.ada7_base.data_model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.ada7_base.controller.MainControlador;

public class Producto {
    private String nombre;
    private int totalVotos;
    private String imagenUrl;
    private File archivo;

    private static final Logger registrador = LogManager.getLogger(MainControlador.class);

    public Producto(String nombre, int totalVotos, String imagenUrl) {
        this.nombre = nombre;
        this.totalVotos = totalVotos;
        this.imagenUrl = imagenUrl;
        crearArchivo(this.nombre);
    }

    // getters
    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerTotalVotos() {
        return totalVotos;
    }

    public String obtenerImageUrl() {
        return imagenUrl;
    }

    public File obtenerArchivo() {
        return archivo;
    }

    // setters
    public void colocarNombre(String nombre) {
        this.nombre = nombre;

    }

    public void colocarTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public void colocarImageUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    private void crearArchivo(String nombre) {
        try {
            String ruta = "src/main/java/com/example/ada7_base/data_model";
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs(); // Crear el directorio si no existe
            }

            archivo = new File(ruta + nombre + ".txt"); // Inicializar el archivo

            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe");
            }
            registrador.info("Archivo creado: " + archivo.getName());

        } catch (Exception exepcion) {
            System.out.println("Ocurrió un error");
            exepcion.printStackTrace();
            registrador.info("Error al crear el archivo");
        }
    }

    public void agregarVotosArchivoPropio() throws IOException {
        LocalDateTime fechaHora = LocalDateTime.now();

        if (archivo.exists()) {
            FileWriter escritor = new FileWriter(archivo, true); // Modo append
            escritor.write(fechaHora + " - Voto\n");
            escritor.close();
            registrador.info("Voto registrado en el archivo");
        } else {
            System.out.println("El archivo no existe");
            registrador.info("El archivo no existe");
        }

    }

}