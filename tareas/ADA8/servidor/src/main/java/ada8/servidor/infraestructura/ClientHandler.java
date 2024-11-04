package ada8.servidor.infraestructura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

import ada8.servidor.dominio.Parametros;
import ada8.servidor.dominio.Servicio;
import ada8.servidor.dominio.Servidor;
import ada8.servidor.infraestructura.parametros.ParametrosRegistrarEvento;
import ada8.servidor.infraestructura.parametros.ParametrosVotar;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeMapeador;

public class ClientHandler implements Runnable {

    private Socket socketCliente;
    private final Servidor SERVIDOR;

    public ClientHandler(Socket SocketCliente, Servidor servidor) {
        this.socketCliente = SocketCliente;
        this.SERVIDOR = servidor;

    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true)) {

            // Capturar mensaje del cliente
            String peticionJson = "";
            do {
                peticionJson += in.readLine();
            } while (in.ready());
            // Procesar la respuesta
            Mensaje peticionMensaje = MensajeMapeador.deJsonAObjeto(peticionJson);
            Servicio servicio = SERVIDOR.buscarServicioPorNombre(peticionMensaje.getServicio());
            Parametros parametros = getParametros(peticionMensaje);
            Mensaje respuestaMensaje = servicio.ejecutar(parametros);

            // Responder al cliente
            out.println(MensajeMapeador.deObjetoAJson(respuestaMensaje));

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

    private Parametros getParametros(Mensaje mensaje) {

        if (mensaje.getServicio().equals("votar")) {
            String nombreProducto = mensaje.getVariable(0).getNombre();
            int numeroVotos = Integer.parseInt(mensaje.getVariable(0).getValor());
            return new ParametrosVotar(nombreProducto, numeroVotos);

        } else if (mensaje.getServicio().equals("registrar")) {
            String nombreEvento = mensaje.getVariable(0).getNombre();
            LocalDateTime fechaHora = LocalDateTime.parse(mensaje.getVariable(0).getValor());
            return new ParametrosRegistrarEvento(nombreEvento, fechaHora);
        } else {
            // para el caso de listar y contar que no requieren parametros
            return null;
        }
    }
}
