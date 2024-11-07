package ada8.servidor.infraestructura.servicios;

import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeTipo;
import ada8.utilidades.Variable;

import java.util.HashMap;

public class ServicioContarVotos extends Servicio {
    private final String rutaArchivo = "Productos.txt";

    public ServicioContarVotos() {
        this.nombre = "contar";
    }

    @Override
    public Mensaje ejecutar(Parametros parametros) {
        Mensaje respuesta = new Mensaje(MensajeTipo.RESPUESTA);
        respuesta.setServicio("contar");

        //lee los productos y sus votos
        HashMap<String,String> VotosProductos = (HashMap<String, String>) baseDatos.leerBaseDatos(rutaArchivo);

        //escribe el cuerpo del mensaje
        respuesta.setNumeroVariables(VotosProductos.size());
        VotosProductos.forEach((nombre, votos)->{
            Variable variable = new Variable(nombre,votos.toString());
            respuesta.addVariable(variable);
        });

        return respuesta;
    }
}