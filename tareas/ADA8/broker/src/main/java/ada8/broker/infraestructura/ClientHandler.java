package ada8.broker.infraestructura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ada8.broker.dominio.broker.Broker;
import ada8.broker.dominio.broker.Servicio;
import ada8.broker.dominio.mensaje.Mensaje;

public class ClientHandler implements Runnable {

    private Socket socketCliente;
    private Broker broker;

    public ClientHandler(Socket SocketCliente, Broker broker) {
        this.socketCliente = SocketCliente;
        this.broker = broker;
    }

    @Override
    public void run() {

        // toda la logica donde se decide que servicio ejecutar

        Gson gson = new Gson();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje JSON recibido: " + inputLine);

                // Usar TypeToken para deserializar correctamente un ArrayList de Mensaje
                Type listType = new TypeToken<ArrayList<Mensaje>>() {
                }.getType();

                
                ArrayList<Mensaje> mensajes = gson.fromJson(inputLine, listType); // tarea delegada al mapper

                for (Mensaje mensaje : mensajes) {
                    System.out.println("Mensaje del cliente: " + mensaje.getContenido());
                }

                // Crea una respuesta como ArrayList
                ArrayList<Mensaje> respuesta = new ArrayList<>();
                

                String respuestaJSON = gson.toJson(respuesta);
                out.println(respuestaJSON);

                // código copiado e incrustado
                // if (mensajes.stream().anyMatch(m -> "salir".equalsIgnoreCase(m.getContenido()))) {
                //     System.out.println("Cliente desconectado: " + socketCliente.getInetAddress());
                //     break;
                // }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    private Mensaje registrarServicio(String ip, int puerto, String nombreServicio, int numeroVariables) {

        broker.addServicio(new Servicio());
        return new Mensaje();
    }

    private Mensaje listarServicios(String palabraClave) {

        return new Mensaje();
    }

    private Mensaje ejecutarServicio(String nombreServicio) {
        // buscar servicio

        return new Mensaje();
    }

}
