package com.innovatech.solution.nomina.dto;

import lombok.Data;

@Data
public class BusquedaPersonasDTO {
    private String identificacion;
    private String apellidos;
    private String estado;
    private String area;
    private String cargo;
    private Double salarioDesde;
    private Double salarioHasta;
}
