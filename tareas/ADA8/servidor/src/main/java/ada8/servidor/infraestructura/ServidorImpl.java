package ada8.servidor.infraestructura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import ada8.servidor.App;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.dominio.Servidor;
import ada8.servidor.infraestructura.servicios.*;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeMapeador;
import ada8.utilidades.MensajeTipo;
import ada8.utilidades.Variable;

public class ServidorImpl extends Servidor {

    private int puerto = 8787;

    private final int PUERTO_BROKER = 1234;
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

    // TODO (mejorar performance): Aunque solo se usará una vez, se estaría creando
    // una conexión al broker cada que se llame a este método
    @Override
    public void registrarServiciosAlBroker() {
        try (Socket socket = new Socket(IP_BROKER, PUERTO_BROKER);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

            System.out.println("Conectado al broker, iniciando registro de servicios...");

            for (Servicio servicio : getServicios()) {
                Mensaje mensaje = new Mensaje(MensajeTipo.PETICION);

                mensaje.setServicio("registrar");
                mensaje.setNumeroVariables(4);
                ArrayList<Variable> contenido = new ArrayList<Variable>() {
                    {
                        add(new Variable("servidor", socket.getLocalAddress().toString()));
                        add(new Variable("puerto", String.valueOf(puerto)));
                        add(new Variable("servicio", servicio.getNombre()));
                        add(new Variable("parametros", String.valueOf(9999)));

                    }
                };
                mensaje.setContenido(contenido);
                String mensajeJson = MensajeMapeador.deObjetoAJson(mensaje);
                out.println(mensajeJson);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPuerto() {
        return puerto;
    }

}
