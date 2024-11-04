package ada8.servidor.infraestructura.parametros;

import java.time.LocalDateTime;
import ada8.servidor.dominio.Parametros;

/**
 * El evento que será registrado en la bitácora y la fecha-hora en que
 * se llevó a cabo la acción
 */
public class ParametrosRegistrarEvento implements Parametros {

    private String evento;
    private LocalDateTime fechaHora;

    public ParametrosRegistrarEvento(String evento, LocalDateTime fechaHora) {
        this.evento = evento;
        this.fechaHora = fechaHora;
    }

    public String getEvento() {
        return evento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
