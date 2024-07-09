package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrosRepository extends JpaRepository<Libro,Long> {
    Optional<Libro> findByTituloContainingIgnoreCase(String titulo);
}
