package com.example.FacturaYa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.FacturaYa.entity.MetodoPago;

@DataJpaTest
class MetodoPagoRepositoryTest {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Test
    void testFindAll() {
        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();
        assertNotNull(metodosPago);
    }

    @Test
    void testSaveMetodoPago() {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setDescripcion("Débito automático");
        MetodoPago savedMetodoPago = metodoPagoRepository.save(metodoPago);

        assertNotNull(savedMetodoPago.getId());
        assertEquals("Débito automático", savedMetodoPago.getDescripcion());
    }

    @Test
    void testFindById() {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setDescripcion("Transferencia bancaria");
        MetodoPago savedMetodoPago = metodoPagoRepository.save(metodoPago);

        MetodoPago foundMetodoPago = metodoPagoRepository.findById(savedMetodoPago.getId()).orElse(null);
        assertNotNull(foundMetodoPago);
        assertEquals("Transferencia bancaria", foundMetodoPago.getDescripcion());
    }
}