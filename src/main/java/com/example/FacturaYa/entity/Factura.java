package com.example.FacturaYa.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(name = "total_impuestos", nullable = false)
    private BigDecimal totalImpuestos;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pago", referencedColumnName = "id")
    private MetodoPago metodoPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(BigDecimal totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    // *Factory Method (2)*:
    // Método estático para crear una factura con valores predeterminados.
    public static Factura crearFacturaPredeterminada() {
        Factura factura = new Factura();
        factura.setCodigo("FAC-" + System.currentTimeMillis()); // Código único basado en timestamp.
        factura.setFecha(new Date()); // Fecha actual.
        factura.setSubtotal(BigDecimal.ZERO); // Subtotal predeterminado.
        factura.setTotalImpuestos(BigDecimal.ZERO); // Impuestos predeterminados.
        factura.setTotal(BigDecimal.ZERO); // Total predeterminado.
        factura.setEstado("PENDIENTE"); // Estado inicial.
        return factura;
    }

    // *Builder (2)*:
    // Builder interno para crear facturas de manera flexible.
    public static class Builder {
        private final Factura factura;

        public Builder() {
            this.factura = new Factura();
        }

        public Builder conCodigo(String codigo) {
            factura.setCodigo(codigo);
            return this;
        }

        public Builder conFecha(Date fecha) {
            factura.setFecha(fecha);
            return this;
        }

        public Builder conSubtotal(BigDecimal subtotal) {
            factura.setSubtotal(subtotal);
            return this;
        }

        public Builder conTotalImpuestos(BigDecimal impuestos) {
            factura.setTotalImpuestos(impuestos);
            return this;
        }

        public Builder conTotal(BigDecimal total) {
            factura.setTotal(total);
            return this;
        }

        public Builder conEstado(String estado) {
            factura.setEstado(estado);
            return this;
        }

        public Builder conCliente(Cliente cliente) {
            factura.setCliente(cliente);
            return this;
        }

        public Builder conMetodoPago(MetodoPago metodoPago) {
            factura.setMetodoPago(metodoPago);
            return this;
        }

        public Factura build() {
            return factura;
        }
    }

    // *Fabricación Pura (2)*:
    // Calcula el total basado en subtotal e impuestos, sin modificar el estado de la factura.
    public BigDecimal calcularTotal(BigDecimal subtotal, BigDecimal impuestos) {
        return subtotal.add(impuestos);
    }

    // *Fabricación Pura (3)*:
    // Calcula los impuestos basados en un porcentaje sin alterar el estado de la factura.
    public BigDecimal calcularImpuestos(BigDecimal subtotal, BigDecimal porcentajeImpuestos) {
        return subtotal.multiply(porcentajeImpuestos);
    }
}
