package com.example.ApiFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.repository.AlumnoRepository;
import com.example.ApiFinal.service.AlumnoService;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con los alumnos.
 */
@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	AlumnoRepository alumnoRepo;
	
	/**
     * Busca un alumno por su ID.
     * @param id ID del alumno a buscar.
     * @return El alumno encontrado, o un objeto Optional vacío si no se encuentra.
     */
	@Override
	public Optional<Alumno> findAlumnoById(Long id) {
		
		if(id != null) {
			return alumnoRepo.findById(id);
		}
		return null;
	}

	/**
     * Obtiene todos los alumnos.
     * @return Lista de todos los alumnos, o una lista vacía si no hay ningún alumno.
     */
	@Override
	public List<Alumno> getAllAlumnos() {
		
		List<Alumno> alumns = alumnoRepo.findAll();	
		
		
		//Verificando que he obtenido algo 
		if (alumns!= null && alumns.size()> 0) {
			
			return alumns;
		}
		
		//No he obtenido nada devuelvo una lista vacía (para no devolver nulo)
		return new ArrayList<Alumno>();
	}

	/**
     * Inserta un nuevo alumno en la base de datos.
     * @param alumno El alumno a insertar.
     * @return El alumno insertado, o null si el alumno es nulo o ya tiene un ID asignado.
     */
	@Override
	public Alumno insertarAlumno(Alumno alumno) {
		
		if (alumno!=null && alumno.getId()== null) {
			Alumno alumn = alumnoRepo.save(alumno);
			return alumn;
		}
		
		return null;
	}

	 /**
     * Actualiza los datos de un alumno en la base de datos.
     * @param alumno El alumno con los datos actualizados.
     * @return El alumno actualizado, o null si el alumno es nulo o no tiene un ID asignado.
     */
	@Override
	public Alumno actualizarAlumno(Alumno alumno) {
		
		if (alumno==null || alumno.getId() ==null) {
			System.out.println("No hay datos");
			return null;
		}
		
		return alumnoRepo.save(alumno); 
	}

	/**
     * Busca un alumno por su NIF.
     * @param nif El NIF del alumno a buscar.
     * @return El alumno encontrado, o null si el NIF es nulo o está vacío.
     */
	@Override
	public Alumno findAlumnoByNif(String nif) {
		if(!nif.equals("") && nif!=null) {
			return alumnoRepo.findByNif(nif);
		}
		return null;
	}

	/**
	 * Eliminar alumno por id
	 */
	@Override
    public void deleteAlumnoById(Long id) {
        Optional<Alumno> alumnoOptional = alumnoRepo.findById(id);
        
        // Verificar si el alumno existe
        if (alumnoOptional.isPresent()) {
            // Si existe, eliminarlo de la base de datos
        	alumnoRepo.deleteById(id);
        } else {
            // Si no existe, lanzar una excepción o manejar de alguna otra manera
            throw new IllegalArgumentException("No se encontró ningún alumno con el ID proporcionado");
        }
    }


}
