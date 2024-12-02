package com.example.FacturaYa.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.entity.Informe;
import com.example.FacturaYa.repository.FacturaRepository;
import com.example.FacturaYa.repository.InformeRepository;

@Service
public class InformeService {

    @Autowired
    InformeRepository informeRepository;

    @Autowired
    FacturaRepository facturaRepository;

    public List<Informe> getAllInformes() {
        return informeRepository.findAll();
    }

    public Optional<Informe> getInforme(Long id) {
        return informeRepository.findById(id);
    }

    public void saveOrUpdate(Informe informe) {
        informeRepository.save(informe);
    }

    public void delete(Long id) {
        informeRepository.deleteById(id);
    }

    public List<Factura> getVentasDelMes(int mes, int anio) {
        // Consulta de ventas en un mes determinado
        return FacturaRepository.findAllByFechaBetween(getInicioMes(mes, anio), getFinMes(mes, anio));
    }

    private Date getInicioMes(int mes, int anio) {
        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes - 1, 1, 0, 0, 0);
        return (Date) cal.getTime();
    }

    private Date getFinMes(int mes, int anio) {
        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes, 1, 0, 0, 0);
        cal.add(Calendar.DAY_OF_MONTH, -1);  // Último día del mes
        return (Date) cal.getTime();
    }
}
