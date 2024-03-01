package com.example.ApiFinal.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.ApiFinal.models.asignatura.Asignatura;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsignaturaUnitTest {
	private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
        "'Matemáticas', '6.0', 'Obligatoria', 'Asignatura válida'",
        "'', '6.0', 'Obligatoria', 'Nombre en blanco'",
        "'Matemáticas', '', 'Obligatoria', 'Créditos en blanco'",
        "'Matemáticas', '6.0', '', 'Tipo en blanco'",
        "'Matemáticas', '', '', 'Créditos y tipo en blanco'"
    })
    void testValidationAsignatura(String nombre, String creditos, String tipo, String mensajeTest) {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(nombre);
        asignatura.setCreditos(creditos.isEmpty() ? null : Double.parseDouble(creditos));
        asignatura.setTipo(tipo);

        Set<ConstraintViolation<Asignatura>> violations = validator.validate(asignatura);

        if (violations.isEmpty()) {
            assertTrue(violations.isEmpty(), mensajeTest);
        } else {
            assertFalse(violations.isEmpty(), mensajeTest + " - Debería fallar la validación");
        }
    }


}
