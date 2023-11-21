package com.innovatech.solution.nomina.controller;

import com.innovatech.solution.nomina.dto.*;
import com.innovatech.solution.nomina.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/cargar")
public class CargarCamposController {

    @Autowired
    private CargarCamposService cargarCamposService;
    @GetMapping("/cargos")
    public ResponseEntity<List<CargoDTO>> consultarCargos(){
        return new ResponseEntity<>(cargarCamposService.cargos(), null, HttpStatus.OK);
    }
    @GetMapping("/areas")
    public ResponseEntity<List<AreaDTO>> consultarAreas(){
        return new ResponseEntity<>(cargarCamposService.areas(), null, HttpStatus.OK);
    }
    @GetMapping("/contratos")
    public ResponseEntity<List<TipoContratoDTO>> consultarContratos(){
        return new ResponseEntity<>(cargarCamposService.tipoContratos(), null, HttpStatus.OK);
    }
    @GetMapping("/bancos")
    public ResponseEntity<List<BancosDTO>> consultarBancos(){
        return new ResponseEntity<>(cargarCamposService.bancos(), null, HttpStatus.OK);
    }
    @GetMapping("/eps")
    public ResponseEntity<List<EpsDTO>> consultarEps(){
        return new ResponseEntity<>(cargarCamposService.listaEps(), null, HttpStatus.OK);
    }
    @GetMapping("/pensiones")
    public ResponseEntity<List<PensionesDTO>> consultarPensiones(){
        return new ResponseEntity<>(cargarCamposService.pensiones(), null, HttpStatus.OK);
    }

}
