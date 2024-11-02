package ada8.servidor.dominio;

import java.util.ArrayList;

public abstract class Servidor {
    private ArrayList<Servicio> servicios;

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public abstract void registrarServiciosAlBroker();

}
