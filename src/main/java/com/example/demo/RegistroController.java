package com.example.demo;

import java.io.File;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistroController {

    @GetMapping("/prueba1")
    public String Hola(){
        return "Hola";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/archivos/{rfc}")
    public List<Expediente> mostrarArchivos(@PathVariable("rfc") String carpeta){

        List<Expediente> listaArchivos = new ArrayList<>();
        String carpetaLectura = System.getProperty("user.dir");
        File dir = new File(carpetaLectura+"/Documentos/"+ carpeta);
        String[] listado = dir.list();

        System.out.println("======>"+carpetaLectura);

        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");

        } else {
            for (int i=0; i< listado.length; i++) {
                System.out.println("======>"+listado[i]);
                listaArchivos.add( new Expediente(listado[i], "/Documentos/"+ carpeta +"/" + listado[i], carpeta,listado[i]));
            }

        }
        //System.out.println("======>"+listaArchivos);
        return listaArchivos;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/archivo/data")
    public ResponseEntity<byte[]> getArchivo(@RequestParam String carpeta, @RequestParam String file){
        byte[] archivo = new byte[0];
        String base  = "Documentos/";
        String extensionFile = FilenameUtils.getExtension(file);
        //MediaType tipo = new MediaType("");

        System.out.println("Esta es la extension: =>"+extensionFile);
        try{
            System.out.println(base + carpeta + "/" + file);
            archivo = FileUtils.readFileToByteArray(new File(base+ carpeta+"/"+file));

        }catch(IOException e){
            e.printStackTrace();
        }

        /*if(extensionFile == "pdf"){
            tipo = MediaType.APPLICATION_PDF;
            //return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(archivo);
        } else if (extensionFile == "png") {
            tipo = MediaType.IMAGE_PNG;
            //return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(archivo);
        } else if (extensionFile == "jpg") {
            tipo = MediaType.IMAGE_JPEG;
            //return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(archivo);
        }

        System.out.println("Este es el tipo:"+tipo);*/

        //return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(tipo).body(archivo);
        return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(archivo);
    }


    //@CrossOrigin(origins = "*")
    @GetMapping("/imagen/{filename}")
    public ResponseEntity<byte[]> getImagen(@PathVariable("filename") String filename){
        byte[] imagen = new byte[0];
        System.out.println("============>"+ filename);
        try{
            imagen = FileUtils.readFileToByteArray(new File("/imagen/" + filename));
            System.out.println("============>"+imagen);
        }catch(IOException e){
            e.printStackTrace();
        }

        return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.IMAGE_PNG);
        //return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF);
    }


    //Guardar registro con un archivo
    @CrossOrigin(origins = "*")
    @PostMapping("/save/imagen")
    public void saveUser(@RequestParam("registro") String strRegistro, @RequestParam("fichero") MultipartFile multipartFile) throws IOException{

       System.out.println("ESTO LLEGO" + strRegistro);
       String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       //String filename1 = StringUtils.cleanPath(multipartFile.getContentType());
       System.out.println("!!"+filename);
       //System.out.println("!!"+filename1);

       Gson gson = new Gson();
       Registro registro = gson.fromJson(strRegistro, Registro.class);

       String uploadDir = "Documentos/"+registro.getNombre();

       System.out.println(registro.getNombre());
       System.out.println(registro.getApellidos());

       registro.setImagen(filename);
       System.out.println(registro.getImagen());

       //Guardamos la imagen
       FileUploadtil.SaveFile(uploadDir, filename, multipartFile); 

    }


    //Guardar multiples archivos

    @CrossOrigin(origins = "*")
    @PostMapping("/bolsatrabajo")
    // @ResponseBody
    
    public void saveBolsaTabajo(@RequestParam("registro") String strRegistro, @RequestParam("fichero") MultiValueMap<String, MultipartFile> multiFileMap) throws IOException{
        //public void saveBolsaTabajo(@RequestBody Registro registro, @RequestParam("fichero") MultiValueMap<String, MultipartFile> multiFileMap) throws IOException{
        //public saveBolsaTabajo(@RequestBody Bolsa bolsa){

        // System.out.println(bolsa);

        MultipartFile[] files = multiFileMap.values().stream().flatMap(values-> values.stream()).toArray(MultipartFile[] :: new);

        System.out.println(files);

        Gson gson = new Gson();

        Registro registro = gson.fromJson(strRegistro, Registro.class);

        String uploadDir = "Documentos/"+registro.getNombre();

        System.out.println(registro.getNombre());
        System.out.println(registro.getApellidos());

        
        for(MultipartFile multipartFile: files){
            String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println("REGISTRO DEL FOR =======> "+filename);
            registro.setImagen(filename);

            System.out.println(registro.getImagen());

            FileUploadtil.SaveFile(uploadDir, filename, multipartFile);
        }

    }

}
