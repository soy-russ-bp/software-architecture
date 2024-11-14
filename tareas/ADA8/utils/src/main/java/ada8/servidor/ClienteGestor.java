package ada8.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeMapeador;

class ClienteGestor implements Runnable {

    private Socket clientSocket;
    private Servidor servidor;

    public ClienteGestor(Socket socketCliente, Servidor servidor) {
        this.clientSocket = socketCliente;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String mensajeClienteJson = "";
            do {
                mensajeClienteJson += in.readLine();
            } while (in.ready());

            Mensaje mensajeCliente = MensajeMapeador.deJsonAObjeto(mensajeClienteJson);
            Mensaje mensajeRespuesta = servidor.procesarMensaje(mensajeCliente);

            out.println(MensajeMapeador.deObjetoAJson(mensajeRespuesta));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}