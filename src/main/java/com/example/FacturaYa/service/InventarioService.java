package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Inventario;
import com.example.FacturaYa.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    InventarioRepository inventarioRepository;

    public List<Inventario> getAllInventarios() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> getInventario(Long id) {
        return inventarioRepository.findById(id);
    }

    public void save(Inventario inventario) {
        inventarioRepository.save(inventario);
    }

    public Inventario updateInventario(Long id, Inventario inventario) {
        // Buscar inventario por ID
        Optional<Inventario> inventarioExistenteOpt = inventarioRepository.findById(id);

        if (inventarioExistenteOpt.isPresent()) {
            Inventario inventarioExistente = inventarioExistenteOpt.get();

            // Modificar los campos
            inventarioExistente.setFecha(inventario.getFecha());
            inventarioExistente.setTipoMovimiento(inventario.getTipoMovimiento());
            inventarioExistente.setEntrada(inventario.getEntrada());
            inventarioExistente.setSalida(inventario.getSalida());
            inventarioExistente.setObservaciones(inventario.getObservaciones());
            inventarioExistente.setProducto(inventario.getProducto());

            // Guardar inventario actualizado
            return inventarioRepository.save(inventarioExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        inventarioRepository.deleteById(id);
    }
}
