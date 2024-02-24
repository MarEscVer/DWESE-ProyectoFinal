package com.example.ApiFinal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.asignatura.Asignatura;
import com.example.ApiFinal.repository.AsignaturaRepository;
import com.example.ApiFinal.service.AsignaturaService;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con las asignaturas.
 */
@Service
public class AsignaturaServiceImpl implements AsignaturaService {

	@Autowired
	AsignaturaRepository asignaturaRepo;

	/**
     * Obtiene todas las asignaturas.
     * @return Lista de todas las asignaturas, o una lista vacía si no hay ninguna.
     */
	@Override
	public List<Asignatura> getAllAsignaturas() {
		List<Asignatura> asignaturas = asignaturaRepo.findAll();
		if (asignaturas != null && asignaturas.size() > 0) {

			return asignaturas;
		} else {
			return new ArrayList<Asignatura>();
		}
	}

	/**
     * Busca una asignatura por su ID.
     * @param id ID de la asignatura a buscar.
     * @return La asignatura encontrada, o un objeto Optional vacío si no se encuentra.
     */
	@Override
	public Optional<Asignatura> findAsignaturaById(Long id) {
		return asignaturaRepo.findById(id);
	}

	/**
     * Busca una asignatura por su nombre.
     * @param nombre Nombre de la asignatura a buscar.
     * @return La asignatura encontrada, o null si el nombre es nulo o está vacío.
     */
	@Override
	public Asignatura getAsignaturaByName(String nombre) {
		if (nombre != null) {
			return asignaturaRepo.findByNombre(nombre);
		}
		return null;
	}

	/**
     * Inserta una nueva asignatura en la base de datos.
     * @param asignatura La asignatura a insertar.
     * @return La asignatura insertada, o null si la asignatura es nula o ya existe una asignatura con el mismo nombre.
     */
	@Override
	public Asignatura insertarAsignatura(Asignatura asignatura) {
		if (asignatura != null && getAsignaturaByName(asignatura.getNombre()) == null) {
			return asignaturaRepo.save(asignatura);
		}
		return null;
	}

	/**
     * Actualiza los datos de una asignatura en la base de datos.
     * @param asignatura La asignatura con los datos actualizados.
     * @return La asignatura actualizada, o null si la asignatura es nula o no tiene un ID asignado.
     */
	@Override
	public Asignatura actualizarAsignatura(Asignatura asignatura) {
		if (asignatura != null && asignatura.getId() != null && asignatura.getNombre() != null) {
			return asignaturaRepo.save(asignatura);
		}
		return null;
	}
}
