package com.colegio.gestion_asignaturas.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "usuarios")
@Schema(description = "Representa un usuario del sistema que pueden tener diferente roles")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre de usuario", example = "docente01")
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Schema(description = "Nombre completo del usuario", example = "Kevin Santiago")
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role rol;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "docente_asignatura",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private Set<Asignatura> asignaturasACargo;

    // Enum para los roles
    public enum Role {
        RECTOR, DOCENTE, ESTUDIANTE
    }
}