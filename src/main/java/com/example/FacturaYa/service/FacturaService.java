package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getFactura(Long id) {
        return facturaRepository.findById(id);
    }

    public void save(Factura factura) {
        facturaRepository.save(factura);
    }

    public Factura updateFactura(Long id, Factura factura) {
        // Buscar factura por ID
        Optional<Factura> facturaExistenteOpt = facturaRepository.findById(id);

        if (facturaExistenteOpt.isPresent()) {
            Factura facturaExistente = facturaExistenteOpt.get();

            // Modificar los campos
            facturaExistente.setCodigo(factura.getCodigo());
            facturaExistente.setFecha(factura.getFecha());
            facturaExistente.setSubtotal(factura.getSubtotal());
            facturaExistente.setTotalImpuestos(factura.getTotalImpuestos());
            facturaExistente.setTotal(factura.getTotal());
            facturaExistente.setEstado(factura.getEstado());
            facturaExistente.setCliente(factura.getCliente());
            facturaExistente.setMetodoPago(factura.getMetodoPago());
            // Guardar factura actualizada
            return facturaRepository.save(facturaExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        facturaRepository.deleteById(id);
    }
}
