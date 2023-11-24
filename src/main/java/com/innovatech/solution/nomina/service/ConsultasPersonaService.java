package com.innovatech.solution.nomina.service;

import com.innovatech.solution.nomina.dta.Persona;
import com.innovatech.solution.nomina.dto.BusquedaPersonasDTO;

import java.util.List;

public interface ConsultasPersonaService {
    List<Persona> busquedaPersonas(BusquedaPersonasDTO busquedaDTO);
}
