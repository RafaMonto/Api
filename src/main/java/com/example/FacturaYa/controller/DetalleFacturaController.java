package com.example.FacturaYa.controller;

import com.example.FacturaYa.entity.DetalleFactura;
import com.example.FacturaYa.service.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/detalle-facturas")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    // (1) (SRP) Este controlador se encarga exclusivamente de exponer los servicios de DetalleFactura.
    // (2) (OCP) Se puede extender fácilmente si es necesario agregar más operaciones.

    // Método para obtener todos los detalles de factura
    @GetMapping
    public List<DetalleFactura> getAll() {
        return detalleFacturaService.getAllDetallesFactura();
    } // (3) DRY

    // Método para obtener un detalle de factura por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getDetalle(@PathVariable Long id) {
        Optional<DetalleFactura> detalleFacturaOpt = detalleFacturaService.getDetalleFactura(id);
        // (4) Retornamos 200 OK con el detalle
        // (5) Retornamos 404 si no se encuentra el detalle
        return detalleFacturaOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para crear un nuevo detalle de factura
    @PostMapping
    public ResponseEntity<DetalleFactura> createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        detalleFacturaService.save(detalleFactura);
        return ResponseEntity.status(201).body(detalleFactura); // (6) Retornamos 201 Created
    }

    // Método para actualizar un detalle de factura
    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(@PathVariable Long id, @RequestBody DetalleFactura detalleFactura) {
        DetalleFactura detalleFacturaActualizado = detalleFacturaService.updateDetalleFactura(id, detalleFactura);
        if (detalleFacturaActualizado != null) {
            return ResponseEntity.ok(detalleFacturaActualizado); // (7) Retornamos 200 OK con el detalle actualizado
        } else {
            return ResponseEntity.notFound().build(); // (8) Retornamos 404 si no se encuentra el detalle
        }
    }

    // Método para eliminar un detalle de factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable Long id) {
        detalleFacturaService.delete(id);
        return ResponseEntity.noContent().build(); // (9) Retornamos 204 No Content después de eliminar
    }
}
