package ada8.servidor.dominio;

import java.util.ArrayList;

public abstract class Servidor {
    private ArrayList<Servicio> servicios;

    
    abstract public int getPuerto();

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
