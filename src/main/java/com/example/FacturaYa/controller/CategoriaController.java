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

import com.example.FacturaYa.entity.Categoria;
import com.example.FacturaYa.service.CategoriaService;

@RestController
@RequestMapping(path = "api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    // SRP: Este método se encarga únicamente de manejar la solicitud GET para obtener todas las categorías.
    @GetMapping
    public List<Categoria> getAll() {
        return categoriaService.getAllCategorias(); // SRP: Delegación a CategoriaService.
    }

    // SRP: Este método se encarga únicamente de manejar la solicitud POST para guardar o actualizar una categoría.
    @PostMapping
    public void saveOrUpdate(@RequestBody Categoria categoria) {
        categoriaService.save(categoria); // SRP: Delegación a CategoriaService.
    }

    // SRP: Este método se encarga únicamente de manejar la solicitud PUT para actualizar una categoría específica.
    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.updateCategoria(id, categoria); // SRP: Delegación a CategoriaService.
    }

    // SRP: Este método se encarga únicamente de manejar la solicitud DELETE para eliminar una categoría específica.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long categoriaId) {
        categoriaService.delete(categoriaId); // SRP: Delegación a CategoriaService.
    }

    // SRP: Este método se encarga únicamente de manejar la solicitud GET para obtener una categoría por ID.
    @GetMapping("/{id}")
    public Optional<Categoria> getById(@PathVariable("id") Long categoriaId) {
        return categoriaService.getCategoria(categoriaId); // SRP: Delegación a CategoriaService.
    }
}