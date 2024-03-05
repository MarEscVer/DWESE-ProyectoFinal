package com.example.ApiFinal.models.alumno;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa los datos de un alumno utilizados para la creación o actualización de un alumno en el sistema.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO{

	private String nif;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String ciudad;
	private String direccion;
	private String telefono;
	private Date fechaNacimiento;
	private String sexo;
	
}
