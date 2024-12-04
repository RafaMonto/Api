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
    private InformeRepository informeRepository;

    @Autowired

    // *Singleton (1)*:
    // Uso del Singleton para asegurar que una instancia del servicio sea compartida.
    private static InformeService instance;

    public static InformeService getInstance() {
        if (instance == null) {
            instance = new InformeService();
        }
        return instance;
    }

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
        // *Bridge (1)*:
        // La lógica de inicio y fin de mes es independiente del origen de datos (puente entre lógica y datos).
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
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return (Date) cal.getTime();
    }

    // *Adapter (3)*:
    // Usa el método convertirACSV de Informe para transformar el JSON en formato CSV.
    public String convertirInformeACSV(Long id) {
        Optional<Informe> informe = informeRepository.findById(id);
        return informe.map(Informe::convertirACSV).orElse("Informe no encontrado");
    }

    // *Singleton (2)*:
    // Servicio auxiliar para cálculos únicos con una sola instancia.
    private static Calendar servicioDeFechas;

    public static Calendar getServicioDeFechas() {
        if (servicioDeFechas == null) {
            servicioDeFechas = Calendar.getInstance();
        }
        return servicioDeFechas;
    }
}