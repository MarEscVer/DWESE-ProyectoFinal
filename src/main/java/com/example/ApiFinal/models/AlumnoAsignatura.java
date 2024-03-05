package com.example.ApiFinal.models;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.asignatura.Asignatura;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa la relación entre un alumno y una asignatura, junto con la nota obtenida.
 */
@Entity
@Table(name="alumno_matricula_asignatura")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoAsignatura {

	@Id
	@ManyToOne
	@JoinColumn(name="id_alumno", insertable=false,updatable=false)
	private Alumno alumno;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_asignatura", insertable=false,updatable=false)
	private Asignatura asignatura;
	
	@Column(nullable=false)
	private int nota;
	
}
