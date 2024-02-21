package com.example.demo;

public class Expediente {

    private String nombreArchivo;
    private String url;
    private String directorio;
    private String archivo;

    public Expediente(String nombreArchivo, String url, String directorio, String archivo) {
        this.nombreArchivo = nombreArchivo;
        this.url = url;
        this.directorio = directorio;
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

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
