package com.example.ApiFinal.models.asignatura;

import java.util.HashSet;
import java.util.Set;

import com.example.ApiFinal.models.AlumnoAsignatura;
import com.example.ApiFinal.models.alumno.Alumno;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="asignatura")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asignatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private Double creditos;
	
	@OneToMany(mappedBy="asignatura", cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<AlumnoAsignatura> alumnosAsignatura = new HashSet<>();
}
