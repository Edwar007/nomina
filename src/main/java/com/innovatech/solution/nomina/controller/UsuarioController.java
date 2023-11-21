package com.innovatech.solution.nomina.controller;

import com.innovatech.solution.nomina.dta.Usuario;
import com.innovatech.solution.nomina.dto.UsuarioDTO;
import com.innovatech.solution.nomina.service.UsuarioService;
import com.innovatech.solution.nomina.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping("/obtenerUsuario")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token)) { return null; }
        return usuarioService.getUsuarios();
    }
    @PostMapping("/registrar-usuario")
    public String registrarUsuario( @Valid @RequestBody UsuarioDTO usuario,  BindingResult bindingResult) {
        //Captura las expeciones si es por el correo o por la contraseña
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                if (error.getField().equals("email")) {
                    return "correo";
                } else if (error.getField().equals("password")) {
                    return "clave";
                }
            }
        }
        //Objeto de la calse Argon2
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //el parametro 1, es la cantidad de iteraciones
        // el parametro 2, uso de memoria
        // el parametro 3, hilos
        //parametro 4 el texto que se desea realizar el hash
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioService.registrar(usuario);
    }
    @DeleteMapping("/eliminarUsuario")
    public void eliminar(@RequestHeader(value="Authorization") String token,@PathVariable Long id) {
        if (!validarToken(token)) { return; }
        usuarioService.eliminar(id);
    }
    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
}