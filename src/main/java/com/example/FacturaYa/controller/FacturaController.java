package com.example.FacturaYa.controller;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.service.FacturaService;
import com.example.FacturaYa.service.XmlFacturaService;
import com.example.FacturaYa.service.PdfFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;  // Para BigDecimal
import java.util.Date;    // Para Date

@RestController
@RequestMapping(path = "api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private XmlFacturaService xmlFacturaService;

    @Autowired
    private PdfFacturaService pdfFacturaService;

    // Principio de Experto en Información (1):
    // El servicio es experto en conocer cómo obtener todas las facturas.
    @GetMapping
    public List<Factura> getAll() {
        return facturaService.getAllFacturas();
    }

    // Principio de Experto en Información (2):
    // El servicio sabe cómo guardar o actualizar una factura.
    @PostMapping
    public void saveOrUpdate(@RequestBody Factura factura) {
        facturaService.save(factura);
    }

    // Principio de Experto en Información (3):
    // El servicio sabe cómo actualizar una factura específica.
    @PutMapping("/{id}")
    public Factura updateFactura(@PathVariable Long id, @RequestBody Factura factura) {
        return facturaService.updateFactura(id, factura);
    }

    // Polimorfismo (1):
    // El controlador delega la eliminación al servicio, que puede usar cualquier implementación del repositorio.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long facturaId) {
        facturaService.delete(facturaId);
    }

    // Fabricación Pura (1):
    // Este método coordina la operación para obtener una factura por ID sin modificar ningún estado.
    @GetMapping("/{id}")
    public Optional<Factura> getById(@PathVariable("id") Long facturaId) {
        return facturaService.getFactura(facturaId);
    }

    // Factory Method (1):
    // Usamos el método estático crearFacturaPredeterminada para crear facturas con valores iniciales predeterminados.
    @PostMapping("/predeterminada")
    public Factura crearFacturaPredeterminada() {
        Factura factura = Factura.crearFacturaPredeterminada(); // Invocación del Factory Method.
        facturaService.save(factura); // Guardar la factura generada.
        return factura;
    }

    // Builder (1):
    // Se construye una factura personalizada directamente desde el controlador usando el patrón Builder.
    @PostMapping("/builder")
    public Factura crearFacturaConBuilder() {
        Factura factura = new Factura.Builder()
                .conCodigo("FAC-" + System.currentTimeMillis()) // Establece el código.
                .conFecha(new Date()) // Establece la fecha actual.
                .conSubtotal(new BigDecimal("100.00")) // Establece el subtotal.
                .conTotalImpuestos(new BigDecimal("19.00")) // Establece los impuestos.
                .conTotal(new BigDecimal("119.00")) // Establece el total.
                .conEstado("CONFIRMADA") // Establece el estado.
                .build(); // Finaliza la construcción.

        facturaService.save(factura); // Guarda la factura creada.
        return factura;
    }

    // Nuevo endpoint para generar XML de la factura
    @GetMapping("/xml/{id}")
    public String obtenerXml(@PathVariable("id") Long id) throws JAXBException {
        Optional<Factura> factura = facturaService.getFactura(id);
        return "Factura no encontrada";
    }

    // Nuevo endpoint para generar PDF de la factura
    @GetMapping("/pdf/{id}")
    public byte[] obtenerPdf(@PathVariable("id") Long id) throws com.example.FacturaYa.controller.DocumentException, java.io.IOException {
        Optional<Factura> factura = facturaService.getFactura(id);
        if (factura.isPresent()) {
            ByteArrayOutputStream pdf = pdfFacturaService.generatePdf(factura.get()); // Generar el PDF
            return pdf.toByteArray(); // Retornar el PDF como byte array
        } else {
            return new byte[0]; // Si no se encuentra la factura, retorna un arreglo vacío
        }
    }
}
