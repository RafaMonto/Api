package com.example.FacturaYa.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacturaYa.entity.Factura;

@Repository
// *Polimorfismo (2)*: JpaRepository permite múltiples implementaciones dependiendo del proveedor de base de datos.
public interface FacturaRepository extends JpaRepository<Factura,Long>{

    static List<Factura> findAllByFechaBetween(Date inicioMes, Date finMes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByFechaBetween'");
    }

}