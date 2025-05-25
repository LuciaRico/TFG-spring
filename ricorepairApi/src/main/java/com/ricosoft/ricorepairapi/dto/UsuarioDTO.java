package com.ricosoft.ricorepairapi.dto;

import com.ricosoft.ricorepairapi.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    @NotBlank(message = "El nombre es requerido")
    private String name;
    @NotBlank(message = "El apellido es requerido")
    private String surname;
    @Email(message = "Debe ser un correo con formato válido")
    @NotBlank(message = "El email es requerido")
    private String email;
    @NotBlank(message = "El teléfono es requerido")
    private String tlf;
    @NotBlank(message = "La contraseña es requerida")
    private String password;
//    @NotNull(message = "El rol es requerido")
//    private Rol rol;


}
