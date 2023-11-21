package com.innovatech.solution.nomina.repository.impl;

import com.innovatech.solution.nomina.dta.Persona;
import com.innovatech.solution.nomina.dta.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}
