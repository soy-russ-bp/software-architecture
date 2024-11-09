package com.example.ada7_base.data_model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.ada7_base.Cliente;

import utils.Mensaje;
import utils.MensajeTipo;
import utils.Variable;

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
        solicitud.setServicio("registrar");
        solicitud.setNumeroVariables(2);
        solicitud.addVariable(new Variable("variable1", "evento"));
        solicitud.addVariable(new Variable("valor1", "Se registro un voto para " + nombre));
        solicitud.addVariable(new Variable("variable2", "fecha"));
        String fecha = LocalDateTime.now().toString();
        solicitud.addVariable(new Variable("valor2", fecha));
        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);
        
        for (int i = 1; i <= respuesta.getNumeroVariables(); i++) {
            totalEventos = Integer.parseInt(respuesta.getVariable(i - 1).getValor());
        }
    }
    
    public void listarRegistros() throws IOException {
        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);
        solicitud.setServicio("listar");
        solicitud.setNumeroVariables(0);
        Mensaje respuesta = Cliente.enviarSolicitud(solicitud);
        for (int i = 1; i <= respuesta.getNumeroVariables(); i++) {
            String registro = respuesta.getVariable(i - 1).getValor();
            registros.add(registro);
        }
    }
    
}
