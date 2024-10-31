package com.ada8.mapper;

import java.util.ArrayList;

public class Mensaje {
    private String servicio = "";
    private int numeroVariables = 0;
    private ArrayList<Variable> contenido;

    public Mensaje(String servicio, int numeroVariables, ArrayList<Variable> contenido) {
        this.servicio = servicio;
        this.numeroVariables = numeroVariables;
        this.contenido = contenido;
    }

    public Mensaje() {
        this.contenido = new ArrayList<Variable>();
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

    @Override
    public String toString() {
        return "Mensaje [contenido=" + contenido + ", numeroVariables=" + numeroVariables + ", servicio=" + servicio
                + "]";
    }

}
