package com.example.demo.FileUpload.Controller;
import com.example.demo.FileUpload.Classes.ResponseHandler;
import com.example.demo.FileUpload.Model.FileUploadModel;
import com.example.demo.FileUpload.Service.FileUploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = {"/expediente"})
public class FileUploadController {
    private static final FileUploadService fileUploadService = new FileUploadService();
    private static final ResponseHandler responseHandler = new ResponseHandler();
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/archivos/{rfc}")
    public ResponseEntity<Object> mostrarArchivos(@PathVariable("rfc") String carpeta) {
            return fileUploadService.getFilesByRfc((carpeta));
    }

    @CrossOrigin("*")
    @GetMapping("/directories")
    public ResponseEntity<Object> getAllDirectories(){
        return fileUploadService.searchDirectory();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/archivo/data")
    public ResponseEntity<byte[]> getArchivo(@RequestParam String carpeta, @RequestParam String file){
        byte[] archivo = new byte[0];
        String base  = "D:/Documentos/";
        String extensionFile = FilenameUtils.getExtension(file);
        try{
            System.out.println(base + carpeta + "/" + file);
            archivo = FileUtils.readFileToByteArray(new File(base+ carpeta+"/"+file));

        }catch(IOException e){
            e.printStackTrace();
        }
        return (ResponseEntity<byte[]>) ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(archivo);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save/files")
    public ResponseEntity<Object> saveMultipleFiles(@RequestParam("registro") String strRegistro, @RequestParam("fichero") MultipartFile[] multipartFiles) {
        List<String> fileNames = new ArrayList<>();
        Gson gson = new Gson();
                FileUploadModel registro = gson.fromJson(strRegistro, FileUploadModel.class);
                String uploadDir = "D:/Documentos/" + registro.getNombre();
                System.out.println("Directorio: "+uploadDir);
                Arrays.asList(multipartFiles).stream().forEach(file -> {
                String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                registro.setArchivo(filename);
                try {
                    fileUploadService.save(file,uploadDir,filename);
                    fileNames.add(file.getOriginalFilename());
                } catch (IOException e) {
//                    throw new RuntimeException("" + e);
                }
            });
            return responseHandler.generateResponse("Exito en el envio de archivos", HttpStatus.OK, fileNames);
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping()
    public ResponseEntity<Object> deleteFile(@RequestParam String rfc, @RequestParam String fileName){
        return fileUploadService.deleteFileByRfc(rfc, fileName);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/dir")
    public ResponseEntity<Object> deleteDirectory(@RequestParam String rfc){
        return fileUploadService.deleteDirByRfc(rfc);
    }

}
