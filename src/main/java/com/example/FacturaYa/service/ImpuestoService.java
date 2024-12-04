package com.example.FacturaYa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Impuesto;
import com.example.FacturaYa.repository.ImpuestoRepository;

@Service
public class ImpuestoService {

    @Autowired
    private ImpuestoRepository impuestoRepository;

    // *Principio de Experto en Información (3)*:
    // Este método es experto en obtener todos los impuestos de la base de datos.
    public List<Impuesto> getAllImpuestos() {
        return impuestoRepository.findAll();
    }

    public Optional<Impuesto> getImpuesto(Long id) {
        return impuestoRepository.findById(id);
    }

    public void save(Impuesto impuesto) {
        impuestoRepository.save(impuesto);
    }

    public Impuesto updateImpuesto(Long id, Impuesto impuesto) {
        Optional<Impuesto> impuestoExistenteOpt = impuestoRepository.findById(id);

        if (impuestoExistenteOpt.isPresent()) {
            Impuesto impuestoExistente = impuestoExistenteOpt.get();
            impuestoExistente.setNombre(impuesto.getNombre());
            impuestoExistente.setPorcentaje(impuesto.getPorcentaje());
            return impuestoRepository.save(impuestoExistente);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        impuestoRepository.deleteById(id);
    }

    // *Fabricación Pura (3)*:
    // Método que calcula el monto total con impuesto basado en el porcentaje.
    public BigDecimal calcularTotalConImpuesto(BigDecimal base, BigDecimal porcentaje) {
        return base.add(base.multiply(porcentaje.divide(BigDecimal.valueOf(100))));
    }
}