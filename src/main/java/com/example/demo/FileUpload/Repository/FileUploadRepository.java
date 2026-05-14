
package com.example.demo.FileUpload.Repository;

import com.example.demo.FileUpload.Model.FileUploadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUploadModel, Long> {
    @Query(value = "SELECT * FROM datos_ventanilla \n" +
        "WHERE folio = ?1 AND fec_documento = ?2",
        nativeQuery = true)
    FileUploadModel getFilesInfoByParams(String folio, String fec_documento);
}
