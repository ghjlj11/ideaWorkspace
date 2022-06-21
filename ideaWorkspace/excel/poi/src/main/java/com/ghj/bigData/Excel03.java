package com.ghj.bigData;

import com.ghj.first.ExcelTest03;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 86187
 *使用03版本的HSSFWorkbook可以更快速的写入数据， 但是数据过多就会报错， 只能有65536行
 * 结果2.128秒
 */
public class Excel03 {
    public static void main(String[] args) throws IOException {
        long star = System.currentTimeMillis();
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 65536; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0 ; j < 10 ; j ++){
                row.createCell(j).setCellValue(j + 1);
            }
        }
        FileOutputStream outputStream = new FileOutputStream(ExcelTest03.s + "bigData03.xls");
        workbook.write(outputStream);
        long end = System.currentTimeMillis();
        //获取开始到结束的时间差
        System.out.println( (double)(end - star) / 1000);
    }
}
