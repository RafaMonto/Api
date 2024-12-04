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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // YAGNI (2): Solo se mantienen atributos necesarios para DetalleFactura, evitando atributos innecesarios.
}