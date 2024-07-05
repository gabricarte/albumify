package ada.tech.albumify.controllers;
import ada.tech.albumify.service.IExcelService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/albumify/excel")
@AllArgsConstructor


public class ExcelController {

    private IExcelService excelService;


    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> createFile(@PathVariable("userId") int userId) {
        XSSFWorkbook workbook = excelService.createFile(userId);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            workbook.write(baos);
            byte[] bytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "albumsList.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
