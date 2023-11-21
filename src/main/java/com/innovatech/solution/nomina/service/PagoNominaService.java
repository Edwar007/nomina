package com.innovatech.solution.nomina.service;

import com.innovatech.solution.nomina.criteria.PagoNominaCriteria;
import com.innovatech.solution.nomina.dta.PagoNomina;
import com.innovatech.solution.nomina.dto.BusquedaPagosDTO;
import com.innovatech.solution.nomina.dto.JasperDTO;
import com.innovatech.solution.nomina.dto.PagoNominaDTO;
import com.innovatech.solution.nomina.dto.PersonaDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PagoNominaService {
    List<PagoNominaDTO> listarPagos();
    PagoNominaDTO calcularPago(PagoNominaDTO pago);

    PagoNominaDTO crearPago(PagoNominaDTO pago);
    Double calPrima(LocalDate fecNom, Double salario);
    ResponseEntity<ByteArrayResource> crearPdf(JasperDTO jasper);

}
