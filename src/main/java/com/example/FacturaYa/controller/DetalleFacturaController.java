package com.example.FacturaYa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacturaYa.entity.DetalleFactura;
import com.example.FacturaYa.service.DetalleFacturaService;

@RestController
@RequestMapping(path = "api/detalles-factura")
public class DetalleFacturaController {

    @Autowired
    DetalleFacturaService detalleFacturaService;

    @GetMapping
    public List<DetalleFactura> getAll() {
        return detalleFacturaService.getAllDetallesFactura();
    }

    @PostMapping
    public void saveUpdate(@RequestBody DetalleFactura detalleFactura) {
        detalleFacturaService.save(detalleFactura);
    }

    @PutMapping("/{id}")
    public DetalleFactura updateDetalleFactura(@PathVariable Long id, @RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.updateDetalleFactura(id, detalleFactura);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long detalleFacturaId) {
        detalleFacturaService.delete(detalleFacturaId);
    }

    @GetMapping("/{id}")
    public Optional<DetalleFactura> getById(@PathVariable("id") Long detalleFacturaId) {
        return detalleFacturaService.getDetalleFactura(detalleFacturaId);
    }
}

