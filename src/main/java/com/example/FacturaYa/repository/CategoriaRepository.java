package com.example.FacturaYa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacturaYa.entity.Categoria;

@Repository
// SRP: Esta interfaz se encarga únicamente de definir las operaciones de persistencia para la entidad Categoria.
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}