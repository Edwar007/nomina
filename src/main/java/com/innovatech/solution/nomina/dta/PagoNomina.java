package com.innovatech.solution.nomina.dta;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagos_nomina")
public class PagoNomina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_personal")
    private String idPersona;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "prima")
    private Double prima;

    @Column(name = "sub_trans")
    private Double subTrans;

    @Column(name = "gas_repre")
    private Double gasRepre;

    @Column(name = "viaticos")
    private Double viaticos;

    @Column(name = "comisiones")
    private Double comisiones;

    @Column(name = "hor_ex_diu")
    private Double horExtDiu;

    @Column(name = "hor_ex_noc")
    private Double horExtNoc;

    @Column(name = "hor_ex_diu_dom_fes")
    private Double horasExtDiuDomFes;

    @Column(name = "hor_ex_noc_dom_fes")
    private Double horasExtNocDomFes;

    @Column(name = "salud")
    private Double salud;

    @Column(name = "pension")
    private Double pension;

    @Column(name = "fon_sol")
    private Double fonSol;

    @Column(name = "tot_dev")
    private Double totDev;

    @Column(name = "tod_des")
    private Double totDes;

    @Column(name = "pago_final")
    private Double pagoFinal;

}
