package com.example.ApiFinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.usuario.Usuario;
import com.example.ApiFinal.repository.UsuarioRepository;
import com.example.ApiFinal.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Override
	public Usuario insertUsuario(Usuario usuario) {
		if (usuario != null) {
			return usuarioRepo.save(usuario);
		} else {
			return null;
		}
	}

}
