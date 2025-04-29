package com.colegio.gestion_asignaturas.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity
@Table(name = "asignaturas")

@Schema(description = "Representa una asignatura en el sistema")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la asignatura", example = "1")
    private Long id;

    @Column(nullable = false)
    @Size(max = 30)
    @Schema(description = "Nombre de la asignatura", example = "Matemáticas", maxLength = 30)
    private String nombre;

    @Schema(description = "Descripción de la asignatura", example = "Asignatura básica de matemáticas", maxLength = 100)
    @Column(nullable = false)
    @Size(max = 100)
    private String descripcion;

    @Schema(description = "Cupo máximo de estudiantes", example = "30")
    @Column(nullable = false)
    private Integer cupo;

    @Schema(description = "Hora de inicio", example = "08:00")
    @Column(nullable = false)
    private LocalTime horaInicio;

    @Schema(description = "Hora de fin", example = "09:30")
    @Column(nullable = false)
    private LocalTime horaFin;

    @Schema(description = "IDs de los docentes asignados")
    @ManyToMany(mappedBy = "asignaturasACargo")
    private Set<Usuario> docentes;
}