package com.example.demo.FileUpload.Service;

import com.example.demo.FileUpload.Classes.ResponseHandler;
import com.example.demo.FileUpload.Model.FileStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class FileUploadService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final ResponseHandler responseHandler = new ResponseHandler();

    public void save(MultipartFile multipartFile, String uploadDir, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Archivos guardados");

        } catch (IOException ioe) {
            logger.error("No se guardaron los archivos");
//            return responseHandler.generateResponse("No se guardaron los arhivos", HttpStatus.CONFLICT, null);
            throw new RuntimeException("No se guardaron los arhivos");
        }
    }

    public ResponseEntity<Object> getFilesByRfc(String rfc){
        List<FileStructure> listaArchivos = new ArrayList<>();
        File dir = new File("D:/Documentos/" + rfc);
//        File dir = new File("/media/compartida/" + rfc);
        String[] listado = dir.list();
        if (listado == null || listado.length == 0) {
            logger.warn("No se encontraron archivos del externo " + rfc);
            return responseHandler.generateResponse("No se encontraron archivos del externo " + rfc, HttpStatus.NOT_FOUND, null);
        } else {
            for (String s : listado) {
                System.out.println("Los archivos: " + s);
//                listaArchivos.add(new FileStructure("D:/Documentos/"+rfc, s, rfc));
                listaArchivos.add(new FileStructure("/media/compartida/"+rfc, s, rfc));
            }
            logger.info("Expediente del externo "+rfc+" encontrado");
            return responseHandler.generateResponse("Expediente del externo "+rfc+" encontrado", HttpStatus.OK, listaArchivos);
        }
    }

    public ResponseEntity<Object> searchDirectory() {
        File file = new File("D:/Documentos");
//        File file = new File("/media/compartida/");
        List<String> namesOfDirectories = new ArrayList<>();
        String[] names = file.list();
        assert names != null;
        for (String name : names){
            if(new File("D:/Documentos").isDirectory()){
//            if(new File("/media/compartida/").isDirectory()){
                namesOfDirectories.add((name));
            }
        }
        System.out.println(namesOfDirectories);
        logger.info("Directorios encontrados: " + namesOfDirectories.toString());
        return responseHandler.generateResponse("Encontrados", HttpStatus.OK, namesOfDirectories);
    }
    public ResponseEntity<Object> deleteFileByRfc(String rfc,  String fileName){
//        File file = new File("D:/Documentos/" + rfc +"/"+ fileName);
        File file = new File("/media/compartida/" + rfc +"/"+ fileName);
        try {
            if (file.delete()) {
                logger.info("Archivo " + file.getName()+" borrado exitosamente");
                return responseHandler.generateResponse("El archivo " + file.getName() + " fué borrado exitosamente", HttpStatus.OK, null);
            } else {
                logger.error("Archivo " + file.getName()+" no fué borrado");
                return responseHandler.generateResponse("El archivo " + file.getName() + " no fué borrado", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return responseHandler.generateResponse("El archivo " + file.getName() + "Error al borrar", HttpStatus.CONFLICT, null);
        }
    }


}
