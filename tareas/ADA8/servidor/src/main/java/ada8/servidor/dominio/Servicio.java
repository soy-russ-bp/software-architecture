package ada8.servidor.dominio;

import ada8.utilidades.Mensaje;

public abstract class Servicio {
    protected String nombre;


    public String getNombre() {
        return nombre;
    }

    private int identificador;
    private String archivoUrl; // ? TODO: no setoy seguro de que esta propiedad le pertenezca a la clase
                               // servicio

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public abstract Mensaje ejecutar(Parametros parametros);
}
