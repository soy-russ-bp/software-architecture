package ada8.broker.dominio.broker;

import java.util.ArrayList;

import ada8.comun.cliente.Cliente;
import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeTipo;
import ada8.comun.utilidades.Variable;

public class Servicio {

    private String ipServidor;
    private int puertoServidor;
    private String nombreServicio;
    private int numeroParametros;
    private int identificador;
    private Cliente cliente;

    public Servicio(String ipServidor, int puertoServidor, String nombreServicio, int numeroParametros) {
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        this.nombreServicio = nombreServicio;
        this.numeroParametros = numeroParametros;

        cliente = new Cliente(ipServidor, puertoServidor);
    }

    public Mensaje ejecutar(Mensaje mensaje) {

        Mensaje solicitud = new Mensaje(MensajeTipo.PETICION);

        solicitud.setServicio(nombreServicio);
        solicitud.setNumeroVariables(numeroParametros);

        ArrayList<Variable> contenido = new ArrayList<Variable>();

        // empieza tomar las variables de la solicitud del cliente desde la posicion 2,
        // ignorando servicio, variables y la primera variable que es la que especifica
        // el servicio a ejecutar
        // ejem:
        // {                             
        //     "servicio" : "ejecutar"       ==>    {
        //     "variables" :                 ==>        "servicio" : "votar",
        //     "variable1" : "servicio",     ==>        "variables" : 1,
        //     "valor1" : "votar"            ==>        "variable1" : "Windows",
        //     "variable2" : "Windows"       ==>        "valor1" : 1
        //     "valor2" : "1"                ==>    }
        // }                             

        for (int i = 1; i < mensaje.getContenido().size(); i++) {
            Variable variable = mensaje.getContenido().get(i);
            contenido.add(variable);
        }

        solicitud.setContenido(contenido);
        Mensaje respuesta = cliente.enviarMensaje(solicitud);
        return respuesta;

    }

    public String getIpServidor() {
        return ipServidor;
    }

    public int getPuertoServidor() {
        return puertoServidor;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public int getNumeroParametros() {
        return numeroParametros;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

}
