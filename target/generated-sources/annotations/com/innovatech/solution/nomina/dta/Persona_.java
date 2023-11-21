package com.innovatech.solution.nomina.dta;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, String> apellidos;
	public static volatile SingularAttribute<Persona, Area> area;
	public static volatile SingularAttribute<Persona, String> estado;
	public static volatile SingularAttribute<Persona, Double> salario;
	public static volatile SingularAttribute<Persona, Bancos> banco;
	public static volatile SingularAttribute<Persona, Eps> eps;
	public static volatile SingularAttribute<Persona, String> identificacion;
	public static volatile SingularAttribute<Persona, String> nombres;
	public static volatile SingularAttribute<Persona, Date> fechaNac;
	public static volatile SingularAttribute<Persona, Date> fechaIngreso;
	public static volatile SingularAttribute<Persona, Pensiones> pensiones;
	public static volatile SingularAttribute<Persona, String> cuentaBancaria;
	public static volatile SingularAttribute<Persona, String> correo;
	public static volatile SingularAttribute<Persona, TipoContrato> tipoContrato;
	public static volatile SingularAttribute<Persona, Long> id;
	public static volatile SingularAttribute<Persona, Date> fechaRetiro;
	public static volatile SingularAttribute<Persona, String> telefono;
	public static volatile SingularAttribute<Persona, Cargo> cargo;

	public static final String APELLIDOS = "apellidos";
	public static final String AREA = "area";
	public static final String ESTADO = "estado";
	public static final String SALARIO = "salario";
	public static final String BANCO = "banco";
	public static final String EPS = "eps";
	public static final String IDENTIFICACION = "identificacion";
	public static final String NOMBRES = "nombres";
	public static final String FECHA_NAC = "fechaNac";
	public static final String FECHA_INGRESO = "fechaIngreso";
	public static final String PENSIONES = "pensiones";
	public static final String CUENTA_BANCARIA = "cuentaBancaria";
	public static final String CORREO = "correo";
	public static final String TIPO_CONTRATO = "tipoContrato";
	public static final String ID = "id";
	public static final String FECHA_RETIRO = "fechaRetiro";
	public static final String TELEFONO = "telefono";
	public static final String CARGO = "cargo";

}

