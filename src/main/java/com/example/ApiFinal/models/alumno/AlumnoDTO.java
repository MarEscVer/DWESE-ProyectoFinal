package com.example.ApiFinal.models.alumno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoDTO{

	private Long id;
	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
}
