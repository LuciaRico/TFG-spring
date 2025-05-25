package com.ricosoft.ricorepairapi.dto;

import com.ricosoft.ricorepairapi.entity.DocumentacionTecnica;
import com.ricosoft.ricorepairapi.entity.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ParteDTO {
    @NotNull(message = "El id del usuario es requerido")
    private Integer usuarioId;
    @NotNull(message = "La fecha de creación es requerida")
    private Date fechaCreacion;
    @NotBlank(message = "El dispositivo es requerido")
    private String dispositivo;
    private String otrosMateriales;
    @NotNull(message = "El estado es requerido")
    private Estado estado;
    @NotNull(message = "La fecha estimada es requerida")
    private Date fechaEstimada;
    @NotBlank(message = "El motivo del cliente es requerido")
    private String motivoCliente;
    private String informeEmpresa;
    @NotNull(message = "La documentación técnica es requerida")
    private DocumentacionTecnica documentacionTecnica;

}
