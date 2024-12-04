package com.example.FacturaYa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.Inventario;
import com.example.FacturaYa.service.InventarioService;

class InventarioControllerTest {

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    public InventarioControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInventarios() {
        List<Inventario> inventariosMock = List.of(new Inventario(), new Inventario());
        when(inventarioService.getAllInventarios()).thenReturn(inventariosMock);

        List<Inventario> inventarios = inventarioController.getAll();
        assertNotNull(inventarios);
        assertEquals(2, inventarios.size());
        verify(inventarioService, times(1)).getAllInventarios();
    }

    @Test
    void testSaveInventario() {
        Inventario inventarioMock = new Inventario();
        inventarioController.saveOrUpdate(inventarioMock);

        verify(inventarioService, times(1)).save(inventarioMock);
    }

    @Test
    void testDeleteInventario() {
        Long inventarioId = 1L;
        inventarioController.delete(inventarioId);

        verify(inventarioService, times(1)).delete(inventarioId);
    }

    @Test
    void testGetInventarioById() {
        Long inventarioId = 1L;
        Inventario inventarioMock = new Inventario();
        when(inventarioService.getInventario(inventarioId)).thenReturn(Optional.of(inventarioMock));

        Optional<Inventario> inventario = inventarioController.getById(inventarioId);
        assertTrue(inventario.isPresent());
        verify(inventarioService, times(1)).getInventario(inventarioId);
    }
}