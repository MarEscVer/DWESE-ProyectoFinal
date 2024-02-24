package com.example.ApiFinal.service;

import java.util.List;
import java.util.Optional;
import com.example.ApiFinal.models.asignatura.Asignatura;


public interface AsignaturaService {
	public List<Asignatura> getAllAsignaturas();
	public Optional<Asignatura> findAsignaturaById(Long id);
	public Asignatura getAsignaturaByName(String nombre);
	public Asignatura insertarAsignatura(Asignatura asignatura);
	public Asignatura actualizarAsignatura(Asignatura asignatura);
}
