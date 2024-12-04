package com.example.FacturaYa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacturaYa.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // *Bridge (3)*:
    // Encuentra todos los productos cuyo precio de venta sea mayor a un valor especificado.
    List<Producto> findByPrecioVentaGreaterThan(BigDecimal precioMinimo);

    // *Bridge (4)*:
    // Encuentra productos de una categoría específica.
    List<Producto> findByCategoriaId(Long categoriaId);

    // *Bridge (5)*:
    // Encuentra productos que contengan un texto específico en su descripción.
    List<Producto> findByDescripcionContaining(String texto);
}