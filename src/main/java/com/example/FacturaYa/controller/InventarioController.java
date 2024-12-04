package com.example.FacturaYa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.FacturaYa.entity.Inventario;
import com.example.FacturaYa.service.InventarioService;

@RestController
@RequestMapping(path = "api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> getAll() {
        return inventarioService.getAllInventarios();
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody Inventario inventario) {
        inventarioService.save(inventario);
    }

    @PutMapping("/{id}")
    public Inventario updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        return inventarioService.updateInventario(id, inventario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long inventarioId) {
        inventarioService.delete(inventarioId);
    }

    @GetMapping("/{id}")
    public Optional<Inventario> getById(@PathVariable("id") Long inventarioId) {
        return inventarioService.getInventario(inventarioId);
    }

    // *Facade (1)*:
    // Controlador coordina las operaciones para generar un resumen de inventario.
    @GetMapping("/resumen")
    public String generarResumen() {
        return inventarioService.generarResumenInventario();
    }

    // *Command (1)*:
    // Permite registrar movimientos de entrada/salida como comandos.
    @PostMapping("/movimiento")
    public String registrarMovimiento(@RequestBody Inventario inventario) {
        return inventarioService.registrarMovimientoInventario(inventario);
    }
}