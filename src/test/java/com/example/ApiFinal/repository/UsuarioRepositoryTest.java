package com.example.ApiFinal.repository;

import com.example.ApiFinal.models.usuario.Role;
import com.example.ApiFinal.models.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Optional;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void findByEmail_ExistingEmail_ReturnsUsuario() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setFirstName("usuario_prueba");
        usuario.setEmail("usuario_prueba@example.com");
        usuario.setFirstName("Usuario");
        usuario.setLastName("Prueba");
        usuario.setPassword("contraseña");
        usuario.setRoles(Collections.singleton(Role.ROLE_USER));
        usuarioRepository.save(usuario);

        // When
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail("usuario_prueba@example.com");

     // Then
        assertTrue(optionalUsuario.isPresent(), "El usuario debería existir");
        Usuario foundUsuario = optionalUsuario.get();
        assertEquals("usuario_prueba", foundUsuario.getUsername(), "El nombre de usuario debería ser 'usuario_prueba'");
        assertEquals("usuario_prueba@example.com", foundUsuario.getEmail(), "El email del usuario debería ser 'usuario_prueba@example.com'");
        assertEquals("Usuario", foundUsuario.getFirstName(), "El nombre del usuario debería ser 'Usuario'");
        assertEquals("Prueba", foundUsuario.getLastName(), "Los apellidos del usuario deberían ser 'Prueba'");
        assertEquals("contraseña", foundUsuario.getPassword(), "La contraseña del usuario debería ser 'contraseña'");
        assertEquals(Collections.singleton(Role.ROLE_USER), foundUsuario.getRoles(), "Los roles del usuario deberían ser 'ROLE_USER'");
    }
}
