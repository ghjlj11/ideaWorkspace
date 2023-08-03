package com.ghj.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class SavePageAsPDF {
    public static void main(String[] args) {

        String inputFilePath = "D:\\123456\\testPDF2.pdf";
        String outputFilePath = "D:\\123456\\testPDF3.pdf";
        String searchText = "#" + "user";
        String replacementText = "ghj";
        String content = "This is the content of the page.";

        try (PDDocument newDocument = new PDDocument()) {
            // Load the input PDF document
            File inputFile = new File(inputFilePath);
            PDDocument document = PDDocument.load(inputFile);

            // Create PDFTextStripper object to extract text from the document
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            // Get the content of the PDF as text
            String pdfContent = pdfTextStripper.getText(document);


            // Replace the target string with the replacement string
            content = pdfContent.replaceAll(searchText, replacementText);

            // Close the input document
            document.close();


            PDPage page = new PDPage();
            newDocument.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(newDocument, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(20, 700);
            contentStream.showText(content);
            contentStream.endText();
            contentStream.close();

            newDocument.save("D:\\123456\\testPDF3.pdf");
            System.out.println("PDF saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}