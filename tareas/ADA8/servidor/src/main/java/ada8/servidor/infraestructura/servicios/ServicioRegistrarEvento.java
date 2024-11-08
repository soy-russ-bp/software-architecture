package ada8.servidor.infraestructura.servicios;

import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.infraestructura.parametros.ParametrosRegistrarEvento;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeTipo;
import ada8.utilidades.Variable;

import java.time.LocalDateTime;

public class ServicioRegistrarEvento extends Servicio{
    private String rutaArchivo = "Registros.txt";
    public ServicioRegistrarEvento() {
        this.nombre = "registrar";
    }

    @Override
    public Mensaje ejecutar(Parametros parametros) {
        ParametrosRegistrarEvento parametrosRegistrarEvento = (ParametrosRegistrarEvento) parametros;
        String evento = parametrosRegistrarEvento.getEvento();
        LocalDateTime fechaHora = parametrosRegistrarEvento.getFechaHora();

        //registrar evento
        baseDatos.agregarDato(rutaArchivo,fechaHora.toString() + "," + evento);

        //generar mensaje
        Mensaje respuesta = new Mensaje(MensajeTipo.RESPUESTA);
        respuesta.setServicio(nombre);
        respuesta.setNumeroVariables(1);
        String numeroRegistrosAcumulados = baseDatos.dimension(rutaArchivo) + "";
        Variable registrosAcumulados = new Variable("eventos",numeroRegistrosAcumulados);
        respuesta.addVariable(registrosAcumulados);
        return respuesta;
    }
    
}
