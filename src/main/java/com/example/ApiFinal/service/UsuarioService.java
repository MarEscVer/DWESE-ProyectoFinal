package com.example.ApiFinal.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ApiFinal.models.usuario.Usuario;

public interface UsuarioService {
	UserDetailsService userDetailsService();
	public Usuario insertUsuario(Usuario usuario);
}