package com.example.FacturaYa.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_factura")
// Alta cohesión (1): Esta clase está exclusivamente diseñada para representar datos de DetalleFactura.
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    private BigDecimal descuento;

    @ManyToOne
    @JoinColumn(name = "id_factura", referencedColumnName = "id") // Relación con Factura.
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id") // Relación con Producto.
    private Producto producto;

    // YAGNI (2): Solo se mantienen atributos necesarios para DetalleFactura, evitando atributos innecesarios.
}