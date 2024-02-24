package com.example.ApiFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.asignatura.Asignatura;
import com.example.ApiFinal.repository.AsignaturaRepository;
import com.example.ApiFinal.service.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

	@Autowired
	AsignaturaRepository asignaturaRepo;

	@Override
	public List<Asignatura> getAllAsignaturas() {
		List<Asignatura> asignaturas = asignaturaRepo.findAll();
		if (asignaturas != null && asignaturas.size() > 0) {

			return asignaturas;
		} else {
			return new ArrayList<Asignatura>();
		}
	}

	@Override
	public Optional<Asignatura> findAsignaturaById(Long id) {
		return asignaturaRepo.findById(id);
	}

	@Override
	public Asignatura getAsignaturaByName(String nombre) {
		if (nombre != null) {
			return asignaturaRepo.findByNombre(nombre);
		}
		return null;
	}

	@Override
	public Asignatura insertarAsignatura(Asignatura asignatura) {
		if (asignatura != null && getAsignaturaByName(asignatura.getNombre()) == null) {
			return asignaturaRepo.save(asignatura);
		}
		return null;
	}

	@Override
	public Asignatura actualizarAsignatura(Asignatura asignatura) {
		if (asignatura != null && asignatura.getId() != null && asignatura.getNombre() != null) {
			return asignaturaRepo.save(asignatura);
		}
		return null;
	}
}
