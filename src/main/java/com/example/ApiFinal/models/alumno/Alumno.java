package com.example.ApiFinal.models.alumno;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.ApiFinal.models.AlumnoAsignatura;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 9, nullable = true)
	private String nif;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido1;

	@Column(nullable = true)
	private String apellido2;

	@Column(nullable = false)
	private String ciudad;

	@Column(nullable = false)
	private String direccion;

	@Column(nullable = true)
	private String telefono;

	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fechaNacimiento;

	@Column(nullable = false, length = 1)
	private String sexo;

	@OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AlumnoAsignatura> alumnoAsignaturas = new HashSet<>();

	public Alumno(AlumnoDTO alumnoDTO) {
		this.nif = alumnoDTO.getNif();
		this.apellido1 = alumnoDTO.getApellido1();
		this.apellido2 = alumnoDTO.getApellido2();
		this.nombre = alumnoDTO.getNombre();
		this.ciudad = alumnoDTO.getCiudad();
		this.direccion = alumnoDTO.getDireccion();
		this.telefono = alumnoDTO.getTelefono();
		this.fechaNacimiento = alumnoDTO.getFechaNacimiento();
		this.sexo = alumnoDTO.getSexo();
	}
}
