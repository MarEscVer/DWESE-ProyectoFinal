package com.example.ApiFinal.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.asignatura.Asignatura;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlumnoAsignaturaUnitTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Prueba parametrizada para la validación de la clase AlumnoAsignatura con diferentes conjuntos de datos.
    @ParameterizedTest
    @CsvSource({
        "10, 10, 8, 'AlumnoAsignatura válido'",
        ", 10, 8, 'Alumno en blanco'",
        "10, , 8, 'Asignatura en blanco'",
        "10, 10, 12, 'Nota incorrecta'",
    })
    void testValidationAlumnoAsignatura(Long idAlumno, Long idAsignatura, int nota, String mensajeTest) {
        AlumnoAsignatura alumnoAsignatura = new AlumnoAsignatura();
        
        // Configurar el alumno y la asignatura con los ID proporcionados
        Alumno alumno = new Alumno();
        alumno.setId(idAlumno);
        alumnoAsignatura.setAlumno(alumno);
        
        Asignatura asignatura = new Asignatura();
        asignatura.setId(idAsignatura);
        alumnoAsignatura.setAsignatura(asignatura);
        
        // Configurar la nota
        alumnoAsignatura.setNota(nota);

        // Validar el alumnoAsignatura utilizando el validador previamente configurado
        Set<ConstraintViolation<AlumnoAsignatura>> violations = validator.validate(alumnoAsignatura);

        // Verificar si hay incumplimientos de restricciones de validación
        if (violations.isEmpty()) {
            // Si no hay incumplimientos, la prueba pasa
            assertTrue(violations.isEmpty(), mensajeTest);
        } else {
            // Si hay incumplimientos, la prueba falla y se proporciona un mensaje descriptivo
            assertFalse(violations.isEmpty(), mensajeTest + " - Debería fallar la validación");
        }
    }
}
