package com.innovatech.solution.nomina.dta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personal")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "cuenta_bancaria")
    private String cuentaBancaria;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "fecha_nac")
    private Date fechaNac;

    @Column(name = "fecha_retiro")
    private Date fechaRetiro;

    @Column(name = "estado")
    private String estado;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name="cargo_id")
    @JsonIgnoreProperties("personas")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name="area_id")
    @JsonIgnoreProperties("personas")
    private Area area;

    @ManyToOne
    @JoinColumn(name="tipo_contrato_id")
    private TipoContrato tipoContrato;

    @ManyToOne
    @JoinColumn(name="banco_id")
    private Bancos banco;

    @ManyToOne
    @JoinColumn(name="eps_id")
    private Eps eps;

    @ManyToOne
    @JoinColumn(name="pensiones_id")
    private Pensiones pensiones;

}
