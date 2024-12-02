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

import com.example.FacturaYa.entity.Producto;
import com.example.FacturaYa.service.ProductoService;

@RestController
@RequestMapping(path = "api/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAllProductos();
    }

    @PostMapping
    public void save(@RequestBody Producto producto) {
        productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long productoId) {
        productoService.delete(productoId);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.updateProducto(id, producto);
    }

    @GetMapping("/{id}")
    public Optional<Producto> getById(@PathVariable("id") Long productoId) {
        return productoService.getProducto(productoId);
    }
}
