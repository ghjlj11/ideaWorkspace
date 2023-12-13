import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test07 {

    static final String PATH = "C:/Users/86187/Desktop/華新麗華L3L4客开条目处置方案_刘华202311010.xls";
    static final String OUT_PATH = "D:/my-study/ideaWorkspace/test/outTest.xls";
    static final String MOULD_VALUE = "制造云";
    static final String ADVICE_VALUE = "正常推进";

    @Test
    public void test07() throws Exception {
        FileInputStream inputStream = new FileInputStream(PATH);
        Workbook workbook = new HSSFWorkbook(inputStream);

        Workbook outWorkbook = new XSSFWorkbook();

        Sheet outSheet = outWorkbook.createSheet();
        Sheet sheet = workbook.getSheet("2021客开合约履约状况");
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0, j = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            Cell mouldCell = row.getCell(2);
            Cell adviceCell = row.getCell(8);
            if (mouldCell != null && adviceCell != null) {

                String mouldValue = mouldCell.getStringCellValue();
                String adviceValue = adviceCell.getStringCellValue();
                if (MOULD_VALUE.equals(mouldValue) && ADVICE_VALUE.equals(adviceValue)) {
                    String outString = "";
                    Cell redmineCell = row.getCell(0);
                    Row outSheetRow = outSheet.createRow(j);
                    j += 1;
                    Cell cell1 = outSheetRow.createCell(0);
                    cell1.setCellValue("#" + (i + 1));
                    outString += "#" + (i + 1) + "        ";
                    Cell cell2 = outSheetRow.createCell(1);
                    CellType cellType = redmineCell.getCellType();
                    if (CellType.STRING.equals(cellType)) {
                        outString += redmineCell.getStringCellValue();
                        cell2.setCellValue(redmineCell.getStringCellValue());
                    }
                    else if (CellType.NUMERIC.equals(cellType)) {
                        outString += redmineCell.getNumericCellValue();
                        cell2.setCellValue(redmineCell.getNumericCellValue());
                    }
                    Cell cell3 = outSheetRow.createCell(2);
                    cell3.setCellValue(row.getCell(4).getStringCellValue());
                    Cell cell4 = outSheetRow.createCell(3);
                    cell4.setCellValue(row.getCell(5).getStringCellValue());
                    System.out.println(outString);
                }
            }
        }
        FileOutputStream outputStream = new FileOutputStream(OUT_PATH);
        outWorkbook.write(outputStream);
    }
}
