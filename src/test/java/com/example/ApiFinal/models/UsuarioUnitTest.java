package com.example.ApiFinal.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.ApiFinal.models.usuario.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioUnitTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
        "'ApellidosUsuario', 'usuario@example.com', 'NombreUsuario', 'password', 'USER', 'usuario_normal', 'Usuario válido'",
        "'', 'usuario@example.com', 'NombreUsuario', 'password', 'USER', 'usuario_normal', 'Apellidos vacíos'",
        "'ApellidosUsuario', '', 'NombreUsuario', 'password', 'USER', 'usuario_normal', 'Email vacío'",
        "'ApellidosUsuario', 'usuario@example.com', '', 'password', 'USER', 'usuario_normal', 'Nombre vacío'",
        "'ApellidosUsuario', 'usuario@example.com', 'NombreUsuario', '', 'USER', 'usuario_normal', 'Password vacío'",
        "'ApellidosUsuario', 'usuario@example.com', 'NombreUsuario', 'password', '', 'usuario_normal', 'Role vacío'",
        "'ApellidosUsuario', 'usuario@example.com', 'NombreUsuario', 'password', 'USER', '', 'UserName vacío'",
        "'ApellidosUsuario', 'usuario@example.com', 'NombreUsuario', 'password', 'USER', 'usuario_normal', 'Usuario válido'"
    })
    void testValidationUsuario(String apellidos, String email, String nombre, String password, String role, String userName, String mensajeTest) {
        Usuario usuario = new Usuario();
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        usuario.setRole(role);
        usuario.setUserName(userName);

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        if (violations.isEmpty()) {
            assertTrue(violations.isEmpty(), mensajeTest);
        } else {
            assertFalse(violations.isEmpty(), mensajeTest + " - Debería fallar la validación");
        }
    }
}
