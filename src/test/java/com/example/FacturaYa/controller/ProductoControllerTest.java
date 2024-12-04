package com.example.FacturaYa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.Producto;
import com.example.FacturaYa.service.ProductoService;

class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    public ProductoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProductos() {
        List<Producto> productosMock = List.of(new Producto(), new Producto());
        when(productoService.getAllProductos()).thenReturn(productosMock);

        List<Producto> productos = productoController.getAll();
        assertNotNull(productos);
        assertEquals(2, productos.size());
        verify(productoService, times(1)).getAllProductos();
    }

    @Test
    void testSaveProducto() {
        Producto productoMock = new Producto();
        productoController.save(productoMock);

        verify(productoService, times(1)).save(productoMock);
    }

    @Test
    void testDeleteProducto() {
        Long productoId = 1L;
        productoController.delete(productoId);

        verify(productoService, times(1)).delete(productoId);
    }

    @Test
    void testGetProductoById() {
        Long productoId = 1L;
        Producto productoMock = new Producto();
        when(productoService.getProducto(productoId)).thenReturn(Optional.of(productoMock));

        Optional<Producto> producto = productoController.getById(productoId);
        assertTrue(producto.isPresent());
        verify(productoService, times(1)).getProducto(productoId);
    }
}