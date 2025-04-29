package com.colegio.gestion_asignaturas.controller;

import com.colegio.gestion_asignaturas.entity.Asignatura;
import com.colegio.gestion_asignaturas.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/docente")
@Tag(name = "Docente", description = "Operaciones disponibles para los docentes")
public class DocenteController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Operation(summary = "Listar asignaturas del docente")

    @GetMapping("/asignaturas")
    public String listarAsignaturas(Model model, Authentication authentication) {
        model.addAttribute("asignaturas", asignaturaService.findAsignaturasByDocente(authentication.getName()));
        return "docente/asignaturas";
    }

    @Operation(summary = "Mostrar formulario de horario para una asignatura")

    @GetMapping("/asignaturas/horario/{id}")
    public String formularioHorario(@Parameter(description = "ID de la asignatura")@PathVariable Long id, Model model, Authentication authentication) {
        Asignatura asignatura = asignaturaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de asignatura inválido: " + id));

        model.addAttribute("asignatura", asignatura);
        return "docente/formulario-horario";
    }

    @Operation(summary = "Actualizar el horario de una asignatura")
    @PostMapping("/asignaturas/actualizar-horario/{id}")
    public String actualizarHorario(@Parameter(description = "ID de la asignatura a actualizar")@PathVariable Long id,
                                    @Valid @ModelAttribute Asignatura asignatura,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "docente/formulario-horario";
        }

        asignaturaService.actualizarHorarios(id, asignatura);
        return "redirect:/docente/asignaturas";
    }
}