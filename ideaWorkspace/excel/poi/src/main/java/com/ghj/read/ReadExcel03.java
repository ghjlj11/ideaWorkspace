package com.ghj.read;

import static com.ghj.constant.PathConstant.*;
import com.ghj.first.ExcelTest03;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 86187
 */
public class ReadExcel03 {
    public static void main(String[] args) throws IOException {
        //获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "excelTest.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        //通过Cell类型输出数据
        System.out.println(cell.getDateCellValue());
        inputStream.close();
    }
}
