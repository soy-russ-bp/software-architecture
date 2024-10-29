package com.ada8.broker.dominio.broker;

import com.ada8.broker.dominio.mensaje.Mensaje;

public class Servicio {

    private String ipServidor;
    private int puertoServidor;
    private String nombreServicio;
    private int numeroParametros;
    private int identificador;

    public Mensaje ejecutar(Mensaje mensaje) {

        // hacer la solicitud 
        // convertir json de respuesta en objeto mensaje
        
        // crear un nuevo Objeto mensaje y traducirlo la respuesta del servicio

        // devolver respuesta 
        
        return new Mensaje();

    }

    public String getIpServidor() {
        return ipServidor;
    }

    public int getPuertoServidor() {
        return puertoServidor;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public int getNumeroParametros() {
        return numeroParametros;
    }

    public int getIdentificador() {
        return identificador;
    }

    


}
