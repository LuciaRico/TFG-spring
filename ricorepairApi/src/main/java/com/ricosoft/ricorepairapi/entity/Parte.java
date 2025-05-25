package com.ricosoft.ricorepairapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "partes")
public class Parte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    

    @ManyToOne
    @JsonBackReference  // Esta anotación previene el bucle de serialización
    private Usuario usuario;

    private LocalDateTime fechaCreacion;

    private String dispositivo;

    private String otrosMateriales;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Temporal(TemporalType.DATE)
    private Date fechaEstimada;

    private String motivoCliente;

    private String informeEmpresa;

    @Enumerated(EnumType.STRING)
    private DocumentacionTecnica documentacionTecnica;

    // Relación OneToMany con Intervencion
    @OneToMany(mappedBy = "parte", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Esta anotación maneja la serialización de la lista
    private List<Intervencion> intervenciones = new ArrayList<>();

    public Parte() {
    }

    public Parte(Integer id, Usuario usuario, LocalDateTime fechaCreacion, String dispositivo, String otrosMateriales,
                 Estado estado, Date fechaEstimada, String motivoCliente, String informeEmpresa,
                 DocumentacionTecnica documentacionTecnica) {
        this.id = id;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.dispositivo = dispositivo;
        this.otrosMateriales = otrosMateriales;
        this.estado = estado;
        this.fechaEstimada = fechaEstimada;
        this.motivoCliente = motivoCliente;
        this.informeEmpresa = informeEmpresa;
        this.documentacionTecnica = documentacionTecnica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getOtrosMateriales() {
        return otrosMateriales;
    }

    public void setOtrosMateriales(String otrosMateriales) {
        this.otrosMateriales = otrosMateriales;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public String getMotivoCliente() {
        return motivoCliente;
    }

    public void setMotivoCliente(String motivoCliente) {
        this.motivoCliente = motivoCliente;
    }

    public String getInformeEmpresa() {
        return informeEmpresa;
    }

    public void setInformeEmpresa(String informeEmpresa) {
        this.informeEmpresa = informeEmpresa;
    }

    public DocumentacionTecnica getDocumentacionTecnica() {
        return documentacionTecnica;
    }

    public void setDocumentacionTecnica(DocumentacionTecnica documentacionTecnica) {
        this.documentacionTecnica = documentacionTecnica;
    }

    public List<Intervencion> getIntervenciones() {
        return intervenciones;
    }

    public void setIntervenciones(List<Intervencion> intervenciones) {
        this.intervenciones = intervenciones;
    }

    // Incluye el valor de usuarioId en la representación JSON de Parte
    @JsonProperty("usuarioId")
    public Integer getUsuarioId() {
        return usuario != null ? usuario.getId() : null;
    }
}
