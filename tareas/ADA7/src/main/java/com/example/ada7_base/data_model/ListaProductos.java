package com.example.ada7_base.data_model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private ArrayList<Producto> productos = new ArrayList<Producto>();

    public ListaProductos() {
        // crear productos dentro del constructor:
        Producto agua = obtenerProducto(0);
        Producto galletas = obtenerProducto(1);
        Producto papas = obtenerProducto(2);

        // agregar productos a la lista
        this.productos.add(agua);
        this.productos.add(galletas);
        this.productos.add(papas);
    }

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

    public void votarProducto(String nombre) {
        boolean encontrado = false;

        for (Producto producto : this.productos) {
            if (nombre.equals(producto.obtenerNombre())) {
                producto.colocarTotalVotos(producto.obtenerTotalVotos() + 1);
                System.out.println("producto: " + producto.obtenerNombre() + " votos: " + producto.obtenerTotalVotos());
                try {
                    producto.agregarVotosArchivoPropio();
                    this.agregarVotosArchivo(producto.obtenerTotalVotos(), producto.obtenerNombre());
                } catch (IOException exepcion) {
                    exepcion.printStackTrace();
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado");
        }
    }

    private Producto obtenerProducto(int numlinea) {
        String[] datosArchivo = new String[3];
        StringBuilder constructorCadenas = new StringBuilder();
        int lineaActual = 0;

        try (BufferedReader lectorArchivo = new BufferedReader(
                new FileReader("src/main/java/com/example/ada7_base/data_model/productos.txt"))) {
            String linea;
            while ((linea = lectorArchivo.readLine()) != null) {
                if (lineaActual == numlinea) {
                    int identificadorDato = 0;
                    for (char caracterActual : linea.toCharArray()) {
                        if (caracterActual == ' ') {
                            datosArchivo[identificadorDato] = constructorCadenas.toString();
                            constructorCadenas.setLength(0);
                            identificadorDato++;
                        } else {
                            constructorCadenas.append(caracterActual);
                        }
                    }
                    datosArchivo[identificadorDato] = constructorCadenas.toString();
                    break;
                }
                lineaActual++;
            }
        } catch (IOException exepcion) {
            exepcion.printStackTrace();
        }

        int totalVotos = Integer.parseInt(datosArchivo[0]);
        String nombre = datosArchivo[1];
        String imagenURL = datosArchivo[2];

        return new Producto(nombre, totalVotos, imagenURL);
    }

    public void agregarVotosArchivo(int totalVotos, String producto) {
        List<String> lineasArchivo = new ArrayList<>();
        String rutaArchivo = "src/main/java/com/example/ada7_base/data_model/productos.txt";

        try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(rutaArchivo))) {
            String lineaArchivo;
            while ((lineaArchivo = lectorArchivo.readLine()) != null) {
                if (lineaArchivo.contains(producto)) {
                    String[] partes = lineaArchivo.split(" ", 3);
                    partes[0] = String.valueOf(totalVotos);
                    lineaArchivo = String.join(" ", partes);
                }
                lineasArchivo.add(lineaArchivo);
            }
        } catch (IOException exepcion) {
            exepcion.printStackTrace();
        }

        try (BufferedWriter escritorArchivo = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String lineaArchivo : lineasArchivo) {
                escritorArchivo.write(lineaArchivo);
                escritorArchivo.newLine(); // Agrega una nueva línea después de cada línea escrita
            }
        } catch (IOException exepcion) {
            exepcion.printStackTrace();
        }
    }

}