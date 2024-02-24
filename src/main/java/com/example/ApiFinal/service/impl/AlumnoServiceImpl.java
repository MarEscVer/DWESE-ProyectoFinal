package com.example.ApiFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.repository.AlumnoRepository;
import com.example.ApiFinal.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	AlumnoRepository alumnoRepo;
	
	@Override
	public Optional<Alumno> findAlumnoById(Long id) {
		
		if(id != null) {
			return alumnoRepo.findById(id);
		}
		return null;
	}

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


	@Override
	public Alumno insertarAlumno(Alumno alumno) {
		
		if (alumno!=null && alumno.getId()== null) {
			Alumno alumn = alumnoRepo.save(alumno);
			return alumn;
		}
		
		return null;
	}

	@Override
	public Alumno actualizarAlumno(Alumno alumno) {
		
		if (alumno==null || alumno.getId() ==null) {
			System.out.println("No hay datos");
			return null;
		}
		
		return alumnoRepo.save(alumno); 
	}

	@Override
	public Alumno findAlumnoByNif(String nif) {
		if(!nif.equals("") && nif!=null) {
			return alumnoRepo.findByNif(nif);
		}
		return null;
	}


}
