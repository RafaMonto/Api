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
    private FacturaRepository facturaRepository;

    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> getFactura(Long id) {
        return facturaRepository.findById(id);
    }

    public void save(Factura factura) {
        // *Polimorfismo (3)*:
        // Uso del método save del repositorio que abstrae la lógica de persistencia.
        facturaRepository.save(factura);
    }

    public Factura updateFactura(Long id, Factura factura) {
        Optional<Factura> facturaExistenteOpt = facturaRepository.findById(id);

        if (facturaExistenteOpt.isPresent()) {
            Factura facturaExistente = facturaExistenteOpt.get();

            facturaExistente.setCodigo(factura.getCodigo());
            facturaExistente.setFecha(factura.getFecha());
            facturaExistente.setSubtotal(factura.getSubtotal());
            facturaExistente.setTotalImpuestos(factura.getTotalImpuestos());
            facturaExistente.setTotal(factura.getTotal());
            facturaExistente.setEstado(factura.getEstado());
            facturaExistente.setCliente(factura.getCliente());
            facturaExistente.setMetodoPago(factura.getMetodoPago());

            // *Polimorfismo (4)*:
            // Delegación de la lógica de guardado al método save del repositorio.
            return facturaRepository.save(facturaExistente);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        facturaRepository.deleteById(id); // *Polimorfismo (5)*.
    }
}