package com.colegio.gestion_asignaturas.service;

import com.colegio.gestion_asignaturas.entity.Asignatura;
import com.colegio.gestion_asignaturas.entity.Usuario;
import com.colegio.gestion_asignaturas.repository.AsignaturaRepository;
import com.colegio.gestion_asignaturas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Asignatura> findAll() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> findById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public void deleteById(Long id) {
        asignaturaRepository.deleteById(id);
    }

    public List<Asignatura> findAsignaturasByDocente(String username) {
        Optional<Usuario> docente = usuarioRepository.findByUsername(username);
        return docente.map(d -> asignaturaRepository.findByDocentesContaining(d))
                .orElse(List.of());
    }

    public void actualizarHorarios(Long asignaturaId, Asignatura asignaturaActualizada) {
        asignaturaRepository.findById(asignaturaId).ifPresent(asignatura -> {
            asignatura.setHoraInicio(asignaturaActualizada.getHoraInicio());
            asignatura.setHoraFin(asignaturaActualizada.getHoraFin());
            asignaturaRepository.save(asignatura);
        });
    }
}