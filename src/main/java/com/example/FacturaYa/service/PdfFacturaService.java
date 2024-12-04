package com.example.FacturaYa.service;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.entity.Cliente;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@Service
public class PdfFacturaService {

    public void generatePdf(Factura factura) throws IOException {
        // Crear un nuevo documento PDF
        PDDocument document = new PDDocument();

        // Crear una nueva página en el documento
        PDPage page = new PDPage();
        document.addPage(page);

        // Crear un flujo de contenido para la página
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Comenzar a escribir en el documento
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(25, 750); // Ajustar la posición inicial

        // Agregar contenido de la factura al PDF

        // Título de la factura
        contentStream.showText("Factura: " + factura.getCodigo());
        contentStream.newLine();

        // Cliente
        Cliente cliente = factura.getCliente();
        contentStream.showText("Cliente: " + (cliente != null ? cliente.getNombre() : "Desconocido"));
        contentStream.newLine();

        // Fecha de la factura
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = sdf.format(factura.getFecha());
        contentStream.showText("Fecha: " + fechaFormateada);
        contentStream.newLine();

        // Subtotal, impuestos y total
        DecimalFormat df = new DecimalFormat("#,###.00");
        contentStream.showText("Subtotal: " + df.format(factura.getSubtotal()));
        contentStream.newLine();
        contentStream.showText("Impuestos: " + df.format(factura.getTotalImpuestos()));
        contentStream.newLine();
        contentStream.showText("Total: " + df.format(factura.getTotal()));
        contentStream.newLine();

        // Estado de la factura
        contentStream.showText("Estado: " + factura.getEstado());
        contentStream.newLine();

        // Cerrar el flujo de contenido
        contentStream.endText();

        // Cerrar el flujo de contenido
        contentStream.close();

        // Guardar el documento PDF en un archivo
        String dest = "Factura_" + factura.getCodigo() + ".pdf"; // Ruta y nombre del archivo
        document.save(new File(dest));

        // Cerrar el documento
        document.close();

        System.out.println("Factura generada con éxito: " + dest);
    }
}
