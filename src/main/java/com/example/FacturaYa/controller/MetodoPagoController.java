package com.example.FacturaYa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.FacturaYa.entity.MetodoPago;
import com.example.FacturaYa.service.MetodoPagoService;

@RestController
@RequestMapping(path = "api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    // *Principio de Experto en Información*: Delegación al servicio para obtener todos los métodos de pago.
    @GetMapping
    public List<MetodoPago> getAll() {
        return metodoPagoService.getAllMetodosPago();
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody MetodoPago metodoPago) {
        metodoPagoService.save(metodoPago);
    }

    @PutMapping("/{id}")
    public MetodoPago updateMetodoPago(@PathVariable Long id, @RequestBody MetodoPago metodoPago) {
        return metodoPagoService.updateMetodoPago(id, metodoPago);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long metodoPagoId) {
        metodoPagoService.delete(metodoPagoId);
    }

    @GetMapping("/{id}")
    public Optional<MetodoPago> getById(@PathVariable("id") Long metodoPagoId) {
        return metodoPagoService.getMetodoPago(metodoPagoId);
    }

    // *Iterator (1)*:
    // Devuelve el primer método de pago.
    @GetMapping("/iterator")
    public MetodoPago getFirstMetodoPago() {
        return metodoPagoService.getFirstMetodoPago();
    }

    // *Adapter (1)*:
    // Convierte una lista de métodos de pago a un formato legible para exportar como texto plano.
    @GetMapping("/exportar")
    public String exportarMetodosPago() {
        return metodoPagoService.exportarMetodosPago();
    }
}