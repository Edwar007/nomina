package com.innovatech.solution.nomina.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoNominaDTO {
    private Long id;
    private String idPersona;
    private LocalDate fecha;
    private Double salario;
    private Double prima;
    private Double subTrans;
    private Double gasRepre;
    private Double viaticos;
    private Double comisiones;
    private Double horExtDiu;
    private Double horExtNoc;
    private Double horasExtDiuDomFes;
    private Double horasExtNocDomFes;
    private Double salud;
    private Double pension;
    private Double fonSol;
    private Double totDev;
    private Double totDes;
    private Double pagoFinal;
}
