package com.colegio.gestion_asignaturas.repository;

import com.colegio.gestion_asignaturas.entity.Asignatura;
import com.colegio.gestion_asignaturas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    List<Asignatura> findByDocentesContaining(Usuario docente);
}