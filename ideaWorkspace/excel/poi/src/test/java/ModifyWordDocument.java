import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModifyWordDocument {

    static final String ONE = "11111111111";
    static final String TWO = "22222222222";
    static final String EXCEL_CODE = "#178";
    static final String REDMINE_CODE = "20443";
    static final String EXCEL_PATH = "D:/my-study/ideaWorkspace/test/outTest.xls";
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\86187\\Desktop\\#1_Walsin_L3L4_制造中台冷精车间SASD_part1 - 副本.docx");
            XWPFDocument document = new XWPFDocument(fis);
            FileInputStream inputStream = new FileInputStream(EXCEL_PATH);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex = 12;
            int flag = 0;
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    Row row;
                    if (text != null && text.contains(EXCEL_CODE)) {
                        row = sheet.getRow(rowIndex);
                        if (row != null) {
                            text = text.replace(EXCEL_CODE, row.getCell(0).getStringCellValue());
                            run.setText(text, 0);
                        }
                        flag ++;
                    }
                    else if (text != null && text.contains(REDMINE_CODE)) {
                        row = sheet.getRow(rowIndex);
                        if (row != null) {
                            Cell cell = row.getCell(1);
                            CellType cellType = cell.getCellType();
                            if (CellType.STRING.equals(cellType)) {
                                text = text.replace(EXCEL_CODE, cell.getStringCellValue());
                                run.setText(text, 0);
                            }
                            else if (CellType.NUMERIC.equals(cellType)) {
                                text = text.replace(EXCEL_CODE, String.valueOf(cell.getNumericCellValue()));
                                run.setText(text, 0);
                            }
                        }
                        flag ++;
                    }
                    else if (text != null && text.contains(ONE)) {
                        row = sheet.getRow(rowIndex);
                        if (row != null) {
                            text = text.replace(ONE, row.getCell(2).getStringCellValue());
                            run.setText(text, 0);
                        }
                        flag ++;
                    }
                    else if (text != null && text.contains(TWO)) {
                        row = sheet.getRow(rowIndex);
                        if (row != null) {
                            text = text.replace(TWO, row.getCell(3).getStringCellValue());
                            run.setText(text, 0);
                        }
                        flag ++;
                    }
                    if (flag > 0 && flag % 4 == 0) {
                        rowIndex ++;
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream("D:/my-study/ideaWorkspace/test/modified.docx");
            document.write(fos);
            fos.close();
            fis.close();
            System.out.println("Word document modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}