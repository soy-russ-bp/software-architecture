import java.time.LocalDateTime;

import model.ListaProductos;

public class Main {
    public static void main(String[] args) {
        //LocalDateTime fechaHora = LocalDateTime.now();
        //System.out.println(fechaHora + " +1 voto");

        ListaProductos productos = new ListaProductos();

        productos.votarProducto("agua");
        productos.votarProducto("galletas");
        productos.votarProducto("papas");
        productos.votarProducto("papas");

    }
}
