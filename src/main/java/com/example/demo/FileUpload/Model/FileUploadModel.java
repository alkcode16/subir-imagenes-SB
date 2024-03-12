package com.example.demo.FileUpload.Model;

public class FileUploadModel {
    private String nombreArchivo;
    private String url;
    private String nombre;
    private String archivo;

    public FileUploadModel(String nombreArchivo, String url, String nombre, String archivo) {
        this.nombreArchivo = nombreArchivo;
        this.url = url;
        this.nombre = nombre;
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDirectorio(String directorio) {
        this.nombre = directorio;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
