package com.colegio.gestion_asignaturas.controller;

import com.colegio.gestion_asignaturas.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudiante")
@Tag(name = "Estudiante", description = "Vistas para estudiantes (no disponibles en Swagger UI)")
public class EstudianteController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Operation(summary = "Listar todas las asignaturas visibles al estudiante")
    @GetMapping("/asignaturas")
    public String listarAsignaturas(Model model) {
        model.addAttribute("asignaturas", asignaturaService.findAll());
        return "estudiante/asignaturas";
    }
}