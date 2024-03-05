package com.example.ApiFinal.service;

import java.util.List;
import java.util.Optional;
import com.example.ApiFinal.models.alumno.Alumno;

public interface AlumnoService {	
	public List<Alumno> getAllAlumnos();
	public Optional<Alumno> findAlumnoById(Long id);
	public Alumno insertarAlumno(Alumno alumno);
	public Alumno actualizarAlumno(Alumno alumno);
	public Alumno findAlumnoByNif(String nif);
	void deleteAlumnoById(Long id);
}
