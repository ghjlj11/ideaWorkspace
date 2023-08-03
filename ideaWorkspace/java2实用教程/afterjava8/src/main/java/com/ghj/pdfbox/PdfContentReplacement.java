package com.ghj.pdfbox;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author guohuanjun
 * @date 2023/7/24  16:30
 */
public class PdfContentReplacement {
    public static void main(String[] args) {
        String inputFilePath = "D:\\123456\\testPDF2.pdf";
        String outputFilePath = "D:\\123456\\testPDF3.pdf";
        String searchText = "#" + "user";
        String replacementText = "ghj";

        try {
            // Load the input PDF document
            File inputFile = new File(inputFilePath);
            PDDocument document = PDDocument.load(inputFile);

            // Create PDFTextStripper object to extract text from the document
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            // Get the content of the PDF as text
            String pdfContent = pdfTextStripper.getText(document);

            // Replace the target string with the replacement string
            String modifiedContent = pdfContent.replaceAll(searchText, replacementText);

            // Close the input document
            document.close();

            // Create a new PDF document and page
            PDDocument newDocument = new PDDocument();
            PDPage page = new PDPage();
            newDocument.addPage(page);

            // Create a PDPageContentStream to write the modified content
            PDPageContentStream contentStream = new PDPageContentStream(newDocument, page);

            // Set font and size for the text
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Write the modified content to the new PDF
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(modifiedContent);
            contentStream.endText();

            // Close the content stream
            contentStream.close();

            // Save the new PDF with the modified content
            newDocument.save(outputFilePath);
            newDocument.close();

            System.out.println("PDF content replaced and saved to: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
