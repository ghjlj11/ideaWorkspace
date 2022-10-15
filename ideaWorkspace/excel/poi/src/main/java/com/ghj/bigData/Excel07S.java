package com.ghj.bigData;

import com.ghj.first.ExcelTest03;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import static com.ghj.constant.PathConstant.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 86187
 * 使用SXSSFWorkbook来实现工作簿，XSSFWorkbook是采用一条一条的写入的策略， HSSFWorkbook是采用全部写入到缓存然后再写入的策略，各有优点与弊端
 * 而SXSSFWorkbook是先读取存储固定的大小的数据到内存中，然后再写入到文件里面， 这样会比XSSFWorkbook更快一点 ，但是会生成临时文件。
 * 结果2.227秒
 */
public class Excel07S {
    public static void main(String[] args) throws IOException {
        long star = System.currentTimeMillis();
        Workbook workbook = new SXSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 66536; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0 ; j < 10 ; j ++){
                row.createCell(j).setCellValue(j + 1);
            }
        }
        FileOutputStream outputStream = new FileOutputStream(PATH + "bigData07S.xlsx");
        workbook.write(outputStream);
        boolean dispose = ((SXSSFWorkbook) workbook).dispose();

        System.out.println(dispose);
        long end = System.currentTimeMillis();
        System.out.println( (double)(end - star) / 1000);
    }
}
