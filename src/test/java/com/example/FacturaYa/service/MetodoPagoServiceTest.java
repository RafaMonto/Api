package com.example.FacturaYa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.MetodoPago;
import com.example.FacturaYa.repository.MetodoPagoRepository;

class MetodoPagoServiceTest {

    @Mock
    private MetodoPagoRepository metodoPagoRepository;

    @InjectMocks
    private MetodoPagoService metodoPagoService;

    public MetodoPagoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMetodosPago() {
        List<MetodoPago> metodosPagoMock = List.of(new MetodoPago(), new MetodoPago());
        when(metodoPagoRepository.findAll()).thenReturn(metodosPagoMock);

        List<MetodoPago> metodosPago = metodoPagoService.getAllMetodosPago();
        assertNotNull(metodosPago);
        assertEquals(2, metodosPago.size());
        verify(metodoPagoRepository, times(1)).findAll();
    }

    @Test
    void testSaveMetodoPago() {
        MetodoPago metodoPagoMock = new MetodoPago();
        metodoPagoMock.setDescripcion("Tarjeta de crédito");
        metodoPagoMock.setIdentificador("1234-5678-9012-3456");

        metodoPagoService.save(metodoPagoMock);

        verify(metodoPagoRepository, times(1)).save(metodoPagoMock);
    }

    @Test
    void testUpdateMetodoPago() {
        Long metodoPagoId = 1L;
        MetodoPago metodoPagoMock = new MetodoPago();
        metodoPagoMock.setDescripcion("Tarjeta de crédito");
        metodoPagoMock.setIdentificador("1234-5678-9012-3456");

        MetodoPago metodoPagoExistente = new MetodoPago();
        metodoPagoExistente.setDescripcion("Tarjeta de débito");
        metodoPagoExistente.setIdentificador("6543-2109-8765-4321");

        when(metodoPagoRepository.findById(metodoPagoId)).thenReturn(Optional.of(metodoPagoExistente));
        when(metodoPagoRepository.save(any(MetodoPago.class))).thenReturn(metodoPagoExistente);

        MetodoPago updatedMetodoPago = metodoPagoService.updateMetodoPago(metodoPagoId, metodoPagoMock);

        assertNotNull(updatedMetodoPago);
        assertEquals("Tarjeta de crédito", updatedMetodoPago.getDescripcion());
        assertEquals("1234-5678-9012-3456", updatedMetodoPago.getIdentificador());
        verify(metodoPagoRepository, times(1)).save(metodoPagoExistente);
    }

    @Test
    void testDeleteMetodoPago() {
        Long metodoPagoId = 1L;
        metodoPagoService.delete(metodoPagoId);

        verify(metodoPagoRepository, times(1)).deleteById(metodoPagoId);
    }
}