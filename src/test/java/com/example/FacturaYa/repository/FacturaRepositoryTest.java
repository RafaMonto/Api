package com.example.FacturaYa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.FacturaYa.entity.Factura;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
class FacturaRepositoryTest {

    @Autowired
    private FacturaRepository facturaRepository;

    @Test
    void testFindAll() {
        List<Factura> facturas = facturaRepository.findAll();
        assertNotNull(facturas);
    }

    @Test
    void testSaveFactura() {
        Factura factura = new Factura();
        factura.setCodigo("F001");
        factura.setTotal(new BigDecimal("1000.00"));
        Factura savedFactura = facturaRepository.save(factura);

        assertNotNull(savedFactura.getId());
        assertEquals("F001", savedFactura.getCodigo());
    }

    @Test
    void testFindById() {
        Factura factura = new Factura();
        factura.setCodigo("F002");
        Factura savedFactura = facturaRepository.save(factura);

        Factura foundFactura = facturaRepository.findById(savedFactura.getId()).orElse(null);
        assertNotNull(foundFactura);
        assertEquals("F002", foundFactura.getCodigo());
    }

    @Test
    void testFindByFechaBetween() {
        Factura factura1 = new Factura();
        factura1.setCodigo("F003");
        factura1.setFecha(new Date(System.currentTimeMillis()));

        Factura factura2 = new Factura();
        factura2.setCodigo("F004");
        factura2.setFecha(new Date(System.currentTimeMillis()));

        facturaRepository.save(factura1);
        facturaRepository.save(factura2);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date inicio = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DATE, 2);
        Date fin = new Date(cal.getTimeInMillis());

        List<Factura> facturas = FacturaRepository.findAllByFechaBetween(inicio, fin);
        assertNotNull(facturas);
        assertTrue(facturas.size() > 0);
    }

    @Test
    void testCountFacturas() {
        Factura factura1 = new Factura();
        factura1.setCodigo("F005");
        facturaRepository.save(factura1);

        long count = facturaRepository.count();
        assertTrue(count > 0);
    }
}