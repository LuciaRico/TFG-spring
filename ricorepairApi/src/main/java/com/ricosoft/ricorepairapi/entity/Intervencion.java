package com.ricosoft.ricorepairapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "intervenciones")
public class Intervencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonBackReference  // Esta anotación previene el bucle de serialización
    private Parte parte;


    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String tecnico;

    private String intervencion;

    private String descripcion;

    public Intervencion() {
    }

    public Intervencion(Integer id, Parte parte, Date fecha, String tecnico, String intervencion, String descripcion) {
        this.id = id;
        this.parte = parte;
        this.fecha = fecha;
        this.tecnico = tecnico;
        this.intervencion = intervencion;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getIntervencion() {
        return intervencion;
    }

    public void setIntervencion(String intervencion) {
        this.intervencion = intervencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
