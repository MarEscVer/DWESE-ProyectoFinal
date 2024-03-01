package com.example.ApiFinal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.ApiFinal.models.alumno.Alumno;

@SpringBootTest
@Transactional
public class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Test
    public void findByNif_ExistingNif_ReturnsAlumno() {
        // Crear un alumno de ejemplo y guardarlo en la base de datos
    	Alumno alumno = new Alumno();
        alumno.setNif("12345678A");
        alumno.setNombre("Nombre");
        alumno.setApellido1("Apellido1");
        alumno.setCiudad("Ciudad");
        alumno.setDireccion("Dirección");
        alumno.setFechaNacimiento(new java.sql.Date(System.currentTimeMillis()));
        alumno.setSexo("M");
        alumnoRepository.save(alumno);

        // Invocar el método findByNif para buscar al alumno por su NIF
        Alumno foundAlumno = alumnoRepository.findByNif("123456789");

        // Verificar que el alumno encontrado no sea nulo
        assertNotNull(foundAlumno);

        // Verificar que el NIF del alumno encontrado sea el mismo que el NIF del alumno creado
        assertEquals("123456789", foundAlumno.getNif());
    }

    @Test
    public void findByNif_NonExistingNif_ReturnsNull() {
        // Invocar el método findByNif con un NIF que no existe en la base de datos
        Alumno foundAlumno = alumnoRepository.findByNif("nonExistingNif");

        // Verificar que el alumno no sea encontrado y, por lo tanto, sea nulo
        assertEquals(null, foundAlumno);
    }
}
