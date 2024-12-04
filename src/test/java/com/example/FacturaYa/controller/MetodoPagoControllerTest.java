package com.example.FacturaYa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.MetodoPago;
import com.example.FacturaYa.service.MetodoPagoService;

class MetodoPagoControllerTest {

    @Mock
    private MetodoPagoService metodoPagoService;

    @InjectMocks
    private MetodoPagoController metodoPagoController;

    public MetodoPagoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMetodosPago() {
        List<MetodoPago> metodosPagoMock = List.of(new MetodoPago(), new MetodoPago());
        when(metodoPagoService.getAllMetodosPago()).thenReturn(metodosPagoMock);

        List<MetodoPago> metodosPago = metodoPagoController.getAll();
        assertNotNull(metodosPago);
        assertEquals(2, metodosPago.size());
        verify(metodoPagoService, times(1)).getAllMetodosPago();
    }

    @Test
    void testSaveMetodoPago() {
        MetodoPago metodoPagoMock = new MetodoPago();
        metodoPagoController.saveOrUpdate(metodoPagoMock);

        verify(metodoPagoService, times(1)).save(metodoPagoMock);
    }

    @Test
    void testDeleteMetodoPago() {
        Long metodoPagoId = 1L;
        metodoPagoController.delete(metodoPagoId);

        verify(metodoPagoService, times(1)).delete(metodoPagoId);
    }

    @Test
    void testGetMetodoPagoById() {
        Long metodoPagoId = 1L;
        MetodoPago metodoPagoMock = new MetodoPago();
        when(metodoPagoService.getMetodoPago(metodoPagoId)).thenReturn(Optional.of(metodoPagoMock));

        Optional<MetodoPago> metodoPago = metodoPagoController.getById(metodoPagoId);
        assertTrue(metodoPago.isPresent());
        verify(metodoPagoService, times(1)).getMetodoPago(metodoPagoId);
    }
}