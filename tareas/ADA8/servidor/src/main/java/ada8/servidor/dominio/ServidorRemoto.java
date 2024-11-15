package ada8.servidor.dominio;

import java.util.ArrayList;

import ada8.comun.servidor.Servidor;

public abstract class ServidorRemoto extends Servidor {
    private ArrayList<Servicio> servicios;

    private String ipBroker;
    private int puertoBroker;


    public String getIpBroker() {
        return ipBroker;
    }

    public void setIpBroker(String ipBroker) {
        this.ipBroker = ipBroker;
    }

    public int getPuertoBroker() {
        return puertoBroker;
    }

    public void setPuertoBroker(int puertoBroker) {
        this.puertoBroker = puertoBroker;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public abstract void registrarServiciosAlBroker();

    public Servicio buscarServicioPorNombre(String nombre) {
        for (Servicio servicio : servicios) {
            if (servicio.getNombre().equals(nombre)) {
                return servicio;
            }
        }
        return null;
    }

}
