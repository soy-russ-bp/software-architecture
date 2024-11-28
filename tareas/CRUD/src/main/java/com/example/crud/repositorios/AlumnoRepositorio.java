package com.example.crud.repositorios;

import com.example.crud.entidades.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long>{
}
