package ada.tech.albumify.service;

import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.repositories.IAlbumUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ExcelService implements IExcelService {


    private final IAlbumUserRepository albumUserRepository;

    private List<Album> getAlbumsByUserId(int userId){
        List<Album> albums = albumUserRepository.findAlbumsByUserId(userId);
        return albums;
    }

    @Override
    public XSSFWorkbook createFile(int userId) {

        List<Album> albums = getAlbumsByUserId(userId);
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Album List");
        int lineNumber = 0;

        addHeader(sheet, lineNumber++);

        for (Album album : albums) {
            var row = sheet.createRow(lineNumber++);
            addCell(row, 0, album.getName());
            addCell(row , 1, album.getArtist());
            addCell(row, 2, album.getSummary());
        }

        return workbook;
    }
    @Override
    public void addHeader(XSSFSheet sheet, int lineNumber) {
        var row = sheet.createRow(lineNumber);
        addCell(row, 0, "Name");
        addCell(row, 1, "Artist");
        addCell(row, 2, "Summary");
    }

    @Override
    public void addCell(Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }

    @Override
    public void addCell(Row row, int column, int value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }
}
