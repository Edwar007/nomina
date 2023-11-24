package com.innovatech.solution.nomina.repository;

import com.innovatech.solution.nomina.dta.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {
    Optional<Persona> findByIdentificacion(String identificacion);

}
