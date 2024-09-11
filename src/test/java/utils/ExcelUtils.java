package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {

    public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < rowCount; i++) {  // Start from 1 to skip the header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
            }
        }

        workbook.close();
        return data;
    }
}
