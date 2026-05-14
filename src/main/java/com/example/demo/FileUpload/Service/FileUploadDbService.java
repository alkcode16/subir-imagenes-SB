package com.example.demo.FileUpload.Service;

import com.example.demo.FileUpload.Classes.ResponseHandler;
import com.example.demo.FileUpload.Model.FileUploadModel;
import com.example.demo.FileUpload.Repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FileUploadDbService {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    private static final ResponseHandler responseHandler = new ResponseHandler();
    public FileUploadModel saveFile(FileUploadModel fileUploadModel){
        return fileUploadRepository.save(fileUploadModel);
    }

    public Optional<FileUploadModel> getFileById(Long id){
        return fileUploadRepository.findById(id);
    }

    public ResponseEntity<Object> getFilesByBody(FileUploadModel file) {
           FileUploadModel files = (FileUploadModel) fileUploadRepository.findAll(Example.of(file));

               return responseHandler.generateResponse("Encontrado", HttpStatus.OK, files);

    }

    public ResponseEntity<Object> getPrueba(String folio, String fec_documento) {
        FileUploadModel registro = fileUploadRepository.getFilesInfoByParams(folio,fec_documento);
        if (registro == null){
            return responseHandler.generateResponse("No encontrado", HttpStatus.NOT_FOUND, null);
        }else {
            return responseHandler.generateResponse("Encontrado", HttpStatus.OK, registro);
        }
    }


}
