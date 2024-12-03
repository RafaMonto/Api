package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Cliente;
import com.example.FacturaYa.repository.ClienteRepository;

@Service
// SRP: Esta clase gestiona la lógica de negocio de los clientes.
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        // ISP: Solo interactúa con los métodos de lectura del repositorio.
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Long id) {
        // ISP: Método de lectura.
        return clienteRepository.findById(id);
    }

    public void save(Cliente cliente) {
        // ISP: Método de escritura para guardar un cliente.
        clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        // OCP: La lógica para actualizar un cliente se puede extender sin modificar este método.
        Optional<Cliente> clienteExistenteOpt = clienteRepository.findById(id);
        if (clienteExistenteOpt.isPresent()) {
            Cliente clienteExistente = clienteExistenteOpt.get();
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setTelefono(cliente.getTelefono());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setCiudad(cliente.getCiudad());
            clienteExistente.setDepartamento(cliente.getDepartamento());
            return clienteRepository.save(clienteExistente);
        } else {
            return null; // Manejo de errores puede extenderse sin modificar este método.
        }
    }

    public void delete(Long id) {
        // ISP: Método de escritura para eliminar un cliente.
        clienteRepository.deleteById(id);
    }
}