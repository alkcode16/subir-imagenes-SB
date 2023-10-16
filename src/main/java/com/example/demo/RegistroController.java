package com.example.demo;

import java.io.File;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class RegistroController {

    @GetMapping("/prueba1")
    public String Hola(){
        return "Hola";
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
}
