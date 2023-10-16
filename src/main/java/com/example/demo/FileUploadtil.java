package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadtil {

    public static void SaveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
        Path uploadPath = Paths.get(uploadDir);
        //System.out.println("UPLOAD: "+uploadPath);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("FilePath: "+filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("InputStream: " + inputStream);
        }catch(IOException ioe){
            throw new IOException("No se guardo la imagen: " + fileName, ioe);
        }
    }
}
