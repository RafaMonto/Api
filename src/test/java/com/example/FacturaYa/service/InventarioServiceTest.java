package com.example.FacturaYa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.Inventario;
import com.example.FacturaYa.repository.InventarioRepository;

class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    public InventarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInventarios() {
        List<Inventario> inventariosMock = List.of(new Inventario(), new Inventario());
        when(inventarioRepository.findAll()).thenReturn(inventariosMock);

        List<Inventario> inventarios = inventarioService.getAllInventarios();
        assertNotNull(inventarios);
        assertEquals(2, inventarios.size());
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void testSaveInventario() {
        Inventario inventarioMock = new Inventario();
        inventarioService.save(inventarioMock);

        verify(inventarioRepository, times(1)).save(inventarioMock);
    }

    @Test
    void testUpdateInventario() {
        Long inventarioId = 1L;
        Inventario inventarioMock = new Inventario();
        inventarioMock.setEntrada(new BigDecimal("10.00"));

        Inventario inventarioExistente = new Inventario();
        inventarioExistente.setEntrada(new BigDecimal("5.00"));

        when(inventarioRepository.findById(inventarioId)).thenReturn(Optional.of(inventarioExistente));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioExistente);

        Inventario updatedInventario = inventarioService.updateInventario(inventarioId, inventarioMock);

        assertNotNull(updatedInventario);
        assertEquals(new BigDecimal("10.00"), updatedInventario.getEntrada());
        verify(inventarioRepository, times(1)).save(inventarioExistente);
    }

    @Test
    void testDeleteInventario() {
        Long inventarioId = 1L;
        inventarioService.delete(inventarioId);

        verify(inventarioRepository, times(1)).deleteById(inventarioId);
    }
}