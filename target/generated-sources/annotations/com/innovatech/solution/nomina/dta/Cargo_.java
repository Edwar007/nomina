package com.innovatech.solution.nomina.dta;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cargo.class)
public abstract class Cargo_ {

	public static volatile SingularAttribute<Cargo, Long> id;
	public static volatile SingularAttribute<Cargo, String> nombre;
	public static volatile SetAttribute<Cargo, Persona> personas;

	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String PERSONAS = "personas";

}

