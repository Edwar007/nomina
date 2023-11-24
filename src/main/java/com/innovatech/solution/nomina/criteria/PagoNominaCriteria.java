package com.innovatech.solution.nomina.criteria;

import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

@Data
public class PagoNominaCriteria {
    private StringFilter idPersona;
    private LocalDateFilter fecha;
    private DoubleFilter totDev;
    private DoubleFilter totDes;
    private DoubleFilter pagoFinal;
}
