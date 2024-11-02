package ada8.broker.dominio.broker;

import java.util.ArrayList;

public abstract class Broker {

    private int puerto = 1234;

    public int getPuerto() {
        return puerto;
    }

    private ArrayList<Servicio> servicios;

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void addServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

    public abstract Servicio buscarServicioRegistrado(String nombreServicio);


}