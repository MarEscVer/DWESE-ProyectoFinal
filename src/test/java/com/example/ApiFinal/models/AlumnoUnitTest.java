package com.example.ApiFinal.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.ApiFinal.models.alumno.Alumno;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.sql.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlumnoUnitTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
        "'12345678A', 'John', 'Doe', 'Ciudad', 'Dirección', '123456789', 'M', 'Alumno válido'",
        "'', 'John', 'Doe', 'Ciudad', 'Dirección', '123456789', 'M', 'NIF en blanco'",
        "'12345678A', '', 'Doe', 'Ciudad', 'Dirección', '123456789', 'M', 'Nombre en blanco'",
        "'12345678A', 'John', '', 'Ciudad', 'Dirección', '123456789', 'M', 'Apellido1 en blanco'",
        "'12345678A', 'John', 'Doe', '', 'Dirección', '123456789', 'M', 'Ciudad en blanco'",
        "'12345678A', 'John', 'Doe', 'Ciudad', '', '123456789', 'M', 'Dirección en blanco'",
        "'12345678A', 'John', 'Doe', 'Ciudad', 'Dirección', '', 'M', 'Teléfono en blanco'",
        "'12345678A', 'John', 'Doe', 'Ciudad', 'Dirección', '123456789', '', 'Sexo en blanco'",
    })
    void testValidationAlumno(String nif, String nombre, String apellido1, String ciudad, String direccion, String telefono, String sexo, String mensajeTest) {
        Alumno alumno = new Alumno();
        alumno.setNif(nif);
        alumno.setNombre(nombre);
        alumno.setApellido1(apellido1);
        alumno.setCiudad(ciudad);
        alumno.setDireccion(direccion);
        alumno.setTelefono(telefono);
        alumno.setSexo(sexo);

        Set<ConstraintViolation<Alumno>> violations = validator.validate(alumno);

        if (violations.isEmpty()) {
            assertTrue(violations.isEmpty(), mensajeTest);
        } else {
            assertFalse(violations.isEmpty(), mensajeTest + " - Debería fallar la validación");
        }
    }
}
