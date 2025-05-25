package com.ricosoft.ricorepairapi.entity;

import lombok.Getter;

@Getter
public enum DocumentacionTecnica {
    SI("Si"),
    NO("No");

    private final String descripcion;

    DocumentacionTecnica(String descripcion) {
        this.descripcion = descripcion;
    }

}
