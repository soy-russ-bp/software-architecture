package ada8.broker.dominio.broker;

import java.util.ArrayList;

import ada8.servidor.*;

public abstract class Broker extends Servidor {

    private ArrayList<Servicio> servicios = new ArrayList<Servicio>();

    public void addServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public void removeServicio(Servicio servicio) {
        servicios.remove(servicio);
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public Servicio buscarServicioRegistrado(String nombre) {

        for (Servicio servicio : servicios) {
            if (servicio.getNombreServicio().equals(nombre)) {
                return servicio;
            }
        }
        return null;
    }

    public Servicio buscarServicioRegistrado(String nombre, String ip) {

        for (Servicio servicio : servicios) {
            if (servicio.getNombreServicio().equals(nombre) && servicio.getIpServidor().equals(ip)) {
                return servicio;
            }
        }
        return null;
    }

    public Servicio buscarServicioRegistrado(int identificador) {

        for (Servicio servicio : servicios) {
            if (servicio.getIdentificador() == identificador) {
                return servicio;
            }
        }
        return null;
    }

}