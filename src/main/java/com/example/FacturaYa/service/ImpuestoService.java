package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Impuesto;
import com.example.FacturaYa.repository.ImpuestoRepository;

@Service
public class ImpuestoService {
    @Autowired
    ImpuestoRepository impuestoRepository;
    
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
        // Buscar impuesto por ID
        Optional<Impuesto> impuestoExistenteOpt = impuestoRepository.findById(id);

        if (impuestoExistenteOpt.isPresent()) {
            Impuesto impuestoExistente = impuestoExistenteOpt.get();

            // Modificar los campos
            impuestoExistente.setNombre(impuesto.getNombre());
            impuestoExistente.setPorcentaje(impuesto.getPorcentaje());

            // Guardar impuesto actualizado
            return impuestoRepository.save(impuestoExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        impuestoRepository.deleteById(id);
    }
}
