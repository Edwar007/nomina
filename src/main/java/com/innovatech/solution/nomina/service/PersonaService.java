package com.innovatech.solution.nomina.service;

import com.innovatech.solution.nomina.dta.Persona;
import com.innovatech.solution.nomina.dto.PersonaDTO;
import java.util.List;

public interface PersonaService {

    List<PersonaDTO> personas();
    PersonaDTO persona(String id);
    PersonaDTO registrar(PersonaDTO persona);
    void actualizar(PersonaDTO persona);
    void desactivar(Long id);
}
