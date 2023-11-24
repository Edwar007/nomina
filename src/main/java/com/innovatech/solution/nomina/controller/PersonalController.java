package com.innovatech.solution.nomina.controller;

import com.innovatech.solution.nomina.dta.Persona;
import com.innovatech.solution.nomina.dto.BusquedaPersonasDTO;
import com.innovatech.solution.nomina.dto.PersonaDTO;
import com.innovatech.solution.nomina.service.ConsultasPersonaService;
import com.innovatech.solution.nomina.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    PersonaService personaService;

    @Autowired
    ConsultasPersonaService consultasPersonaService;

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaDTO>> personas() {
        return new ResponseEntity<>(personaService.personas(), null, HttpStatus.OK);
    }
    @GetMapping("/buscar-persona/{identificacion}")
    public ResponseEntity<PersonaDTO> persona(@PathVariable("identificacion") String id) {
        return new ResponseEntity<>(personaService.persona(id), null, HttpStatus.OK);
    }
    @PostMapping("/registrar-persona")
    public PersonaDTO registrarPersona(@RequestBody PersonaDTO persona) {
        return personaService.registrar(persona);

    }
    @PostMapping("/actualizar-persona")
    public void actualizarPersona(@RequestBody PersonaDTO persona) {
        personaService.actualizar(persona);
    }
    @PostMapping("/desactivar-persona/{id}")
    public void desactivarPersona(@PathVariable("id") Long id) {
        personaService.desactivar(id);
    }

    @PostMapping("/busqueda-personas")
    public ResponseEntity<List<Persona>> busquedaPagos(@RequestBody BusquedaPersonasDTO busquedaDTO){
        return new ResponseEntity<List<Persona>>(consultasPersonaService.busquedaPersonas(busquedaDTO), HttpStatus.OK);
    }

}
