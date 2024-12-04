package com.example.FacturaYa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.FacturaYa.entity.Producto;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void testFindAll() {
        List<Producto> productos = productoRepository.findAll();
        assertNotNull(productos);
    }

    @Test
    void testSaveProducto() {
        Producto producto = new Producto();
        producto.setCodigo("P001");
        producto.setDescripcion("Producto de prueba");
        producto.setPrecioVenta(new BigDecimal("100.00"));
        Producto savedProducto = productoRepository.save(producto);

        assertNotNull(savedProducto.getId());
        assertEquals("P001", savedProducto.getCodigo());
    }

    @Test
    void testFindById() {
        Producto producto = new Producto();
        producto.setCodigo("P002");
        producto.setDescripcion("Otro producto");
        producto.setPrecioVenta(new BigDecimal("200.00"));
        Producto savedProducto = productoRepository.save(producto);

        Producto foundProducto = productoRepository.findById(savedProducto.getId()).orElse(null);
        assertNotNull(foundProducto);
        assertEquals("P002", foundProducto.getCodigo());
    }
}