package com.innovatech.solution.nomina.service;

import com.innovatech.solution.nomina.dta.Usuario;
import com.innovatech.solution.nomina.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    String registrar(UsuarioDTO usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
