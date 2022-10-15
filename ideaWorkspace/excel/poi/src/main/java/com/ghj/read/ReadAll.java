package com.ghj.read;

import static com.ghj.constant.PathConstant.*;
import com.ghj.first.ExcelTest03;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86187
 * 获取表格里的所有数据， 同swich case判断是哪种类型， 然后获取对应的类型输出。
 */
public class ReadAll {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(PATH + "testType.xlsx");

        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            int l = row.getPhysicalNumberOfCells();
            for (int j = 0; j < l; j++) {
                Cell cell = row.getCell(j);
                String value = "";
                CellType type = cell.getCellType();
                switch (type){
                    case STRING:
                        //String类型
                        System.out.print("【String】");
                        value = cell.getStringCellValue();
                        break;
                    case BOOLEAN:
                        //boolean类型
                        System.out.print("【BOOLEAN】");
                        value = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case BLANK:
                        //是空的
                        System.out.print("【BLANK】");
                        break;
                    case NUMERIC:
                        //数字类型
                        //通过DateUtil判断是否是Date类型
                        if(DateUtil.isCellDateFormatted(cell)){
                            System.out.print("【DATE】");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = cell.getDateCellValue();
                            value = dateFormat.format(date);
                        } else {
                            //否则就是数字类型
                            System.out.print("【NUMERIC】");
                            value = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    default:
                        System.out.println("出错啦！");
                        break;
                }
                //输出值
                System.out.println("  " + value);
            }
        }
    }
}
