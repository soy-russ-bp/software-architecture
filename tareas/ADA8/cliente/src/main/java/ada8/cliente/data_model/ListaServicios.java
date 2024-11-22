package ada8.cliente.data_model;

import java.io.IOException;
import java.util.ArrayList;

import ada8.cliente.Cliente;

import ada8.comun.utilidades.*;;


public class ListaServicios {
    private static ArrayList<String> servicios = new ArrayList<String>();
    private static int totalServicios = 0;

    public static ArrayList<String> obtenerServicios() {
        
        return servicios;
    }
    
    public static int obtenerTotalServicios(){
        return totalServicios;
    }


    public static void listarServicios(String palabra) throws IOException {
        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);
        solicitud.setServicio("listar");
        solicitud.setNumeroVariables(1);
        solicitud.addVariable(new Variable("palabra", palabra));

        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);
        
        ListaServicios.totalServicios = respuesta.getNumeroVariables();
        for (int i = 1; i <= respuesta.getNumeroVariables(); i++) {
            String registro = respuesta.getVariable(i - 1).getNombre();
            String fecha = respuesta.getVariable(i - 1).getValor();
            servicios.add(registro + " - " + fecha); 
        }
    }
    
}
