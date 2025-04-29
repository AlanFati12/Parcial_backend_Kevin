package com.colegio.gestion_asignaturas.config;

import com.colegio.gestion_asignaturas.entity.Usuario;
import com.colegio.gestion_asignaturas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository) {
        return args -> {
            // Solo inicializar si no hay usuarios
            if (usuarioRepository.count() == 0) {
                // Crear usuario Rector
                Usuario rector = new Usuario();
                rector.setUsername("rector");
                rector.setPassword(passwordEncoder.encode("1234"));
                rector.setNombre("Rector del Colegio");
                rector.setRol(Usuario.Role.RECTOR);
                usuarioRepository.save(rector);

                // Crear usuario Docente
                Usuario docente = new Usuario();
                docente.setUsername("docente");
                docente.setPassword(passwordEncoder.encode("1234"));
                docente.setNombre("Docente de Prueba");
                docente.setRol(Usuario.Role.DOCENTE);
                usuarioRepository.save(docente);

                // Crear usuario Estudiante
                Usuario estudiante = new Usuario();
                estudiante.setUsername("estudiante");
                estudiante.setPassword(passwordEncoder.encode("1234"));
                estudiante.setNombre("Estudiante de Prueba");
                estudiante.setRol(Usuario.Role.ESTUDIANTE);
                usuarioRepository.save(estudiante);

                // Crear usuario Docente
                Usuario Kevin = new Usuario();
                Kevin.setUsername("Kevin");
                Kevin.setPassword(passwordEncoder.encode("1234"));
                Kevin.setNombre("Kevin");
                Kevin.setRol(Usuario.Role.DOCENTE);
                usuarioRepository.save(Kevin);

                System.out.println("Base de datos inicializada con usuarios de prueba");
            }
        };
    }
}
