package com.innovatech.solution.nomina.controller;

import com.innovatech.solution.nomina.dta.Usuario;
import com.innovatech.solution.nomina.service.UsuarioService;
import com.innovatech.solution.nomina.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/verificar")
    public String login(@RequestBody Usuario usuario) {

        Usuario usuarioLogueado = usuarioService.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

}
