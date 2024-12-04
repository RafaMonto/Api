package com.example.FacturaYa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacturaYa.entity.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    // *Adapter (2)*:
    // Busca un método de pago por su identificador único.
    Optional<MetodoPago> findByIdentificador(String identificador);

    // *Bridge (3)*:
    // Cuenta el número total de métodos de pago disponibles.
    long countByDescripcionNotNull();
}