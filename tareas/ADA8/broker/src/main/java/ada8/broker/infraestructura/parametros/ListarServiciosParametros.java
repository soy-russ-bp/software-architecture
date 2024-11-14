package ada8.broker.infraestructura.parametros;

import ada8.broker.dominio.broker.Parametros;

public class ListarServiciosParametros implements Parametros {

    private String palabra;

    public ListarServiciosParametros(String palabra) {
        this.palabra = palabra;
    }

    public String getPalabra() {
        return palabra;
    }
}
