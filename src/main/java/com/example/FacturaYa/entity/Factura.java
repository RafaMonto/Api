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
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String codigo;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double totalImpuestos;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double total;

    @Column(nullable = false)
    private String estado; // pendiente, pagado, cancelado

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pago", referencedColumnName = "id")
    private MetodoPago metodoPago;
}
