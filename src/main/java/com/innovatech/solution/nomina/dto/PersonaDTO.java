package com.innovatech.solution.nomina.dto;


import com.innovatech.solution.nomina.dta.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private Double salario;
    private String cuentaBancaria;
    private String estado;
    private String telefono;
    private String correo;
    private Date fechaIngreso;
    private Date fechaNac;
    private Date fechaRetiro;
    private Cargo cargo;
    private Area area;
    private TipoContrato tipoContrato;
    private Bancos banco;
    private Eps eps;
    private Pensiones pensiones;
    private String mensaje;

}
