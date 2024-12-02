package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Categoria;
import com.example.FacturaYa.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoria(Long id) {
        return categoriaRepository.findById(id);
    }

    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        // Buscar categoría por ID
        Optional<Categoria> categoriaExistenteOpt = categoriaRepository.findById(id);

        if (categoriaExistenteOpt.isPresent()) {
            Categoria categoriaExistente = categoriaExistenteOpt.get();

            // Modificar los campos
            categoriaExistente.setDescripcion(categoria.getDescripcion());

            // Guardar categoría actualizada
            return categoriaRepository.save(categoriaExistente);
        } else {
            // Si no se encuentra, retornar null o lanzar una excepción
            return null;
        }
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
