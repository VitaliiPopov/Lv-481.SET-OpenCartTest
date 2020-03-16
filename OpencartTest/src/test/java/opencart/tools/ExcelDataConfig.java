package opencart.tools;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelDataConfig {

    private File file;
    private FileInputStream fileInputStream;
    private XSSFWorkbook workBook;
    private XSSFSheet sheet1;

    //src\main\resourcesTestData.xlsx
    public ExcelDataConfig(String excelPath) {
        try {
            file = new File(excelPath);
            fileInputStream = new FileInputStream(file);
            workBook = new XSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String  getData(int sheetNumber, int row, int column){
        sheet1 = workBook.getSheetAt(sheetNumber);
        return sheet1.getRow(row).getCell(column).getStringCellValue();
    }

}
