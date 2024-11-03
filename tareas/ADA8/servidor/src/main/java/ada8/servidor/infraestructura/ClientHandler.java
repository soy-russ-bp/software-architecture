package ada8.servidor.infraestructura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ada8.servidor.dominio.Servicio;
import ada8.servidor.dominio.Servidor;
import ada8.servidor.infraestructura.parametros.ParametrosVotar;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeMapeador;

public class ClientHandler implements Runnable {

    private Socket socketCliente;
    private Servidor servidor;

    public ClientHandler(Socket SocketCliente, Servidor servidor) {
        this.socketCliente = SocketCliente;
        this.servidor = servidor;

    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true)) {

            // Capturar mensaje del cliente
            String mensajeJson = "";
            do {
                mensajeJson += in.readLine();
            } while (in.ready());
            // Procesar la respuesta
            Mensaje mensaje = MensajeMapeador.deJsonAObjeto(mensajeJson);

            Servicio servicio = servidor.buscarServicioPorNombre(mensaje.getServicio());

            Mensaje respuesta = servicio.ejecutar(new ParametrosVotar());

            // Responder al cliente
            out.println(MensajeMapeador.deObjetoAJson(respuesta));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
