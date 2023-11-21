package com.innovatech.solution.nomina.dta;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PagoNomina.class)
public abstract class PagoNomina_ {

	public static volatile SingularAttribute<PagoNomina, Double> prima;
	public static volatile SingularAttribute<PagoNomina, Double> horasExtNocDomFes;
	public static volatile SingularAttribute<PagoNomina, Double> pagoFinal;
	public static volatile SingularAttribute<PagoNomina, Double> horExtNoc;
	public static volatile SingularAttribute<PagoNomina, Double> horasExtDiuDomFes;
	public static volatile SingularAttribute<PagoNomina, Double> salud;
	public static volatile SingularAttribute<PagoNomina, LocalDate> fecha;
	public static volatile SingularAttribute<PagoNomina, Double> comisiones;
	public static volatile SingularAttribute<PagoNomina, Double> gasRepre;
	public static volatile SingularAttribute<PagoNomina, Double> viaticos;
	public static volatile SingularAttribute<PagoNomina, Double> fonSol;
	public static volatile SingularAttribute<PagoNomina, Double> subTrans;
	public static volatile SingularAttribute<PagoNomina, Double> totDev;
	public static volatile SingularAttribute<PagoNomina, Double> pension;
	public static volatile SingularAttribute<PagoNomina, Long> id;
	public static volatile SingularAttribute<PagoNomina, Double> totDes;
	public static volatile SingularAttribute<PagoNomina, String> idPersona;
	public static volatile SingularAttribute<PagoNomina, Double> horExtDiu;

	public static final String PRIMA = "prima";
	public static final String HORAS_EXT_NOC_DOM_FES = "horasExtNocDomFes";
	public static final String PAGO_FINAL = "pagoFinal";
	public static final String HOR_EXT_NOC = "horExtNoc";
	public static final String HORAS_EXT_DIU_DOM_FES = "horasExtDiuDomFes";
	public static final String SALUD = "salud";
	public static final String FECHA = "fecha";
	public static final String COMISIONES = "comisiones";
	public static final String GAS_REPRE = "gasRepre";
	public static final String VIATICOS = "viaticos";
	public static final String FON_SOL = "fonSol";
	public static final String SUB_TRANS = "subTrans";
	public static final String TOT_DEV = "totDev";
	public static final String PENSION = "pension";
	public static final String ID = "id";
	public static final String TOT_DES = "totDes";
	public static final String ID_PERSONA = "idPersona";
	public static final String HOR_EXT_DIU = "horExtDiu";

}

