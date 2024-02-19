package com.example.ApiFinal.models.asignatura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsignaturaDTO {

	private Long id;
	private String nombre;
	private Double creditos;
	
}
