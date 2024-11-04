package ada8.servidor;

import ada8.servidor.infraestructura.ClientHandler;
import ada8.servidor.infraestructura.ServidorImpl;
import ada8.servidor.infraestructura.parametros.ParametrosContarVotos;
import ada8.servidor.infraestructura.servicios.ServicioContarVotos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ada8.servidor.dominio.*;

/**
 * Hello world!
 *
 */
public class App {
    private static ExecutorService picinaHilos = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        Servidor servidor = new ServidorImpl();

        try (ServerSocket serverSocket = new ServerSocket(servidor.getPuerto())) {
            System.out.println("Servidor iniciado y esperando clientes...");

            servidor.registrarServiciosAlBroker();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, servidor);
                picinaHilos.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
