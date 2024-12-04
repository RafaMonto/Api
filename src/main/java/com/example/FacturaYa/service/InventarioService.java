package com.example.FacturaYa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Inventario;
import com.example.FacturaYa.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

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
        Optional<Inventario> inventarioExistenteOpt = inventarioRepository.findById(id);

        if (inventarioExistenteOpt.isPresent()) {
            Inventario inventarioExistente = inventarioExistenteOpt.get();
            inventarioExistente.setFecha(inventario.getFecha());
            inventarioExistente.setTipoMovimiento(inventario.getTipoMovimiento());
            inventarioExistente.setEntrada(inventario.getEntrada());
            inventarioExistente.setSalida(inventario.getSalida());
            inventarioExistente.setObservaciones(inventario.getObservaciones());
            inventarioExistente.setProducto(inventario.getProducto());
            return inventarioRepository.save(inventarioExistente);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        inventarioRepository.deleteById(id);
    }

    // *Facade (2)*:
    // Genera un resumen de inventario combinando diferentes operaciones.
    public String generarResumenInventario() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        BigDecimal totalEntradas = inventarios.stream()
                .map(Inventario::getEntrada)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSalidas = inventarios.stream()
                .map(Inventario::getSalida)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return "Resumen de Inventario:\nEntradas Totales: " + totalEntradas + "\nSalidas Totales: " + totalSalidas;
    }

    // *Command (2)*:
    // Registra un movimiento de entrada o salida basado en el tipo de movimiento.
    public String registrarMovimientoInventario(Inventario inventario) {
        if (inventario.getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
            inventario.setSalida(BigDecimal.ZERO);
        } else if (inventario.getTipoMovimiento().equalsIgnoreCase("SALIDA")) {
            inventario.setEntrada(BigDecimal.ZERO);
        } else {
            return "Tipo de movimiento no reconocido.";
        }
        inventarioRepository.save(inventario);
        return "Movimiento registrado correctamente.";
    }

    // *Bridge (2)*:
    // Calcula el saldo total de inventario.
    public BigDecimal calcularSaldoTotal() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        return inventarios.stream()
                .map(Inventario::calcularSaldo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}