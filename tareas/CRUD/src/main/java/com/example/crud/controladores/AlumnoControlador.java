package com.example.crud.controladores;

import com.example.crud.entidades.Alumno;
import com.example.crud.repositorios.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alumnos")
public class AlumnoControlador {
    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @GetMapping
    public String listarAlumnos(Model model) {
        List<Alumno> alumnos = alumnoRepositorio.findAll();
        System.out.println(alumnos);
        model.addAttribute("alumnos", alumnos);
        return "alumnos";
    }

    @PostMapping
    public String guardarAlumno(Alumno alumno) {
        alumnoRepositorio.save(alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "formularioAlumno";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id,Model model) {
        Alumno alumno = alumnoRepositorio.findById(id).orElse(null);
        model.addAttribute("alumno", alumno);
        return "formularioAlumno";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        alumnoRepositorio.deleteById(id);
        return "redirect:/alumnos";
    }

    @PostMapping("/eliminar-alumnos")
    public String eliminarAlumnos(@RequestParam(name = "idsSeleccionados", required = false) List<Long> idsSeleccionados) {
        if (idsSeleccionados != null && !idsSeleccionados.isEmpty()) {
            alumnoRepositorio.deleteAllById(idsSeleccionados);
        }
        return "redirect:/alumnos";
    }



}
