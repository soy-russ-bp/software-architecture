package ada8.utils.mapper;

import java.util.ArrayList;

public class Mensaje {
    private MensajeTipo mensajeTipo;

    public MensajeTipo getMensajeTipo() {
        return mensajeTipo;
    }

    public void setMensajeTipo(MensajeTipo mensajeTipo) {
        this.mensajeTipo = mensajeTipo;
    }

    private String servicio = "";
    private int numeroVariables = 0;
    private ArrayList<Variable> contenido;

    public Mensaje(MensajeTipo mensajeTipo) {
        this.contenido = new ArrayList<Variable>();
        this.mensajeTipo = mensajeTipo;
    }

    public void addVariable(Variable variable) {
        this.contenido.add(variable);
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getNumeroVariables() {
        return numeroVariables;
    }

    public void setNumeroVariables(int numeroVariables) {
        this.numeroVariables = numeroVariables;
    }

    public ArrayList<Variable> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<Variable> contenido) {
        this.contenido = contenido;
    }

    public Variable getVariable(int i) {
        return this.contenido.get(i);
    }

    @Override
    public String toString() {
        return "Mensaje [tipo = " + mensajeTipo + "] [contenido=" + contenido + ", numeroVariables=" + numeroVariables
                + ", servicio=" + servicio
                + "]";
    }

}
