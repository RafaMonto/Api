package com.example.FacturaYa.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "factura")
@XmlRootElement // Permite la serialización a XML
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String codigo;

    @Column(nullable = false)
    // Formato de fecha personalizado para JAXB
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
}