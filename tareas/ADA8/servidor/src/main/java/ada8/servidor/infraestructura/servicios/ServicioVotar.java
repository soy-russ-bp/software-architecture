package ada8.servidor.infraestructura.servicios;

import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.infraestructura.parametros.ParametrosVotar;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeTipo;

public class ServicioVotar extends Servicio {
    private final String rutaArchivo = "Productos.txt";

    public ServicioVotar() {
        this.nombre = "votar";
    }

    @Override
    public Mensaje ejecutar(Parametros parametros) {
        ParametrosVotar parametrosVotar = (ParametrosVotar) parametros;
        String producto = parametrosVotar.getNombreProducto();
        int votos = parametrosVotar.getNumeroVotos();

        Mensaje respuesta = new Mensaje(MensajeTipo.RESPUESTA);
        respuesta.setServicio("votar");
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }

    

}
