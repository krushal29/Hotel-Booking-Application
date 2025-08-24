package com.hmsapp.service;

import com.hmsapp.entity.Booking;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PDFGenerator {

    public void generatePdf(String  path, Booking booking ){
        try{
            PdfWriter writer=new PdfWriter(path);
            PdfDocument pdf=new PdfDocument(writer);
            Document document=new Document(pdf);

            // Create a table with 3 columns
            float[] columnWidths={2,1};
            Table table = new Table(columnWidths);

            // Header row
            table.addHeaderCell("Name");
            table.addHeaderCell("Mobile");


            // Data row
            table.addCell(booking.getName());
            table.addCell(booking.getMobile());



            document.add(table);

            document.close();
            System.out.println("PDF created successfully ");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
