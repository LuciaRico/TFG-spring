package com.ricosoft.ricorepairapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IntervencionDTO {
    @NotNull(message = "El id del parte es requerido")
    private Integer parteId;
    @NotNull(message = "La fecha es requerida")
    private Date fecha;
    @NotBlank(message = "El técnico es requerido")
    private String tecnico;
    @NotBlank(message = " El título de la intervención es requerido")
    private String intervencion;
    private String descripcion;
}
