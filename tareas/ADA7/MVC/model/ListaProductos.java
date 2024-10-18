package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private List<Producto> productos = new ArrayList<Producto>();
   

    public ListaProductos(){
        //crear productos dentro del constructor:
        Producto agua = getProducto(0);
        Producto galletas = getProducto(1);
        Producto papas = getProducto(2);

        //agregar productos a la lista
        this.productos.add(agua);
        this.productos.add(galletas);
        this.productos.add(papas);
    }

   
    
    //identifica por qué productos se vota y aumenta el total de votos de ese producto
    public void votarProducto(String nombre) {
        boolean encontrado = false; // Bandera para saber si se encontró el producto

        for (Producto producto : this.productos) {
            if (nombre.equals(producto.getNombre())) {
                producto.setTotalVotos(producto.getTotalVotos() + 1);
                System.out.println("producto: " + producto.getNombre() + " votos: " + producto.getTotalVotos());
                try {
                    producto.addVotosArchivo(); // Agregar voto al archivo
                    this.addVotosArchivo(producto.getTotalVotos(), producto.getNombre());
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
}

    public Producto getProducto(int numlinea) {
        String[] datos = new String[3]; // Arreglo para nombre, votos, imagen
        StringBuilder sb = new StringBuilder();
        int currentLine = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("MVC/model/productos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Si alcanzamos la línea que necesitamos leer
                if (currentLine == numlinea) {
                    int datoIndex = 0;
                    for (char c : linea.toCharArray()) {
                        // Si encontramos un espacio, agregamos lo construido al arreglo
                        if (c == ' ') {
                            datos[datoIndex] = sb.toString();
                            sb.setLength(0); // Reiniciar el StringBuilder
                            datoIndex++;
                        } else {
                            sb.append(c); // Seguir construyendo el String
                        }
                    }
                    // Agregamos el último fragmento (la URL)
                    datos[datoIndex] = sb.toString();
                    break; // Ya procesamos la línea que nos interesaba
                }
                currentLine++; // Seguimos avanzando por las líneas del archivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Procesamos los datos para crear el Producto
        int totalVotos = Integer.parseInt(datos[0]);
        String nombre = datos[1];
        String imageURL = datos[2];

        return new Producto(nombre, totalVotos, imageURL);
    }

    // Función para modificar los votos en el archivo
    public void addVotosArchivo(int totalVotos, String producto) {
        List<String> lineasArchivo = new ArrayList<>();
        String rutaArchivo = "MVC/model/productos.txt";
        // Leer el archivo y guardar sus líneas en una lista
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Si la línea contiene el producto, actualiza los votos
                if (linea.contains(producto)) {
                    String[] partes = linea.split(" ", 3); // Divide en 3 partes: número, producto, url
                    partes[0] = String.valueOf(totalVotos); // Actualiza el número de votos
                    linea = String.join(" ", partes); // Recompone la línea con los nuevos votos
                }
                lineasArchivo.add(linea); // Guarda la línea (modificada o no) en la lista
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escribir nuevamente el archivo con los cambios
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineasArchivo) {
                bw.write(linea);
                bw.newLine(); // Agrega una nueva línea después de cada línea escrita
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}
