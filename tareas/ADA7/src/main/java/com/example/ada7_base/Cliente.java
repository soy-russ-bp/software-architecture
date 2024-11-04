package com.example.ada7_base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import utils.Mensaje;
import utils.MensajeMapeador;

public class Cliente {
    private static Socket conexionCliente;
    private static PrintWriter mensajeCliente;
    private static BufferedReader respuestaServidor;

    public static void iniciarConexion(String direccionIP, int puerto) throws UnknownHostException, IOException {
        conexionCliente = new Socket(direccionIP, puerto);
        mensajeCliente = new PrintWriter(conexionCliente.getOutputStream(), true);
        respuestaServidor = new BufferedReader(new InputStreamReader(conexionCliente.getInputStream()));
    }
    
    //Debe devolver un json
    public static Mensaje enviarSolicitud(Mensaje mensajeSolicitud) throws IOException {
        mensajeCliente.println(MensajeMapeador.deObjetoAJson(mensajeSolicitud));
        String respuestaJson = respuestaServidor.readLine();
        Mensaje mensajeRespuesta = MensajeMapeador.deJsonAObjeto(respuestaJson);
        return mensajeRespuesta;
    }
    
    public void stopConnection() throws IOException {
        respuestaServidor.close();
        mensajeCliente.close();
        conexionCliente.close();
    }
}

