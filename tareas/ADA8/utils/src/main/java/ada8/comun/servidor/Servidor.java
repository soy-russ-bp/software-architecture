package ada8.comun.servidor;



import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import ada8.comun.utilidades.Mensaje;

public abstract class Servidor extends Thread {

    private int puertoServidor;
    private int maxClientes = 0;
    private ExecutorService hilos = Executors.newFixedThreadPool(maxClientes == 0 ? 10 : maxClientes); // Soporta hasta 5 clientes simultáneos

    public abstract Mensaje procesarMensaje(Mensaje mensaje);

    @Override
    public void run() {
        try (ServerSocket socketServidor = new ServerSocket(puertoServidor)) {
            System.out.println("Servidor iniciado y esperando clientes...");

            while (true) {
                Socket socketCliente = socketServidor.accept(); // Espera por clientes
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

                // Crea un nuevo hilo para manejar cada cliente
                ClienteGestor clientHandler = new ClienteGestor(socketCliente, this);
                hilos.execute(clientHandler);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public int getPuertoServidor() {
        return puertoServidor;
    }

    public void setPuertoServidor(int puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    public int getMaxClientes() {
        return maxClientes;
    }

    public void setMaxClientes(int maxClientes) {
        this.maxClientes = maxClientes;
    }
}