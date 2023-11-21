package com.innovatech.solution.nomina.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotEmpty(message = "Email no puede estar vacío")
    @Email(message = "Email debe ser válido",regexp=".+@.+\\..+")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número")
    private String password;

}
