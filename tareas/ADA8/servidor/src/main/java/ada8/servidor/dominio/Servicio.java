package ada8.servidor.dominio;

import ada8.utilidades.Mensaje;

public abstract class Servicio {
    private int identificador;
    private String archivoUrl; // ? TODO: no setoy seguro de que esta propiedad le pertenezca a la clase
                               // servicio

    public abstract Mensaje ejecutar(Parametros parametros);
}
