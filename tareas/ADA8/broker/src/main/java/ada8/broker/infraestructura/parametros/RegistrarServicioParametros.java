package ada8.broker.infraestructura.parametros;

import ada8.broker.dominio.broker.Parametros;

public class RegistrarServicioParametros implements Parametros {
    private String ipServidor;
    private int puertoServidor;
    private String nombreServicio;
    private int numeroParametros;

    public RegistrarServicioParametros(String ipServidor, int puertoServidor, String nombreServicio,
            int numeroParametros) {
        this.ipServidor = ipServidor;
        this.puertoServidor = puertoServidor;
        this.nombreServicio = nombreServicio;
        this.numeroParametros = numeroParametros;
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
}
