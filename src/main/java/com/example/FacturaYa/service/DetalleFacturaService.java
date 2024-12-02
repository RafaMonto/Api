package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.DetalleFactura;
import com.example.FacturaYa.repository.DetalleFacturaRepository;

@Service
public class DetalleFacturaService {

    @Autowired
    DetalleFacturaRepository detalleFacturaRepository;

    public List<DetalleFactura> getAllDetallesFactura() {
        return detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFactura> getDetalleFactura(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public void save(DetalleFactura detalleFactura) {
        detalleFacturaRepository.save(detalleFactura);
    }

    public DetalleFactura updateDetalleFactura(Long id, DetalleFactura detalleFactura) {
        // Buscar detalle de factura por ID
        Optional<DetalleFactura> detalleExistenteOpt = detalleFacturaRepository.findById(id);

        if (detalleExistenteOpt.isPresent()) {
            DetalleFactura detalleExistente = detalleExistenteOpt.get();

            // Modificar los campos
            detalleExistente.setCantidad(detalleFactura.getCantidad());
            detalleExistente.setValorTotal(detalleFactura.getValorTotal());
            detalleExistente.setDescuento(detalleFactura.getDescuento());
            detalleExistente.setProducto(detalleFactura.getProducto());
            detalleExistente.setFactura(detalleFactura.getFactura());

            // Guardar el detalle de factura actualizado
            return detalleFacturaRepository.save(detalleExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        detalleFacturaRepository.deleteById(id);
    }
}
