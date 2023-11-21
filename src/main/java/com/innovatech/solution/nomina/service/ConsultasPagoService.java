package com.innovatech.solution.nomina.service;

import com.innovatech.solution.nomina.dta.PagoNomina;
import com.innovatech.solution.nomina.dto.BusquedaPagosDTO;

import java.util.List;

public interface ConsultasPagoService {
    List<PagoNomina> busquedaPagos(BusquedaPagosDTO busquedaDTO);
}
