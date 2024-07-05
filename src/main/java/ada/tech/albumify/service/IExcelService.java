package ada.tech.albumify.service;

import ada.tech.albumify.domain.entities.Album;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface IExcelService {

    XSSFWorkbook createFile(int userId);

    void addHeader(XSSFSheet spreadsheet, int lineNumber);

    void addCell(Row line, int column, String value);

    void addCell(Row line, int column, int value);
}
