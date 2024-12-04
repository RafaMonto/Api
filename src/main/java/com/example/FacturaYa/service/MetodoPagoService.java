package com.example.FacturaYa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.MetodoPago;
import com.example.FacturaYa.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> getMetodoPago(Long id) {
        return metodoPagoRepository.findById(id);
    }

    public void save(MetodoPago metodoPago) {
        // *Bridge (2)*:
        // Validación adicional utilizando el método de validación del patrón Bridge.
        if (metodoPago.validarMetodoPago()) {
            metodoPagoRepository.save(metodoPago);
        } else {
            throw new IllegalArgumentException("El método de pago no es válido.");
        }
    }

    public MetodoPago updateMetodoPago(Long id, MetodoPago metodoPago) {
        Optional<MetodoPago> metodoPagoExistenteOpt = metodoPagoRepository.findById(id);

        if (metodoPagoExistenteOpt.isPresent()) {
            MetodoPago metodoPagoExistente = metodoPagoExistenteOpt.get();
            metodoPagoExistente.setDescripcion(metodoPago.getDescripcion());
            metodoPagoExistente.setIdentificador(metodoPago.getIdentificador());
            return metodoPagoRepository.save(metodoPagoExistente);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        metodoPagoRepository.deleteById(id);
    }

    // *Iterator (2)*:
    // Devuelve el primer método de pago de la lista.
    public MetodoPago getFirstMetodoPago() {
        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();
        Iterator<MetodoPago> iterator = metodosPago.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    // *Iterator (3)*:
    // Recorre todos los métodos de pago y genera un resumen en texto plano.
    public String exportarMetodosPago() {
        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();
        Iterator<MetodoPago> iterator = metodosPago.iterator();
        StringBuilder resumen = new StringBuilder("Resumen de Métodos de Pago:\n");
        while (iterator.hasNext()) {
            MetodoPago metodo = iterator.next();
            resumen.append("- ").append(metodo.getDescripcion()).append("\n");
        }
        return resumen.toString();
    }
}