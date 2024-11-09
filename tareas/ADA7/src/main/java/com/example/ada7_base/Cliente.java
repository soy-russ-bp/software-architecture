package com.example.ada7_base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import utils.Mensaje;
import utils.MensajeMapeador;
import utils.MensajeTipo;
import utils.Variable;

public class Cliente {
    private static Socket conexionCliente;
    private static PrintWriter mensajeCliente;
    private static BufferedReader respuestaServidor;
    private static Map<String, Integer> serviciosRegistrados;

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

    public static void registrarServicios(String direccionIP, int puerto) throws IOException {
        Mensaje msjContar = generarMensajeRegistrarServicio(direccionIP, puerto, "contar", "0");
        Mensaje msjVotar = generarMensajeRegistrarServicio(direccionIP, puerto, "votar", "1");
        Mensaje msjRegistrar = generarMensajeRegistrarServicio(direccionIP, puerto, "registrar", "2");
        Mensaje msjListar = generarMensajeRegistrarServicio(direccionIP, puerto, "listar", "0");

        rellenarDiccionarioServicios("contar", msjContar);
        rellenarDiccionarioServicios("votar", msjVotar);
        rellenarDiccionarioServicios("registrar", msjRegistrar);
        rellenarDiccionarioServicios("listar", msjListar);
    }

    private static void rellenarDiccionarioServicios(String servicio, Mensaje msjServicio) throws IOException {
        Mensaje respuestaBroker = enviarSolicitud(msjServicio);
        //Obtener valor1 del json
        String idSinFormato = respuestaBroker.getVariable(1).getValor();
        int idContar = Integer.parseInt(idSinFormato);
        serviciosRegistrados.put(servicio, idContar);
    }

    private static Mensaje generarMensajeRegistrarServicio(String servidorIP, int puerto, String servicio, String numParametos) {
        Mensaje mensaje = new Mensaje(MensajeTipo.PETICION);
        mensaje.setServicio("registrar");
        mensaje.setNumeroVariables(4);
        mensaje.addVariable(new Variable("variable1", "servidor"));
        mensaje.addVariable(new Variable("valor1", servidorIP));
        mensaje.addVariable(new Variable("variable2", "puerto"));
        mensaje.addVariable(new Variable("valor2", Integer.toString(puerto)));
        mensaje.addVariable(new Variable("servicio3", "servicio"));
        mensaje.addVariable(new Variable("valor3", servicio));
        mensaje.addVariable(new Variable("variable4", "parametros"));
        mensaje.addVariable(new Variable("valor4", numParametos));

        return mensaje;
    }
}