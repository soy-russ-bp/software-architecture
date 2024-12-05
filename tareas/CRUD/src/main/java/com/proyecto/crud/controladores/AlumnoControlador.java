package com.proyecto.crud.controladores;

import com.proyecto.crud.entidades.Alumno;
import com.proyecto.crud.repositorios.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar entidades "Alumno".
 *
 * Proporciona la logica para administrar solicitudes relacionadas con el mapeo de "Alumno" enviadas por el cliente.
 */
@Controller
@RequestMapping("/alumnos")
public class AlumnoControlador {
    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    /**
     * Obtiene todos los alumnos de la base de datos y actualiza el contenedor "model" de la vista.
     *
     * @param model Contenedor de datos proporcionado por Spring para la comunicación entre vista y controlador.
     * @return Dirige a la pagina principal del sistema.
     */
    @GetMapping
    public String listarAlumnos(Model model) {
        List<Alumno> alumnos = alumnoRepositorio.findAll();
        System.out.println(alumnos);
        model.addAttribute("alumnos", alumnos);
        return "alumnos";
    }

    /**
     * Guarda una instancia del objeto Alumno en la base de datos.
     *
     * @param alumno Instancia de "Alumno" con los datos proporcioador en "../formularioAlumno"-
     * @return Redirecciona a la página principal del sistema-
     */
    @PostMapping
    public String guardarAlumno(Alumno alumno) {
        alumnoRepositorio.save(alumno);
        return "redirect:/alumnos";
    }

    /**
     * Instancia un objeto "Alumno" vacio
     *
     * @param model Contenedor de datos proporcionado por Spring para la comunicación entre vista y controlador.
     * @return Url de "formularioAlumno" para ingresar datos del nuevo "Alumno".
     */
    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "formularioAlumno";
    }

    /**
     * Obtiene un alumno mediante su identificador ("id") de la base de datos.
     *
     * @param id    identificador del "Alumno"
     * @param model Contenedor de datos proporcionado por Spring para la comunicación entre vista y controlador.
     * @return Url de "formularioAlumno" para ingresar actualizar datos del "Alumno" Seleccionado.
     */
    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id,Model model) {
        Alumno alumno = alumnoRepositorio.findById(id).orElse(null);
        model.addAttribute("alumno", alumno);
        return "formularioAlumno";
    }

    /**
     * Elimina un alumno de la base de datos.
     *
     * @param id identificador del alumno a eliminar.
     * @return Url de la pagina principal del sistema.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        alumnoRepositorio.deleteById(id);
        return "redirect:/alumnos";
    }

    /**
     * Elimina uno o más alumnos de la base de datos.
     *
     * @param idsSeleccionados identificadores de los "Alumnos" seleccionados en el "CheckBox".
     * @return Url de la página principal del sistema.
     */
    @PostMapping("/eliminar-alumnos")
    public String eliminarAlumnos(@RequestParam(name = "idsSeleccionados", required = false) List<Long> idsSeleccionados) {
        if (idsSeleccionados != null && !idsSeleccionados.isEmpty()) {
            alumnoRepositorio.deleteAllById(idsSeleccionados);
        }
        return "redirect:/alumnos";
    }



}
