package com.example.ApiFinal.mapper;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.alumno.AlumnoDTO;

public class AlumnoMapper {

	public static Alumno maptoAlumno(AlumnoDTO alumnoDTO) {
		return Alumno.builder()
				.id(alumnoDTO.getId())
				.nif(alumnoDTO.getNif())
				.nombre(alumnoDTO.getNombre())
				.apellido1(alumnoDTO.getApellido1())
				.apellido2(alumnoDTO.getApellido2())
				.build();
	}
	
	public static AlumnoDTO maptoAlumnoDTO(Alumno alumno) {
		return AlumnoDTO.builder()
				.id(alumno.getId())
				.nif(alumno.getNif())
				.nombre(alumno.getNombre())
				.apellido1(alumno.getApellido1())
				.apellido2(alumno.getApellido2())
				.build();
	}
	
}
