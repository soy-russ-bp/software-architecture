package ada8.servidor.infraestructura.servicios;

import java.util.HashMap;
/*
    plantilla del manejo de los datos
 */
public interface BaseDatos {
    public HashMap<String,String> leerBaseDatos(String url);
    public void actualizarBaseDatos(String url, HashMap<String,String> datos);
    public void agregarDato(String url,String dato);
    public int dimension(String url);
}
