package com.example.ApiFinal.models.asignatura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa los datos de una asignatura utilizados para la creación o actualización de una asignatura en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsignaturaDTO {

	private String nombre;
	private Double creditos;
	private String tipo;
	
}
