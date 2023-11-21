package com.innovatech.solution.nomina.service.impl;

import com.innovatech.solution.nomina.dto.PersonaDTO;
import com.innovatech.solution.nomina.dta.Persona;
import com.innovatech.solution.nomina.repository.PersonaRepository;
import com.innovatech.solution.nomina.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<PersonaDTO> personas() {

        List<PersonaDTO> lPersonaDTOS = new ArrayList<>();
        List<Persona> listPersonas = personaRepository.findAll();
        for (Persona persona : listPersonas) {
            lPersonaDTOS.add(PersonaDTO.builder()
                    .id(persona.getId())
                    .identificacion(persona.getIdentificacion())
                    .nombres(persona.getNombres())
                    .apellidos(persona.getApellidos())
                    .salario(persona.getSalario())
                    .cuentaBancaria(persona.getCuentaBancaria())
                    .fechaIngreso(persona.getFechaIngreso())
                    .fechaNac(persona.getFechaNac())
                    .fechaRetiro(persona.getFechaRetiro())
                    .estado(persona.getEstado())
                    .telefono(persona.getTelefono())
                    .correo(persona.getCorreo())
                    .cargo(persona.getCargo())
                    .area(persona.getArea())
                    .tipoContrato(persona.getTipoContrato())
                    .banco(persona.getBanco())
                    .eps(persona.getEps())
                    .pensiones(persona.getPensiones())
                    .build());
        }
        return lPersonaDTOS;
    }

    @Override
    public PersonaDTO persona(String id) {

        Optional<Persona> persona = personaRepository.findByIdentificacion(id);

        return persona.map(value -> PersonaDTO.builder()
                .id(value.getId())
                .identificacion(value.getIdentificacion())
                .nombres(value.getNombres())
                .apellidos(value.getApellidos())
                .salario(value.getSalario())
                .cuentaBancaria(value.getCuentaBancaria())
                .fechaIngreso(value.getFechaIngreso())
                .fechaNac(value.getFechaNac())
                .fechaRetiro(value.getFechaRetiro())
                .estado(value.getEstado())
                .telefono(value.getTelefono())
                .correo(value.getCorreo())
                .cargo(value.getCargo())
                .area(value.getArea())
                .tipoContrato(value.getTipoContrato())
                .banco(value.getBanco())
                .eps(value.getEps())
                .pensiones(value.getPensiones())
                .build()).orElse(PersonaDTO.builder().mensaje("No se ha encontrado a nadie").build());
    }

    @Override
    public PersonaDTO registrar(PersonaDTO persona) {
        Persona person = Persona.builder()
                .id(persona.getId())
                .identificacion(persona.getIdentificacion())
                .nombres(persona.getNombres())
                .apellidos(persona.getApellidos())
                .salario(persona.getSalario())
                .cuentaBancaria(persona.getCuentaBancaria())
                .estado(persona.getEstado())
                .telefono(persona.getTelefono())
                .correo(persona.getCorreo())
                .fechaIngreso(persona.getFechaIngreso())
                .fechaNac(persona.getFechaNac())
                .cargo(persona.getCargo())
                .area(persona.getArea())
                .tipoContrato(persona.getTipoContrato())
                .banco(persona.getBanco())
                .eps(persona.getEps())
                .pensiones(persona.getPensiones())
                .build();

        personaRepository.save(person);
        return persona;
    }
}
