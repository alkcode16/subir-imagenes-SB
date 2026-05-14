package com.example.demo.FileUpload.Controller;

import com.example.demo.FileUpload.Classes.ResponseHandler;
import com.example.demo.FileUpload.Model.FileUploadModel;
import com.example.demo.FileUpload.Service.FileUploadDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = {"/archivos"})
public class FileUploadDbController {

    private static final ResponseHandler responseHandler = new ResponseHandler();
    @Autowired
    private FileUploadDbService fileUploadDbService;

    @PostMapping("/save/ventanilla")
    public void add(@RequestBody FileUploadModel fileUploadModel) {
        fileUploadDbService.saveFile(fileUploadModel);
    }

    @GetMapping("/{id}")
    public Optional<FileUploadModel> getFileById(@PathVariable("id") Long idFile) {
       return fileUploadDbService.getFileById(idFile);
    }

    @GetMapping("/datos")
    public ResponseEntity<Object> prueba(@RequestParam String folio, @RequestParam String fecDocumento){
        return fileUploadDbService.getPrueba(folio,fecDocumento);
    }

}
