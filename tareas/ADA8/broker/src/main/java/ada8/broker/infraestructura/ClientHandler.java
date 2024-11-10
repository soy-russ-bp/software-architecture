package ada8.broker.infraestructura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import com.ada8.broker.dominio.broker.Broker;
import com.ada8.broker.dominio.broker.Servicio;
import com.ada8.broker.dominio.mensaje.Mensaje;
import com.ada8.broker.dominio.mensaje.Variable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ada8.broker.dominio.broker.Broker;
import ada8.broker.dominio.broker.Servicio;
import ada8.utilidades.Mensaje;
import ada8.utilidades.MensajeTipo;

public class ClientHandler implements Runnable {

    private Socket socketCliente;
    private Broker broker;

    public ClientHandler(Socket SocketCliente, Broker broker) {
        this.socketCliente = SocketCliente;
        this.broker = broker;
    }

    @Override
    public void run() {
        // toda la logica donde se decide que servicio ejecutar se debe hacer en el metodo run

        Gson gson = new Gson();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true)) {

            String inputLine; // lee cada linea del mensaje

            while ((inputLine = in.readLine()) != null) {
                //recibe la peticion del cliente y la convierte en un objeto mensaje
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

        Servicio servicio = new Servicio(ip, puerto, nombreServicio, numeroVariables);
        
        broker.addServicio(servicio);

        // identificador del servicio
        int identificador = broker.getServicios().size()+1;
        servicio.setIdentificador(identificador);
    
        // Crear un mensaje de respuesta para el cliente con el identificador del servicio
        Mensaje mensajeConfirmacion = new Mensaje();
        mensajeConfirmacion.setServicio("registrar");
        mensajeConfirmacion.setNumeroVariables(1);
        mensajeConfirmacion.setContenido(new ArrayList<>() {
            {add(new Variable("respuesta1", "identificador"));}
            {add(new Variable("valor1", String.valueOf(identificador)));}
        });
    
        return mensajeConfirmacion;
    }
    
    private Mensaje listarServicios(String nombreServicio) {

        Mensaje mensajeRespuesta = new Mensaje();
        mensajeRespuesta.setServicio("listar");

        int numeroVariables = 0;
        List<Variable> contenido = new ArrayList<>();

        if(nombreServicio != null){
            //buscar servicios que contengan la palabra clave
            for (Servicio servicio : broker.getServicios()) {
                if(servicio.getNombreServicio().contains(nombreServicio)){
                    numeroVariables++;
                    contenido.add(new Variable("respuesta"+numeroVariables, servicio.getNombreServicio()));
                    contenido.add(new Variable("valor"+numeroVariables, servicio.getIpServidor() + ":" + servicio.getPuertoServidor()));
                }
            }
        }else{
            // listar todos los servicios  
            for (Servicio servicio : broker.getServicios()) {
                numeroVariables++;
                contenido.add(new Variable("respuesta"+numeroVariables, servicio.getNombreServicio()));
                contenido.add(new Variable("valor"+numeroVariables, servicio.getIpServidor() + ":" + servicio.getPuertoServidor()));
            } 
        }
        mensajeRespuesta.setNumeroVariables(numeroVariables);

        return new Mensaje(MensajeTipo.PETICION);
    }

    private Mensaje ejecutarServicio(String nombreServicio) {
        /*
         Ejemplo de solicitud:
        {
        "servicio" : "ejecutar",
        "variables" : 2,
        "variable1" : "servicio",
        "valor1" : "votar",
        "variable2" : "Windows",
        "valor2" : "1"
        }
         */

        /*
         Ejemplo de respuesta:
        {
        "servicio" : "ejecutar",
        "respuestas" : 2,
        "respuesta1" : "servicio",
        "valor1" : "votar",
        "respuesta2" : "Windows",
        "valor2" : 21
        }
         */
        // buscar servicio
        Mensaje m= new Mensaje();
        Servicio servicio = broker.buscarServicioRegistrado(nombreServicio);
        if (servicio != null) {
            // ejecutar servicio
            servicio.ejecutar(m);
        } else {
            // servicio no encontrado
            m.setServicio("ejecutar");
            m.setNumeroVariables(1);
            m.setContenido(new ArrayList<>() {
                {add(new Variable("respuesta1", "error"));}
                {add(new Variable("valor1", "Servicio no encontrado"));}
            });
        }
        return m;

    }

}
