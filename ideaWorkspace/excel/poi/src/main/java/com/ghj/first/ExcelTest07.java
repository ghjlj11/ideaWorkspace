package com.ghj.first;

import static com.ghj.constant.PathConstant.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86187
 * 使用07版本的xlsx，xlsx文件没有最大行限制， 可以存放更多数据， 使用SXSSFWorkbook工作簿， 原理是把数据一条一条的写进文件里面去，
 * 所以不会占用很大的内存空间， 但是速度会慢很多。
 */
public class ExcelTest07 {

    public static String  s = "D:/my-study/ideaWorkspace/test/";
    public static void main(String[] args) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row1 = sheet.createRow(0);
        Cell cell = row1.createCell(0);
        cell.setCellValue("时间");
        row1.createCell(1).setCellValue("地点");

        Row row2 = sheet.createRow(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        row2.createCell(0).setCellValue(dateFormat.format(new Date()));
        row2.createCell(1).setCellValue("泰和");

        FileOutputStream outputStream = new FileOutputStream(PATH + "excelTest07.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("success!");

    }
}
