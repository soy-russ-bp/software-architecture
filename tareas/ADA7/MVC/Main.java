import model.ListaProductos;

public class Main {
    public static void main(String[] args) {

        ListaProductos productos = new ListaProductos();

        productos.votarProducto("agua");
        productos.votarProducto("galletas");
        productos.votarProducto("papas");
        productos.votarProducto("papas");
        
    }
}
