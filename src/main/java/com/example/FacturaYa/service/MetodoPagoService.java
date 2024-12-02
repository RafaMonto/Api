package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.MetodoPago;
import com.example.FacturaYa.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

    @Autowired
    MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> getMetodoPago(Long id) {
        return metodoPagoRepository.findById(id);
    }

    public void save(MetodoPago metodoPago) {
        metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago updateMetodoPago(Long id, MetodoPago metodoPago) {
        // Buscar metodo de pago por ID
        Optional<MetodoPago> metodoPagoExistenteOpt = metodoPagoRepository.findById(id);

        if (metodoPagoExistenteOpt.isPresent()) {
            MetodoPago metodoPagoExistente = metodoPagoExistenteOpt.get();

            // Modificar los campos
            metodoPagoExistente.setDescripcion(metodoPago.getDescripcion());

            // Guardar metodo de pago actualizado
            return metodoPagoRepository.save(metodoPagoExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        metodoPagoRepository.deleteById(id);
    }
}
