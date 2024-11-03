package ada8.servidor.infraestructura;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import ada8.servidor.dominio.Servicio;
import ada8.servidor.dominio.Servidor;
import ada8.servidor.infraestructura.servicios.*;

public class ServidorImpl extends Servidor {

    private int puerto = 8787;

    private final int PUERTO_BROKER = 8787;
    private final String IP_BROKER = "";


    public ServidorImpl() {
        setServicios(
                new ArrayList<ada8.servidor.dominio.Servicio>() {
                    {
                        add(new ServicioContarVotos());
                        add(new ServicioListarEventos());
                        add(new ServicioRegistrarEvento());
                        add(new ServicioVotar());
                    }
                });

    }

    @Override
    public void registrarServiciosAlBroker() {



    }

    @Override
    public int getPuerto() {
        return puerto;
    }

}
