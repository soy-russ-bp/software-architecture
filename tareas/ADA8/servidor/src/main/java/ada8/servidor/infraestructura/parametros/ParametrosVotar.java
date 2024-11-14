package ada8.servidor.infraestructura.parametros;

import ada8.servidor.dominio.Parametros;

public class ParametrosVotar implements Parametros {
    private String nombreProducto;
    private int numeroVotos;

    public ParametrosVotar(String nombreProducto, int numeroVotos) {
        this.nombreProducto = nombreProducto;
        this.numeroVotos = numeroVotos;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }
}
