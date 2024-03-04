package com.example.ApiFinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.usuario.Usuario;
import com.example.ApiFinal.repository.UsuarioRepository;
import com.example.ApiFinal.service.UsuarioService;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con los usuarios.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return usuarioRepo.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
	/**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuario El usuario a insertar.
     * @return El usuario insertado, o null si el usuario es nulo.
     */
	@Override
	public Usuario insertUsuario(Usuario usuario) {
		if (usuario != null) {
			return usuarioRepo.save(usuario);
		} else {
			return null;
		}
	}

}
