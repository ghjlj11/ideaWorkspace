package com.ghj.pdfbox;


import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * @author guohuanjun1
 * @date 2023/7/24 13:39
 */
public class TestApachePdf {
    public static void main(String[] args) throws IOException {
        String url = "D:\\123456\\testPDF.pdf";
        File file = new File(url);
        PDDocument pdDocument = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(pdDocument);
        System.out.println("全部内容-->");
        System.out.print(text);
        for (int pagenum = 1; pagenum <= pdDocument.getNumberOfPages(); pagenum++) {
            pdfTextStripper.setStartPage(pagenum);
            pdfTextStripper.setEndPage(pagenum);
            String text_page = pdfTextStripper.getText(pdDocument);
            System.out.println("分页读取-->第" + pagenum + "页");
            System.out.print(text_page);
        }
    }
}
