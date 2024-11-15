package ada8.servidor.infraestructura.servicios;

import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.infraestructura.parametros.ParametrosVotar;
import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeTipo;
import ada8.comun.utilidades.Variable;

import java.util.HashMap;
/*
    Servicio de votacion soporta hacer uno o mas votos por un solo producto
 */
public class ServicioVotar extends Servicio {
    private final String rutaArchivo = "servidor/data/Productos.txt";
    public ServicioVotar() {this.nombre = "votar";}

    @Override
    public Mensaje ejecutar(Parametros parametros) {
        ParametrosVotar parametrosVotar = (ParametrosVotar) parametros;
        String producto = parametrosVotar.getNombreProducto();
        int votosPorAgregar = parametrosVotar.getNumeroVotos();

        HashMap<String,String> votosProductos = baseDatos.leerBaseDatos(rutaArchivo);
        
        boolean existeElProducto = votosProductos.containsKey(producto);
        if (existeElProducto) {
            //obtiene la cuenta de votos
            String numeroVotos = votosProductos.get(producto);
            //sumar los votos
            int numeroVotosNuevo = 0;
            try {
                numeroVotosNuevo = Integer.parseInt(numeroVotos);
                numeroVotosNuevo += votosPorAgregar;
            }
            catch (NumberFormatException e) {System.out.println("El string no contiene un número válido");}
            numeroVotos = numeroVotosNuevo + "";

            //establecer los votos
            votosProductos.put(producto, numeroVotos);
            baseDatos.actualizarBaseDatos(rutaArchivo, votosProductos);


        }else{
            baseDatos.agregarDato(rutaArchivo, producto + "," + votosPorAgregar);
            votosProductos.put(producto,votosPorAgregar+"");
        }
        //generar respuesta
        Mensaje respuesta = new Mensaje(MensajeTipo.RESPUESTA);
        respuesta.setServicio(nombre);
        respuesta.setNumeroVariables(1);
        Variable votosAcumulados = new Variable(producto,votosProductos.get(producto));
        respuesta.addVariable(votosAcumulados);
        return respuesta;
    }

    @Override
    public int getNumeroDeParametros() {
        return 1;
    }

    

}
