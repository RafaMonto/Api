package com.example.FacturaYa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacturaYa.entity.DetalleFactura;

@Repository
// DRY (5): Usamos JpaRepository para evitar reescribir lógica de persistencia común.
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

}