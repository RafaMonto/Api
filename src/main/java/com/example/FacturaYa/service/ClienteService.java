package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Cliente;
import com.example.FacturaYa.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Long id) {
        return clienteRepository.findById(id);
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        // Buscar cliente por ID
        Optional<Cliente> clienteExistenteOpt = clienteRepository.findById(id);

        if (clienteExistenteOpt.isPresent()) {
            Cliente clienteExistente = clienteExistenteOpt.get();

            // Modificar los campos
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setEmail(cliente.getEmail());

            // Guardar cliente actualizado
            return clienteRepository.save(clienteExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
