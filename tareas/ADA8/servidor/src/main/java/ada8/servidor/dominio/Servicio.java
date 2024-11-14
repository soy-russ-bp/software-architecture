package ada8.servidor.dominio;

import ada8.servidor.infraestructura.servicios.BaseDatos;
import ada8.servidor.infraestructura.servicios.BaseDatosImpl;
import ada8.utilidades.Mensaje;

public abstract class Servicio {
    protected String nombre;
    protected BaseDatos baseDatos = new BaseDatosImpl();

    public String getNombre() {
        return nombre;
    }

    private int identificador;

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public abstract Mensaje ejecutar(Parametros parametros);
}
