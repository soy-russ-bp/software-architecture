package com.proyecto.crud.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.crud.entidades.Alumno;


/**
 * La Interfaz AlumnoRespositorio proporciona el comportamiento necesario para implementar el componente
 * mapeador de alumnos a la base de datos. Alumno Repositorio hereda de "JPARepository" para extender los comportamientos (CRUD)
 * a la entiddad "Alumno".
 */
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long>{
}
