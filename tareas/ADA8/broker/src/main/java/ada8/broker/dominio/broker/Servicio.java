package ada8.broker.dominio.broker;

import ada8.comun.utilidades.Mensaje;
import ada8.comun.utilidades.MensajeTipo;

public class Servicio {

    private String ipServidor;
    private int puertoServidor;
    private String nombreServicio;
    private int numeroParametros;
    private int identificador;

    public Servicio(String ipServidor, int puertoServidor, String nombreServicio, int numeroParametros) {
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        this.nombreServicio = nombreServicio;
        this.numeroParametros = numeroParametros;
    }

    public Mensaje ejecutar(Mensaje mensaje) {

        // hacer la solicitud 
        // convertir json de respuesta en objeto mensaje
        
        // crear un nuevo Objeto mensaje y traducirlo la respuesta del servicio

        // devolver respuesta 
        
        return new Mensaje(MensajeTipo.PETICION);

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
