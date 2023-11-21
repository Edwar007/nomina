package com.innovatech.solution.nomina.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BusquedaPagosDTO {
    private String identificacion;
    private LocalDate fecha;
    private Double totDesDesde;
    private Double totDesHasta;
    private Double totDevDesde;
    private Double totDevHasta;
    private Double pagFinDesde;
    private Double pagFinHasta;

}
