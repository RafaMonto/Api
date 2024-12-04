package com.example.FacturaYa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "metodos_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(nullable = false, length = 100)
    private String descripcion;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    @Column(length = 50)
    private String identificador;

    // *Bridge (1)*:
    // Define un método de validación que puede ser extendido para diferentes tipos de métodos de pago.
    public boolean validarMetodoPago() {
        return descripcion != null && !descripcion.isEmpty();
    }
}