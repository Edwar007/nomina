package com.innovatech.solution.nomina.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JasperDTO {
    private String cedula;
    private String fecha;
    private String nombre;
    private String cargo;
    private String cuenta;
    private String banco;
    private Double salario;
    private Double totDev;
    private Double totDes;
    private Double totalPagar;
}
