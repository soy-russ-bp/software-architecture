package com.ada8.broker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ada8.broker.dominio.broker.Broker;
import com.ada8.broker.infraestructura.BrokerImpl;
import com.ada8.broker.infraestructura.ClientHandler;

public class Main {

    private static ExecutorService picinaHilos = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        Broker broker = new BrokerImpl();

        try (ServerSocket serverSocket = new ServerSocket(broker.getPuerto())) {
            System.out.println("Servidor iniciado y esperando clientes...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, broker);
                picinaHilos.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
