package com.innovatech.solution.nomina.criteria;

import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.Data;

@Data
public class PersonaCriteria {
    private StringFilter identificacion;
    private StringFilter apellidos;
    private StringFilter estado;
    private StringFilter area;
    private StringFilter cargo;
    private DoubleFilter salario;
}
