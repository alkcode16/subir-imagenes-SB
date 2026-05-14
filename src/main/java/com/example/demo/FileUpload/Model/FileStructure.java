package com.example.demo.FileUpload.Model;

public class FileStructure {
    private String carpeta;
    private String subCarpeta;
    private String archivo;

    public String getSubCarpeta() {
        return subCarpeta;
    }

    public void setSubCarpeta(String subCarpeta) {
        this.subCarpeta = subCarpeta;
    }

    public FileStructure(String carpeta, String archivo, String subCarpeta) {
        this.carpeta = carpeta;
        this.archivo = archivo;
        this.subCarpeta = subCarpeta;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
