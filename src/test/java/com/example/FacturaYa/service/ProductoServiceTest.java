package com.example.FacturaYa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.FacturaYa.entity.Producto;
import com.example.FacturaYa.repository.ProductoRepository;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    public ProductoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProductos() {
        List<Producto> productosMock = List.of(new Producto(), new Producto());
        when(productoRepository.findAll()).thenReturn(productosMock);

        List<Producto> productos = productoService.getAllProductos();
        assertNotNull(productos);
        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testSaveProducto() {
        Producto productoMock = new Producto();
        productoService.save(productoMock);

        verify(productoRepository, times(1)).save(productoMock);
    }

    @Test
    void testUpdateProducto() {
        Long productoId = 1L;
        Producto productoMock = new Producto();
        productoMock.setCodigo("P001");

        Producto productoExistente = new Producto();
        productoExistente.setCodigo("P002");

        when(productoRepository.findById(productoId)).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoExistente);

        Producto updatedProducto = productoService.updateProducto(productoId, productoMock);

        assertNotNull(updatedProducto);
        assertEquals("P001", updatedProducto.getCodigo());
        verify(productoRepository, times(1)).save(productoExistente);
    }

    @Test
    void testDeleteProducto() {
        Long productoId = 1L;
        productoService.delete(productoId);

        verify(productoRepository, times(1)).deleteById(productoId);
    }
}
