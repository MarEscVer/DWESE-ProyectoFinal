package com.example.ApiFinal.repository;

import com.example.ApiFinal.models.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void findByUserName_ExistingUserName_ReturnsUsuario() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setUserName("usuario_prueba");
        usuario.setEmail("usuario_prueba@example.com");
        usuario.setNombre("Usuario");
        usuario.setApellidos("Prueba");
        usuario.setPassword("contraseña");
        usuario.setRole("ROLE_USER");
        usuario.setActivo(true);
        usuarioRepository.save(usuario);

        // When
        Usuario foundUsuario = usuarioRepository.findByUserName("usuario_prueba");

        // Then
        assertNotNull(foundUsuario, "El usuario debería existir");
        assertEquals("usuario_prueba", foundUsuario.getUserName(), "El nombre de usuario debería ser 'usuario_prueba'");
        assertEquals("usuario_prueba@example.com", foundUsuario.getEmail(), "El email del usuario debería ser 'usuario_prueba@example.com'");
        assertEquals("Usuario", foundUsuario.getNombre(), "El nombre del usuario debería ser 'Usuario'");
        assertEquals("Prueba", foundUsuario.getApellidos(), "Los apellidos del usuario deberían ser 'Prueba'");
        assertEquals("contraseña", foundUsuario.getPassword(), "La contraseña del usuario debería ser 'contraseña'");
        assertEquals("ROLE_USER", foundUsuario.getRole(), "El rol del usuario debería ser 'ROLE_USER'");
        assertEquals(true, foundUsuario.isActivo(), "El usuario debería estar activo");
    }
}
