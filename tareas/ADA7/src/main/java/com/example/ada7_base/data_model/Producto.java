package com.example.ada7_base.data_model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.ada7_base.controller.Controller_Main;

public class Producto {
    private String nombre;
    private int totalVotos;
    private String imageUrl;
    private File archivo;

    private static final Logger logger = LogManager.getLogger(Controller_Main.class);

    public Producto(String nombre, int totalVotos, String imageUrl) {
        this.nombre = nombre;
        this.totalVotos = totalVotos;
        this.imageUrl = imageUrl;
        crearArchivo(this.nombre);
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public File getArchivo() {
        return archivo;
    }

    // setters
    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
            logger.info("Archivo creado: " + archivo.getName());

        } catch (Exception e) {
            System.out.println("Ocurrió un error");
            e.printStackTrace();
            logger.info("Error al crear el archivo");
        }
    }

    public void addVotosArchivoPropio() throws IOException {
        LocalDateTime fechaHora = LocalDateTime.now();

        if (archivo.exists()) {
            FileWriter writer = new FileWriter(archivo, true); // Modo append
            writer.write(fechaHora + " - Voto\n");
            writer.close();
            logger.info("Voto registrado en el archivo");
        } else {
            System.out.println("El archivo no existe");
            logger.info("El archivo no existe");
        }

    }

}