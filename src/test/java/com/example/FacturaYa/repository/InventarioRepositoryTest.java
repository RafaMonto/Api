package com.example.FacturaYa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.FacturaYa.entity.Inventario;

@DataJpaTest
class InventarioRepositoryTest {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Test
    void testFindAll() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        assertNotNull(inventarios);
    }

    @Test
    void testSaveInventario() {
        Inventario inventario = new Inventario();
        inventario.setTipoMovimiento("ENTRADA");
        inventario.setEntrada(new BigDecimal("20.00"));
        Inventario savedInventario = inventarioRepository.save(inventario);

        assertNotNull(savedInventario.getId());
        assertEquals("ENTRADA", savedInventario.getTipoMovimiento());
    }

    @Test
    void testFindById() {
        Inventario inventario = new Inventario();
        inventario.setTipoMovimiento("SALIDA");
        inventario.setSalida(new BigDecimal("15.00"));
        Inventario savedInventario = inventarioRepository.save(inventario);

        Inventario foundInventario = inventarioRepository.findById(savedInventario.getId()).orElse(null);
        assertNotNull(foundInventario);
        assertEquals("SALIDA", foundInventario.getTipoMovimiento());
    }
}
