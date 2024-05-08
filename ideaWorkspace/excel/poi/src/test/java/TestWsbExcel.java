import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/6 18:43
 */
public class TestWsbExcel {

    @Test
    public void test () throws Exception {
        String inPath = "C:\\Users\\86187\\Desktop\\製程記錄 1.xlsx";
        FileInputStream inputStream = new FileInputStream(inPath);
        String outPath = "C:\\Users\\86187\\Desktop\\test001.xlsx";
        FileInputStream outStream = new FileInputStream(outPath);
        Workbook outBook = new XSSFWorkbook(outStream);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet outBookSheet = outBook.createSheet();
        Row row1 = outBookSheet.createRow(0);
        Cell cell = row1.createCell(0);
        cell.setCellValue("");
        Set<String> fieldNameSet = new HashSet<>();
        Sheet sheet = workbook.getSheet("配置内容");
        for (Row row : sheet) {
            //row.getCell();
        }
    }

}
