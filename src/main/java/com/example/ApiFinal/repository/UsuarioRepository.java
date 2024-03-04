package com.example.ApiFinal.repository;

import com.example.ApiFinal.models.usuario.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
}
