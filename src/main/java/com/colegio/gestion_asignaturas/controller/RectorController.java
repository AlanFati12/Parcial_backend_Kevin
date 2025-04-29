package com.colegio.gestion_asignaturas.controller;

import com.colegio.gestion_asignaturas.entity.Asignatura;
import com.colegio.gestion_asignaturas.entity.Usuario;
import com.colegio.gestion_asignaturas.repository.UsuarioRepository;
import com.colegio.gestion_asignaturas.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rector")

@Tag(name = "RectorController", description = "Controlador para gestión de asignaturas por parte del rector")
public class RectorController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Muestra todas las asignaturas")
    @GetMapping("/asignaturas")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", asignaturaService.findAll());
        return "rector/asignaturas";
    }

    @Operation(summary = "Formulario para nueva asignatura")
    @GetMapping("/asignaturas/nueva")
    public String formularioNuevaAsignatura(Model model) {
        model.addAttribute("asignatura", new Asignatura());
        List<Usuario> docentes = usuarioRepository.findAll().stream()
                .filter(u -> u.getRol() == Usuario.Role.DOCENTE)
                .collect(Collectors.toList());
        model.addAttribute("docentes", docentes);
        return "rector/formulario-asignatura";
    }

    @Operation(summary = "Guarda una nueva asignatura")
    @PostMapping("/asignaturas/guardar")
    public String guardarAsignatura(@Valid @ModelAttribute Asignatura asignatura,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Usuario> docentes = usuarioRepository.findAll().stream()
                    .filter(u -> u.getRol() == Usuario.Role.DOCENTE)
                    .collect(Collectors.toList());
            model.addAttribute("docentes", docentes);
            return "rector/formulario-asignatura";
        }

        asignaturaService.save(asignatura);
        return "redirect:/rector/asignaturas";
    }

    @Operation(summary = "Formulario de edición de una asignatura")
    @GetMapping("/asignaturas/editar/{id}")
    public String editarAsignatura(@PathVariable Long id, Model model) {
        Asignatura asignatura = asignaturaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de asignatura inválido: " + id));

        model.addAttribute("asignatura", asignatura);
        List<Usuario> docentes = usuarioRepository.findAll().stream()
                .filter(u -> u.getRol() == Usuario.Role.DOCENTE)
                .collect(Collectors.toList());
        model.addAttribute("docentes", docentes);
        return "rector/formulario-asignatura";
    }

    @Operation(summary = "Elimina una asignatura por ID")
    @GetMapping("/asignaturas/eliminar/{id}")
    public String eliminarAsignatura(@PathVariable Long id) {
        asignaturaService.deleteById(id);
        return "redirect:/rector/asignaturas";
    }
}