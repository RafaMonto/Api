package com.example.FacturaYa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.repository.FacturaRepository;

class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaService facturaService;

    public FacturaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacturas() {
        List<Factura> facturasMock = List.of(new Factura(), new Factura());
        when(facturaRepository.findAll()).thenReturn(facturasMock);

        List<Factura> facturas = facturaService.getAllFacturas();
        assertNotNull(facturas);
        assertEquals(2, facturas.size());
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    void testSaveFactura() {
        Factura facturaMock = new Factura();
        facturaService.save(facturaMock);

        verify(facturaRepository, times(1)).save(facturaMock);
    }

    @Test
    void testUpdateFactura() {
        Long facturaId = 1L;
        Factura facturaMock = new Factura();
        facturaMock.setCodigo("F001");

        Factura facturaExistente = new Factura();
        facturaExistente.setCodigo("F002");

        when(facturaRepository.findById(facturaId)).thenReturn(Optional.of(facturaExistente));
        when(facturaRepository.save(any(Factura.class))).thenReturn(facturaExistente);

        Factura updatedFactura = facturaService.updateFactura(facturaId, facturaMock);

        assertNotNull(updatedFactura);
        assertEquals("F001", updatedFactura.getCodigo());
        verify(facturaRepository, times(1)).save(facturaExistente);
    }

    @Test
    void testDeleteFactura() {
        Long facturaId = 1L;
        facturaService.delete(facturaId);

        verify(facturaRepository, times(1)).deleteById(facturaId);
    }
}