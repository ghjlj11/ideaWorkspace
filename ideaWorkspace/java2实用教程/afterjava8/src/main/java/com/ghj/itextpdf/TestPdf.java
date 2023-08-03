package com.ghj.itextpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import static com.itextpdf.text.html.HtmlTags.FONT;

/**
 * @author guohuanjun1
 * @date 2023/7/20 15:23
 */
public class TestPdf {

    public static void main(String[] args) throws Exception {

        //创建文档对象
        Document document = new Document();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\my-study\\ideaWorkspace\\test\\pdf\\example1.pdf");
        //设置输出流
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);

        //打开文档
        document.open();

        // 中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //BaseFont bfChinese = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 加粗35号
        Font fontBold35 = new Font(bfChinese, 35, Font.BOLD);
        // 加粗18号
        Font fontBold18 = new Font(bfChinese, 18, Font.BOLD);
        // 常规12
        Font fontNormal12 = new Font(bfChinese, 12, Font.NORMAL);
        // 加粗12
        Font fontBold12 = new Font(bfChinese, 12, Font.BOLD);


        Paragraph paragraph = new Paragraph();
        Chunk chunkTop1 = new Chunk("Walsin", fontBold35);
        Chunk chunkTop2 = new Chunk("                                     ");
        Chunk chunkTop3 = new Chunk("文件編號: " + "EMR202307070000069", fontBold12);
        paragraph.add(chunkTop1);
        paragraph.add(chunkTop2);
        paragraph.add(chunkTop2);
        paragraph.add(chunkTop3);
        //向文档中添加内容
        document.add(paragraph);

//        Paragraph paragraph1 = new Paragraph("文件编号: " + "EMR202307070000069", fontBold12);
//        paragraph1.setAlignment(Element.ALIGN_RIGHT);
//        document.add(paragraph1);

        // 图片
//        Image image = Image.getInstance("D:\\Pictures\\Saved Pictures\\wallhaven-v93z13.jpg");
//        image.setAlignment(Image.ALIGN_LEFT);
//        //设置图片相对大小
//        image.scalePercent(10);
//
//        document.add(image);

        // 3列的表.
        PdfPTable table = new PdfPTable(15);
        // 宽度100%填充
        table.setWidthPercentage(100);
        // 前间距
        table.setSpacingBefore(10f);
        // 后间距
        table.setSpacingAfter(10f);

        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f};
        //table.setWidths(columnWidths);

        //行1
        PdfPCell[] cells1 = new PdfPCell[15];
        PdfPRow row1 = new PdfPRow(cells1);

        //单元格
        //单元格内容
        cells1[0] = new PdfPCell(new Paragraph("簽       呈", fontBold18));
        //边框验证
        //cells1[0].setBorderColor(BaseColor.BLUE);
        //左填充20
        //cells1[0].setPaddingLeft(20);
        setCell(cells1[0], 3, 50, 1.5f, true);
        


        cells1[3] = new PdfPCell(new Paragraph("日期: \n\n" + LocalDate.now(), fontNormal12));
        setCell(cells1[3], 3, 40, 1.5f, true);

        cells1[6] = new PdfPCell(new Paragraph("单位: \n\n" + "烟台华新", fontNormal12));
        setCell(cells1[6], 3, 40, 1.5f, true);

        cells1[9] = new PdfPCell(new Paragraph("答辯人: \n\n" + "jack", fontNormal12));
        setCell(cells1[9], 3, 40, 1.5f, true);

        cells1[12] = new PdfPCell(new Paragraph("分機: \n\n", fontNormal12));
        setCell(cells1[12], 3, 40, 1.5f, true);


        //行2
        PdfPCell[] cells2 = new PdfPCell[15];
        PdfPRow row2 = new PdfPRow(cells2);
        cells2[0] = new PdfPCell();
        Chunk chunk1 = new Chunk("主旨: ", fontBold12);
        Chunk chunk2 = new Chunk("  请购眼模", fontNormal12);
        Paragraph elements = new Paragraph();
        elements.add(chunk1);
        elements.add(chunk2);
        cells2[0].addElement(elements);
        cells2[0].addElement(new Paragraph("说明: ", fontBold12));
        setCell(cells2[0], 15, 400, 1.5f, false);


        // 行3
        PdfPCell[] cells3 = new PdfPCell[15];
        PdfPRow row3 = new PdfPRow(cells3);

        cells3[0] = new PdfPCell(new Paragraph("發起人", fontNormal12));
        setCell(cells3[0], 5, 30, 1.5f, true);
        cells3[5] = new PdfPCell(new Paragraph("審批人", fontNormal12));
        setCell(cells3[5], 5, 30, 1.5f, true);
        cells3[10] = new PdfPCell(new Paragraph("抄送人", fontNormal12));
        setCell(cells3[10], 5, 30, 1.5f, true);

        // 行4

        PdfPCell[] cells4 = new PdfPCell[15];
        PdfPRow row4 = new PdfPRow(cells4);

        cells4[0] = new PdfPCell(new Paragraph("同意", fontNormal12));
        setCell(cells4[0], 5, 130, 1.5f, true);
        cells4[5] = new PdfPCell(new Paragraph());
        setCell(cells4[5], 5, 130, 1.5f, true);
        cells4[10] = new PdfPCell(new Paragraph("會簽完成", fontNormal12));
        setCell(cells4[10], 5, 130, 1.5f, true);


        //把第行添加到集合
        listRow.add(row1);
        listRow.add(row2);
        listRow.add(row3);
        listRow.add(row4);
        //把表格添加到文件中
        document.add(table);



        //关闭文档
        document.close();
        InputStream in = new ByteArrayInputStream(outputStream.toByteArray());
        byte[] bytes = new byte[10240];

    }

    /**
     * 设置表格中的单元格属性
     * @param cell 单元格
     * @param colspan 合并几个单元格
     * @param fixedHeight 行高
     * @param borderWidth 边框宽度
     * @param textCenter 居中
     */
    public static void setCell(PdfPCell cell, int colspan, float fixedHeight, float borderWidth, boolean textCenter){
        // 合并格子
        cell.setColspan(colspan);
        // 行高
        cell.setFixedHeight(fixedHeight);
        // 边框
        cell.setBorderWidth(borderWidth);
        // 居中
        if(textCenter){
            // 水平
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 垂直
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        }
    }
}
