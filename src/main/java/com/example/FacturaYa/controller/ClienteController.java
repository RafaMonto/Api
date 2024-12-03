package com.example.FacturaYa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacturaYa.entity.Cliente;
import com.example.FacturaYa.service.ClienteService;

@RestController
@RequestMapping(path = "api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll() {
        // LSP: Funciona con cualquier implementación de ClienteService que se respete el contrato.
        return clienteService.getAllClientes();
    }

    @PostMapping
    public void saveUpdate(@RequestBody Cliente cliente) {
        // LSP: Se puede sustituir ClienteService con otra implementación sin romper el comportamiento.
        clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente); // Cumple con LSP.
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long clienteId) {
        // DIP: El controlador depende de abstracción (ClienteService) en lugar de implementaciones específicas.
        clienteService.delete(clienteId);
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getById(@PathVariable("id") Long clienteId) {
        return clienteService.getCliente(clienteId); // DIP: Controlador sigue independiente de implementaciones.
    }
}