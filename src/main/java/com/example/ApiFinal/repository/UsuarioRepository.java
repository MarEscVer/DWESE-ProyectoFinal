package com.example.ApiFinal.repository;

import com.example.ApiFinal.models.usuario.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Usuario findByUserName(String userName);

}
