package com.ghj.first;

import static com.ghj.constant.PathConstant.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86187
 * 使用的是03版本的xls，创建工作簿使用HSSFWorkbook实现，xls文件有最大行数是65536， 其工作原理是先把数据全部写到内存中， 然后全部写进文件
 * 速度很快， 但是占用很大的内存。
 */
public class ExcelTest03 {

    public static void main(String[] args) throws IOException {
        //获取到工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建表格
        Sheet sheet = workbook.createSheet();
        //创建行
        Row row1 = sheet.createRow(0);
        //创建每行的小格
        Cell cell = row1.createCell(0);
        //为小格设置值
        cell.setCellValue("时间");
        row1.createCell(1).setCellValue("地点");

        Row row2 = sheet.createRow(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        row2.createCell(0).setCellValue(dateFormat.format(new Date()));
        row2.createCell(1).setCellValue("泰和");

        FileOutputStream outputStream = new FileOutputStream(PATH + "excelTest.xls");
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("success!");

    }
    public void test07(){}
}
