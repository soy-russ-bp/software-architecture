package model;

import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private List<Producto> productos;

    Producto agua= new Producto("agua", 0, "https://www.google.com");
    Producto galletas= new Producto("galletas", 0, "https://www.google.com");
    Producto papas= new Producto("papas", 0, "https://www.google.com");

    public ListaProductos(){
        this.productos.add(agua);
        this.productos.add(galletas);
        this.productos.add(papas);
    }
    
    //identifica por qué productos se vota y aumenta el total de votos de ese producto
    public void votarProducto(String nombre){
        for(Producto producto: this.productos){
            if( nombre.equals(producto.getNombre()) ){
                producto.setTotalVotos(producto.getTotalVotos()+1);
            }
        }
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
