package com.ghj.read;

import static com.ghj.constant.PathConstant.*;
import com.ghj.first.ExcelTest03;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86187
 * 07同理
 */
public class ReadExcel07 {
    public static void main(String[] args) throws IOException {
        //获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "testType.xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell1 = row.getCell(5);
        cell1.setCellType(CellType.BOOLEAN);
        System.out.println(cell1.getBooleanCellValue());
        //cell.setCellType(CellType.STRING);
        //cell.setCellValue(new Date());
        //通过Cell类型输出数据
        inputStream.close();
    }
}
