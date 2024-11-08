package ada8.servidor.infraestructura.servicios;

import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.infraestructura.parametros.ParametrosListarEventos;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeTipo;
import ada8.utilidades.Variable;

import java.util.HashMap;

public class ServicioListarEventos extends Servicio {
    private String rutaArchivo = "Registros.txt";
    public ServicioListarEventos() {
        this.nombre = "listar";
    }

    @Override
    public Mensaje ejecutar(Parametros parametros) {
        Mensaje respuesta = new Mensaje(MensajeTipo.RESPUESTA);
        respuesta.setServicio(nombre);

        //lee los registros y sus fechas
        HashMap<String,String> eventosRegistros = baseDatos.leerBaseDatos(rutaArchivo);

        //escribe el cuerpo del mensaje
        respuesta.setNumeroVariables(eventosRegistros.size());
        eventosRegistros.forEach((registro,fechaRegistro)->{
            Variable variable = new Variable(registro,fechaRegistro);
            respuesta.addVariable(variable);
        });

        return respuesta;
    }
    
}
