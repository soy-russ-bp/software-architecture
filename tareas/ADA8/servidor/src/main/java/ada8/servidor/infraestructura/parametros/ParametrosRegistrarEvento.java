package ada8.servidor.infraestructura.parametros;

import ada8.servidor.dominio.Parametros;

/**
 * El evento que será registrado en la bitácora y la fecha-hora en que
 * se llevó a cabo la acción
 */
public class ParametrosRegistrarEvento implements Parametros {

    private String evento;
    private String fechaHora;

    public ParametrosRegistrarEvento(String evento, String fechaHora) {
        this.evento = evento;
        this.fechaHora = fechaHora;
    }

    public String getEvento() {
        return evento;
    }

    public String getFechaHora() {
        return fechaHora;
    }
}
