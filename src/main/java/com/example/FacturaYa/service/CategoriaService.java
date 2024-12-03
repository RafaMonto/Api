package com.example.FacturaYa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacturaYa.entity.Categoria;
import com.example.FacturaYa.repository.CategoriaRepository;

@Service
// SRP: Esta clase se encarga únicamente de manejar la lógica de negocio relacionada con las categorías.
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    // SRP: Este método se encarga únicamente de obtener todas las categorías.
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll(); // SRP: Delegación a CategoriaRepository.
    }

    // SRP: Este método se encarga únicamente de obtener una categoría por su ID.
    public Optional<Categoria> getCategoria(Long id) {
        return categoriaRepository.findById(id); // SRP: Delegación a CategoriaRepository.
    }

    // SRP: Este método se encarga únicamente de guardar una nueva categoría.
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria); // SRP: Delegación a CategoriaRepository.
    }

    // SRP: Este método se encarga únicamente de actualizar una categoría existente.
    public Categoria updateCategoria(Long id, Categoria categoria) {
        Optional<Categoria> categoriaExistenteOpt = categoriaRepository.findById(id);

        if (categoriaExistenteOpt.isPresent()) {
            Categoria categoriaExistente = categoriaExistenteOpt.get();
            categoriaExistente.setDescripcion(categoria.getDescripcion());
            return categoriaRepository.save(categoriaExistente);
        } else {
            return null;
        }
    }

    // SRP: Este método se encarga únicamente de eliminar una categoría por su ID.
    public void delete(Long id) {
        categoriaRepository.deleteById(id); // SRP: Delegación a CategoriaRepository.
    }
}