package ada8.broker.infraestructura;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ada8.broker.dominio.broker.*;

import java.io.IOException;
import java.net.*;

public class BrokerImpl extends Broker {

    private int PUERTO = 1234;
    

    @Override
    public Servicio buscarServicioRegistrado(String nombreServicio) {
        for (Servicio servicio : getServicios()) {
            if (servicio.getNombreServicio().equals(nombreServicio)) {
                return servicio;
            }
        }
        return null;
    }

}
