package com.innovatech.solution.nomina.service;

import com.innovatech.solution.nomina.dto.*;

import java.util.List;

public interface CargarCamposService {

    List<EpsDTO> listaEps();

    List<PensionesDTO> pensiones();

    List<BancosDTO> bancos();

    List<TipoContratoDTO> tipoContratos();

    List<CargoDTO> cargos();

    List<AreaDTO> areas();
}
