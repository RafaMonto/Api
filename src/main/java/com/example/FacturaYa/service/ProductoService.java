package com.example.FacturaYa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Producto;
import com.example.FacturaYa.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(Long id) {
        return productoRepository.findById(id);
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        Optional<Producto> productoExistenteOpt = productoRepository.findById(id);

        if (productoExistenteOpt.isPresent()) {
            Producto productoExistente = productoExistenteOpt.get();
            productoExistente.setCodigo(producto.getCodigo());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecioVenta(producto.getPrecioVenta());
            productoExistente.setMedida(producto.getMedida());
            productoExistente.setImpuesto(producto.getImpuesto());
            productoExistente.setCategoria(producto.getCategoria());
            return productoRepository.save(productoExistente);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    // *Mediator (2)*:
    // Genera un resumen coordinando operaciones relacionadas con productos.
    public String generarResumenProducto() {
        List<Producto> productos = productoRepository.findAll();
        StringBuilder resumen = new StringBuilder("Resumen de Productos:\n");
        for (Producto producto : productos) {
            resumen.append("- Código: ").append(producto.getCodigo())
                    .append(", Precio: ").append(producto.getPrecioVenta())
                    .append(", Precio con Impuestos: ").append(producto.calcularPrecioConImpuestos())
                    .append("\n");
        }
        return resumen.toString();
    }

    // *Bridge (2)*:
    // Calcula el precio total de todos los productos con impuestos.
    public BigDecimal calcularTotalConImpuestos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(Producto::calcularPrecioConImpuestos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}