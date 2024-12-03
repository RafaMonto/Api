package com.example.FacturaYa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacturaYa.entity.Cliente;

@Repository
// ISP: ClienteRepository está dividido en lectura y escritura a nivel de métodos de JpaRepository.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos predefinidos como save(), findById(), deleteById() ya cumplen ISP.
}