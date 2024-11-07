package ada8.servidor.infraestructura.servicios;

public interface BaseDatos {
    public Object leerBaseDatos(String url);
    public void actualizarBaseDatos(String url,Object datos);
    public void agregarDato(String url,Object dato);
}
