package com.example.FacturaYa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.FacturaYa.entity.Factura;
import com.example.FacturaYa.entity.Informe;
import com.example.FacturaYa.service.InformeService;

@RestController
@RequestMapping(path = "api/informes")
public class InformeController {

    @Autowired
    private InformeService informeService;

    @GetMapping
    public List<Informe> getAll() {
        return informeService.getAllInformes();
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody Informe informe) {
        informeService.saveOrUpdate(informe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long informeId) {
        informeService.delete(informeId);
    }

    @GetMapping("/{id}")
    public Optional<Informe> getById(@PathVariable("id") Long informeId) {
        return informeService.getInforme(informeId);
    }

    @GetMapping("/ventas/{mes}/{anio}")
    public List<Factura> getVentasDelMes(@PathVariable int mes, @PathVariable int anio) {
        return informeService.getVentasDelMes(mes, anio);
    }

    // *Adapter (1)*:
    // Convierte un informe en un formato CSV compatible desde JSON.
    @GetMapping("/convertirCSV/{id}")
    public String convertirInformeACSV(@PathVariable("id") Long informeId) {
        return informeService.convertirInformeACSV(informeId);
    }
}