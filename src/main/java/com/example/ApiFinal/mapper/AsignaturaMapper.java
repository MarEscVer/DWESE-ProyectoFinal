package com.example.ApiFinal.mapper;

import com.example.ApiFinal.models.asignatura.Asignatura;
import com.example.ApiFinal.models.asignatura.AsignaturaDTO;

public class AsignaturaMapper {

	public static Asignatura maptoAsignatura(AsignaturaDTO asignaturaDTO) {
		return Asignatura.builder()
				.id(asignaturaDTO.getId())
				.nombre(asignaturaDTO.getNombre())
				.creditos(asignaturaDTO.getCreditos())
				.build();
	}
	
	public static AsignaturaDTO maptoAsignaturaDTO(Asignatura asignatura) {
		return AsignaturaDTO.builder()
				.id(asignatura.getId())
				.nombre(asignatura.getNombre())
				.creditos(asignatura.getCreditos())
				.build();
	}
	
}
