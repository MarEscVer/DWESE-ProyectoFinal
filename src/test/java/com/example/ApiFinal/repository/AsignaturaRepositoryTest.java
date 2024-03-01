package com.example.ApiFinal.repository;

import com.example.ApiFinal.models.asignatura.Asignatura;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AsignaturaRepositoryTest {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Test
    public void findByNombre_ExistingNombre_ReturnsAsignatura() {
        // Given
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Matemáticas");
        asignatura.setCreditos(5.0);
        asignatura.setTipo("Obligatoria");
        asignaturaRepository.save(asignatura);

        // When
        Asignatura foundAsignatura = asignaturaRepository.findByNombre("Matemáticas");

        // Then
        assertNotNull(foundAsignatura, "La asignatura debería existir");
        assertEquals("Matemáticas", foundAsignatura.getNombre(), "El nombre de la asignatura debería ser 'Matemáticas'");
        assertEquals(5.0, foundAsignatura.getCreditos(), "Los créditos de la asignatura deberían ser 5.0");
        assertEquals("Obligatoria", foundAsignatura.getTipo(), "El tipo de la asignatura debería ser 'Obligatoria'");
    }
}
