package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.DetalleFactura;
import com.example.FacturaYa.repository.DetalleFacturaRepository;

@Service
// Alta cohesión (2): Esta clase se centra exclusivamente en la lógica de negocio de DetalleFactura.
// Bajo acoplamiento (1): Depende de DetalleFacturaRepository, que es una interfaz.
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public List<DetalleFactura> getAllDetallesFactura() {
        return detalleFacturaRepository.findAll(); // DRY (1): Evitamos lógica redundante.
    }

    public Optional<DetalleFactura> getDetalleFactura(Long id) {
        return detalleFacturaRepository.findById(id); // DRY (2).
    }

    public void save(DetalleFactura detalleFactura) {
        detalleFacturaRepository.save(detalleFactura); // YAGNI (3): Evitamos validaciones innecesarias aquí.
    }

    public DetalleFactura updateDetalleFactura(Long id, DetalleFactura detalleFactura) {
        Optional<DetalleFactura> detalleExistenteOpt = detalleFacturaRepository.findById(id);
        if (detalleExistenteOpt.isPresent()) {
            DetalleFactura detalleExistente = detalleExistenteOpt.get();

            // DRY (3): Reutilizamos los setters de la entidad para actualizar campos.
            detalleExistente.setCantidad(detalleFactura.getCantidad());
            detalleExistente.setValorTotal(detalleFactura.getValorTotal());
            detalleExistente.setDescuento(detalleFactura.getDescuento());
            detalleExistente.setProducto(detalleFactura.getProducto());
            detalleExistente.setFactura(detalleFactura.getFactura());

            return detalleFacturaRepository.save(detalleExistente);
        } else {
            return null; // YAGNI (4): Mantenemos lógica simple para manejar errores.
        }
    }

    public void delete(Long id) {
        detalleFacturaRepository.deleteById(id); // DRY (4).
    }
}