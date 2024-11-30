package com.example.FacturaYa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String tipoMovimiento; // entrada o salida

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10, 2) DEFAULT 0")
    private Double entrada = 0.0;

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10, 2) DEFAULT 0")
    private Double salida = 0.0;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;
}
