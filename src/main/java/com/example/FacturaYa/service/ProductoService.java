package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Producto;
import com.example.FacturaYa.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(Long id) {
        return productoRepository.findById(id);
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        // Buscar el producto por su ID
        Optional<Producto> productoExistenteOpt = productoRepository.findById(id);
        
        if (productoExistenteOpt.isPresent()) {
            // Si el producto existe, obtenemos la entidad
            Producto productoExistente = productoExistenteOpt.get();
            
            // Modificamos los campos que deseamos actualizar
            productoExistente.setCodigo(producto.getCodigo());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecioVenta(producto.getPrecioVenta());
            productoExistente.setMedida(producto.getMedida());
            productoExistente.setImpuesto(producto.getImpuesto());  // Si estás cambiando el impuesto
            productoExistente.setCategoria(producto.getCategoria()); // Si estás cambiando la categoría

            // Guardar el producto actualizado
            return productoRepository.save(productoExistente);
        } else {
            // Si no se encuentra el producto, puedes lanzar una excepción o retornar null
            return null;
        }
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}
