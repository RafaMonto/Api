package com.example.FacturaYa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "impuestos")
public class Impuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private BigDecimal porcentaje;

    // *Fabricación Pura (2)*:
    // Método que calcula el impuesto sin modificar el estado del objeto.
    public BigDecimal calcularImpuesto(BigDecimal base) {
        return base.multiply(porcentaje.divide(BigDecimal.valueOf(100)));
    }
}