package com.ghj.bigData;

import com.ghj.first.ExcelTest03;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 86187
 * 使用07版本的可以写入更多的数据，但是速度会更慢， 因为是一条一条的写入到文件。
 * 结果5.521秒
 */
public class Excel07 {
    public static void main(String[] args) throws IOException {
        long star = System.currentTimeMillis();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 66536; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0 ; j < 10 ; j ++){
                row.createCell(j).setCellValue(j + 1);
            }
        }
        FileOutputStream outputStream = new FileOutputStream(ExcelTest03.s + "bigData07.xlsx");
        workbook.write(outputStream);
        long end = System.currentTimeMillis();
        System.out.println( (double)(end - star) / 1000);
    }
}
