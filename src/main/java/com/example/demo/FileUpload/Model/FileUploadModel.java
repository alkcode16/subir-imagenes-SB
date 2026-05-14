package com.example.demo.FileUpload.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "datos_ventanilla")
public class FileUploadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "folio")
    private String folio;
    @Column(name = "fec_documento")
    private String fecDocumento;
    @Column(name = "documento")
    private String documento;
    @Column(name = "procedencia")
    private String procedencia;
    @Column(name = "asunto")
    private String asunto;
    @Column(name = "tipo_tramite")
    private String tipoTramite;
    @Column(name = "tipo_asunto")
    private String tipoAsunto;
    @Column(name = "fec_limite")
    private String fecLimite;
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "accion")
    private String accion;
    @Column(name = "ruta")
    private String ruta;

    public FileUploadModel(){}
    public FileUploadModel(String folio,
                           String fecDocumento,
                           String documento,
                           String procedencia,
                           String asunto,
                           String tipoTramite,
                           String tipoAsunto,
                           String fecLimite,
                           String prioridad,
                           String observaciones,
                           String responsable,
                           String accion,
                           String ruta) {
        this.folio = folio;
        this.fecDocumento = fecDocumento;
        this.documento = documento;
        this.procedencia = procedencia;
        this.asunto = asunto;
        this.tipoTramite = tipoTramite;
        this.tipoAsunto = tipoAsunto;
        this.fecLimite = fecLimite;
        this.prioridad = prioridad;
        this.observaciones = observaciones;
        this.responsable = responsable;
        this.accion = accion;
        this.ruta = ruta;
    }


    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecDocumento() {
        return fecDocumento;
    }

    public void setFecDocumento(String fecDocumento) {
        this.fecDocumento = fecDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getTipoAsunto() {
        return tipoAsunto;
    }

    public void setTipoAsunto(String tipoAsunto) {
        this.tipoAsunto = tipoAsunto;
    }

    public String getFecLimite() {
        return fecLimite;
    }

    public void setFecLimite(String fecLimite) {
        this.fecLimite = fecLimite;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}

