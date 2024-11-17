package ada8.comun.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeMapeador;

public class Cliente {

    private String ipServidor;
    private int puertoServidor;

    public String getIpServidor() {
        return ipServidor;
    }

    public int getPuertoServidor() {
        return puertoServidor;
    }

    public Cliente(String ipServidor, int puertoServidor) {
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
    }

    public Mensaje enviarMensaje(Mensaje mensaje) {
        try (Socket socket = new Socket(ipServidor, puertoServidor);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

            String mensajeClienteJson = MensajeMapeador.deObjetoAJson(mensaje);
            out.println(mensajeClienteJson);

            String mensajeServidorJson = "";
            do {
                mensajeServidorJson += in.readLine();
            } while (in.ready());

            return MensajeMapeador.deJsonAObjeto(mensajeServidorJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
