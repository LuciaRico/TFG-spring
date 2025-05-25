package com.ricosoft.ricorepairapi.entity;

import lombok.Getter;

@Getter
public enum Estado {
    PE("Pendiente"),
    EP("En Pausa"),
    EC("En Curso"),
    AN("Anulado"),
    OK("Finalizado");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

}
