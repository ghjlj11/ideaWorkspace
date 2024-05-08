import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.ghj.constant.PathConstant.PATH;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/3/13 16:29
 */
public class Test0313 {
    static String filePath = "C:\\Users\\86187\\Desktop\\物件號信息.xlsx";
    static String outPutPath = "C:\\Users\\86187\\Desktop\\ttttt.xlsx";
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Workbook outWorkBook = new XSSFWorkbook();
        Sheet outWorkBookSheet = outWorkBook.createSheet("列转行");
        Row outRow = outWorkBookSheet.createRow(0);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            Cell inCell = row.getCell(0);
            String cellValue = inCell.getStringCellValue();
            Cell outCell = outRow.createCell(i);
            outCell.setCellValue(cellValue);
        }
        inputStream.close();
        FileOutputStream fileOutputStream = new FileOutputStream(outPutPath);
        outWorkBook.write(fileOutputStream);
    }
}
