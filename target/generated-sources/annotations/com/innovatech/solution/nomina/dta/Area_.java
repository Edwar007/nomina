package com.innovatech.solution.nomina.dta;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Area.class)
public abstract class Area_ {

	public static volatile SingularAttribute<Area, Long> id;
	public static volatile SingularAttribute<Area, String> nombre;
	public static volatile SetAttribute<Area, Persona> personas;

	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String PERSONAS = "personas";

}

