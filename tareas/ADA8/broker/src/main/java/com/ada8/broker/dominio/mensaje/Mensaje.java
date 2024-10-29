package com.ada8.broker.dominio.mensaje;

import java.util.ArrayList;

public class Mensaje {
    private String servicio;
    private int numeroVariables;
    private ArrayList<Variable> contenido;

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

}
