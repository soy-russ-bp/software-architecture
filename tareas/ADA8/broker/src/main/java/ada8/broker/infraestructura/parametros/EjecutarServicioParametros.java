package ada8.broker.infraestructura.parametros;

import java.util.ArrayList;

import ada8.broker.dominio.broker.Parametros;
import ada8.comun.utilidades.Variable;

public class EjecutarServicioParametros implements Parametros {

    private String nombreServicio;
    private ArrayList<Variable> parametros;

    public EjecutarServicioParametros(String nombreServicio, ArrayList<Variable> parametros) {
        this.nombreServicio = nombreServicio;
        this.parametros = parametros;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public ArrayList<Variable> getParametros() {
        return parametros;
    }

}
