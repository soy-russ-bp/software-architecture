package ada8.cliente.data_model;

public class Producto {
    private String nombre;
    private int totalVotos;
    private String imagenUrl;

    public Producto(String nombre, int totalVotos, String imagenUrl) {
        this.nombre = nombre;
        this.totalVotos = totalVotos;
        this.imagenUrl = imagenUrl;
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
}