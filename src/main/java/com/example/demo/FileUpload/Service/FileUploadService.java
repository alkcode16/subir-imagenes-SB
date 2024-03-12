package com.example.demo.FileUpload.Service;

import com.example.demo.FileUpload.Classes.ResponseHandler;
import com.example.demo.FileUpload.Model.FileUploadModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {

    private static final ResponseHandler responseHandler = new ResponseHandler();

    public ResponseEntity<Object> SaveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
            return responseHandler.generateResponse("Nuevo expediente creado", HttpStatus.OK, null);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return responseHandler.generateResponse("Expediente actualizado", HttpStatus.OK, null);
        } catch (IOException ioe) {
            return responseHandler.generateResponse("Error en la sulicitud", HttpStatus.NOT_MODIFIED, null);
        }
    }

    public void save(MultipartFile multipartFile, String uploadDir, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new RuntimeException();
        }
    }

    public ResponseEntity<Object> getFilesByRfc(String rfc){
        List<FileUploadModel> listaArchivos = new ArrayList<>();
        File dir = new File("D:/Documentos/" + rfc);
        String[] listado = dir.list();
        if (listado == null || listado.length == 0) {
            return responseHandler.generateResponse("No se encontraron archivos del externo " + rfc, HttpStatus.NOT_FOUND, null);
        } else {
            for (String s : listado) {
                listaArchivos.add(new FileUploadModel(s, "/D:/Documentos/" + rfc + "/" + s, rfc, s));
            }
            return responseHandler.generateResponse("Expediente del externo "+rfc+" encontrado", HttpStatus.OK, listaArchivos);
        }
    }

    public ResponseEntity<Object> searchDirectory() {
        File file = new File("D:/Documentos");
        List<String> namesOfDirectories = new ArrayList<>();
        String[] names = file.list();
        for (String name : names){
            if(new File("D:/Documentos").isDirectory()){
                namesOfDirectories.add((name));
            }
        }
        return responseHandler.generateResponse("OC", HttpStatus.OK, namesOfDirectories);
    }
    public ResponseEntity<Object> deleteFileByRfc(String rfc,  String fileName){
        File file = new File("D:/Documentos/" + rfc +"/"+ fileName);
        try {
            if (file.delete()) {
                return responseHandler.generateResponse("El archivo " + file.getName() + " fué borrado exitosamente", HttpStatus.OK, null);
            } else {
                return responseHandler.generateResponse("El archivo " + file.getName() + " no fué borrado", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return responseHandler.generateResponse("El archivo " + file.getName() + "Error al borrar", HttpStatus.CONFLICT, null);
        }
    }

    public ResponseEntity<Object> deleteDirByRfc(String directory){
        File file = new File("D:/Documentos/" + directory);
        System.out.println(file);
        try {
            if (file.delete()) {
                return responseHandler.generateResponse("La carpeta " + file.getName() + "borrada exitosamente", HttpStatus.OK, null);
            } else {
                return responseHandler.generateResponse("La carpeta " + file.getName() + " no fué borrada", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return responseHandler.generateResponse("La carpeta " + file.getName() + " no se borró debido a un error inesperado", HttpStatus.CONFLICT, null);
        }
    }
}
