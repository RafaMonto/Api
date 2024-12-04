package com.example.FacturaYa.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import com.example.FacturaYa.entity.Factura;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class PdfFacturaService {

    public ByteArrayOutputStream generatePdf(Factura factura) throws FileNotFoundException {
        // Definir la ruta de salida del PDF
        String dest = "Factura_" + factura.getCodigo() + ".pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Formateo de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(factura.getFecha());

        // Formateo de BigDecimal
        DecimalFormat df = new DecimalFormat("#,###.00");
        String subtotalFormateado = df.format(factura.getSubtotal());
        String impuestosFormateados = df.format(factura.getTotalImpuestos());
        String totalFormateado = df.format(factura.getTotal());

        // Agregar contenido al PDF
        document.add(new Paragraph("Factura: " + factura.getCodigo()));
        document.add(new Paragraph("Cliente: " + (factura.getCliente() != null ? factura.getCliente().getNombre() : "Desconocido")));
        document.add(new Paragraph("Fecha: " + fechaFormateada));
        document.add(new Paragraph("Subtotal: " + subtotalFormateado));
        document.add(new Paragraph("Impuestos: " + impuestosFormateados));
        document.add(new Paragraph("Total: " + totalFormateado));
        document.add(new Paragraph("Estado: " + factura.getEstado()));

        // Cerrar el documento PDF
        document.close();

        System.out.println("Factura generada con éxito: " +dest);
        return null;
    }
}