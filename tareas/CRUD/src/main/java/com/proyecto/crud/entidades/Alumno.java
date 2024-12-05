package com.proyecto.crud.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Tipo Alumno.
 * Modelo de la aplicacion, contiene un identificador (id) generado automaticamente, nombre y matricula
 */
@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long matricula;

    /**
     * Instancea un nuevo alumno vacio.
     */
    public Alumno() {}

    /**
     * Obtiene id.
     *
     * @return Identificador del alumno
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia id.
     *
     * @param id Nuevo id del alumno
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene nombre.
     *
     * @return El nombre nombre del alumno
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia nombre del alumno.
     *
     * @param nombre Nuevo nombre del alumno
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene matricula.
     *
     * @return La matricula del alumno
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * Cambia matricula del alumno.
     *
     * @param matricula Nueva matricula del alumno
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
