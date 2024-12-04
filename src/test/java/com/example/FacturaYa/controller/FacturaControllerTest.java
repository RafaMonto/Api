package com.example.FacturaYa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.service.FacturaService;

class FacturaControllerTest {

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    public FacturaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacturas() {
        List<Factura> facturasMock = List.of(new Factura(), new Factura());
        when(facturaService.getAllFacturas()).thenReturn(facturasMock);

        List<Factura> facturas = facturaController.getAll();
        assertNotNull(facturas);
        assertEquals(2, facturas.size());
        verify(facturaService, times(1)).getAllFacturas();
    }

    @Test
    void testSaveFactura() {
        Factura facturaMock = new Factura();
        facturaController.saveOrUpdate(facturaMock);

        verify(facturaService, times(1)).save(facturaMock);
    }

    @Test
    void testDeleteFactura() {
        Long facturaId = 1L;
        facturaController.delete(facturaId);

        verify(facturaService, times(1)).delete(facturaId);
    }

    @Test
    void testGetFacturaById() {
        Long facturaId = 1L;
        Factura facturaMock = new Factura();
        when(facturaService.getFactura(facturaId)).thenReturn(Optional.of(facturaMock));

        Optional<Factura> factura = facturaController.getById(facturaId);
        assertTrue(factura.isPresent());
        verify(facturaService, times(1)).getFactura(facturaId);
    }
}