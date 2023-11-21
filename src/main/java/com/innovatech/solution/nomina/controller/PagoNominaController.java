package com.innovatech.solution.nomina.controller;
import com.innovatech.solution.nomina.dta.PagoNomina;
import com.innovatech.solution.nomina.dto.BusquedaPagosDTO;
import com.innovatech.solution.nomina.dto.JasperDTO;
import com.innovatech.solution.nomina.dto.PagoNominaDTO;
import com.innovatech.solution.nomina.service.ConsultasPagoService;
import com.innovatech.solution.nomina.service.PagoNominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagonomina")
public class PagoNominaController {
    @Autowired
    PagoNominaService pagoNominaService;
    @Autowired
    ConsultasPagoService consultasPagoService;
    @GetMapping("/listarPagos")
    public ResponseEntity<?> pagos(){
        return new ResponseEntity<>(pagoNominaService.listarPagos(), null, HttpStatus.OK);
    }
    @PostMapping("/calcular-pago")
    public PagoNominaDTO calcularPago(@RequestBody PagoNominaDTO pago){
        return pagoNominaService.calcularPago(pago);
    }
    @PostMapping("/cal-prima")
    public Double calPrima(@RequestBody Map<String, Object> datos){
        LocalDate fecNom = null;
        Double salario = null;
        fecNom = LocalDate.parse(datos.get("fecNom").toString());

        try {
            salario = Double.valueOf(datos.get("salario").toString());
        } catch (DateTimeParseException | NullPointerException e) {
            e.printStackTrace();
            salario = 0.0;
        }
        return pagoNominaService.calPrima(fecNom, salario);
    }
    @PostMapping("/crear-pago")
    public PagoNominaDTO crearPago(@RequestBody PagoNominaDTO pago){
        return pagoNominaService.crearPago(pago);
    }
    @PostMapping("/crear-pdf")
    public ResponseEntity<ByteArrayResource> crearPdf(@RequestBody JasperDTO jasper){
        return pagoNominaService.crearPdf(jasper);
    }
    @PostMapping("/busqueda-pagos")
    public ResponseEntity<List<PagoNomina>> busquedaPagos(@RequestBody BusquedaPagosDTO busquedaDTO){
        System.out.println(busquedaDTO);
       return new ResponseEntity<List<PagoNomina>>(consultasPagoService.busquedaPagos(busquedaDTO), HttpStatus.OK);
    }
}
