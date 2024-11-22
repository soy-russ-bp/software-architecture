package ada8.cliente.data_model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ada8.cliente.Cliente;

import ada8.comun.utilidades.*;;


public class ListaRegistros {
    private static ArrayList<String> registros = new ArrayList<String>();
    private static int totalEventos = 0;

    public static ArrayList<String> obtenerRegistros() {
        
        return registros;
    }
    
    public static int obtenerTotalEventos(){
        return totalEventos;
    }

    public static void registrarEvento(String nombre) throws IOException {
        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);
        solicitud.setServicio("ejecutar");
        solicitud.setNumeroVariables(3);
        solicitud.addVariable(new Variable("servicio", "registrar"));
        solicitud.addVariable(new Variable("evento", "Se registro un voto para " + nombre));
        
        String fecha = LocalDateTime.now().toString();
        solicitud.addVariable(new Variable("fecha", fecha));
        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);
        
        for (int i = 1; i <= respuesta.getNumeroVariables(); i++) {
            totalEventos = Integer.parseInt(respuesta.getVariable(i - 1).getValor());
        }
    }
    
    public void listarRegistros() throws IOException {
        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);
        solicitud.setServicio("ejecutar");
        solicitud.setNumeroVariables(1);
        solicitud.addVariable(new Variable("servicio", "listar"));

        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);
        
        for (int i = 1; i <= respuesta.getNumeroVariables(); i++) {
            String registro = respuesta.getVariable(i - 1).getNombre();
            String fecha = respuesta.getVariable(i - 1).getValor();
            registros.add(registro + " " + fecha); 
        }
    }
    
}
