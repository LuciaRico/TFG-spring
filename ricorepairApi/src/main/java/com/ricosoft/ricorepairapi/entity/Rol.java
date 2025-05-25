package com.ricosoft.ricorepairapi.entity;

import lombok.Getter;

@Getter
public enum Rol {
    ADMIN("Administrador"),
    USER("Usuario");

    private final String descripcion;

    Rol(String descripcion) {
        this.descripcion = descripcion;
    }



}
