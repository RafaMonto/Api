package com.example.FacturaYa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.FacturaYa.entity.Impuesto;
import com.example.FacturaYa.service.ImpuestoService;

@RestController
@RequestMapping(path = "api/impuestos")
public class ImpuestoController {

    @Autowired
    private ImpuestoService impuestoService;

    // *Principio de Experto en Información (1)*:
    // El servicio es experto en conocer cómo obtener todos los impuestos.
    @GetMapping
    public List<Impuesto> getAll() {
        return impuestoService.getAllImpuestos();
    }

    // *Principio de Experto en Información (2)*:
    // El servicio sabe cómo guardar o actualizar un impuesto.
    @PostMapping
    public void saveUpdate(@RequestBody Impuesto impuesto) {
        impuestoService.save(impuesto);
    }

    // *Polimorfismo (1)*:
    // El controlador delega la actualización al servicio, que puede usar cualquier repositorio.
    @PutMapping("/{id}")
    public Impuesto updateImpuesto(@PathVariable Long id, @RequestBody Impuesto impuesto) {
        return impuestoService.updateImpuesto(id, impuesto);
    }

    // *Fabricación Pura (1)*:
    // Este método coordina la eliminación sin mantener estados adicionales.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long impuestoId) {
        impuestoService.delete(impuestoId);
    }

    // *Polimorfismo (2)*:
    // El controlador delega la búsqueda por ID al servicio.
    @GetMapping("/{id}")
    public Optional<Impuesto> getById(@PathVariable("id") Long impuestoId) {
        return impuestoService.getImpuesto(impuestoId);
    }
}