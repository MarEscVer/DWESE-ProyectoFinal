package com.example.ApiFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ApiFinal.exception.GlobalExceptionHandler;
import com.example.ApiFinal.models.AlumnoAsignatura;
import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.alumno.AlumnoDTO;
import com.example.ApiFinal.models.asignatura.Asignatura;
import com.example.ApiFinal.service.impl.AlumnoServiceImpl;
import com.example.ApiFinal.service.impl.AsignaturaServiceImpl;

/**
 * Controlador para gestionar las operaciones relacionadas con los alumnos.
 */
@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

	@Autowired
	AlumnoServiceImpl alumnoService;


	/**
     * Recupera todos los alumnos almacenados en la base de datos.
     * 
     * @return ResponseEntity con la lista de alumnos y el código de estado HTTP.
     */
    @GetMapping("/")
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     * Recupera un alumno específico por su ID.
     * 
     * @param id ID del alumno a recuperar.
     * @return ResponseEntity con el alumno recuperado y el código de estado HTTP.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlumnoById(@PathVariable("id") Long id) {
        try{
        	Optional<Alumno> alumnoOptional = alumnoService.findAlumnoById(id);
        	return alumnoOptional.map(alumno -> new ResponseEntity<>(alumno, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (GlobalExceptionHandler e) {
        	return new ResponseEntity<>("No se encontró alumno con el ID", HttpStatus.NOT_FOUND);
        }
        
    }


    /**
     * Inserta un nuevo alumno en la base de datos.
     * 
     * @param alumnoDTO Datos del nuevo alumno.
     * @return ResponseEntity con el alumno insertado y el código de estado HTTP.
     */
    @PostMapping("/")
    public ResponseEntity<Alumno> insertarAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        if (alumnoService.findAlumnoByNif(alumnoDTO.getNif()) == null) {
            Alumno alumno = new Alumno(alumnoDTO);
            Alumno nuevoAlumno = alumnoService.insertarAlumno(alumno);
            return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /**
     * Elimina un alumno específico por su ID.
     * 
     * @param id ID del alumno a eliminar.
     * @return ResponseEntity con el mensaje de éxito y el código de estado HTTP correspondiente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumnoById(@PathVariable("id") Long id) {
        try {
            alumnoService.deleteAlumnoById(id);
            return new ResponseEntity<>("Alumno eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo eliminar el alumno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Actualiza la información de un alumno existente en la base de datos.
     * 
     * @param id         ID del alumno a actualizar.
     * @param alumnoDTO  Datos actualizados del alumno.
     * @return ResponseEntity con el alumno actualizado y el código de estado HTTP.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable("id") Long id, @RequestBody AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumnoOptional = alumnoService.findAlumnoById(id);
        
        if (alumnoOptional.isPresent()) {
            Alumno alumnoExistente = alumnoOptional.get();
            
            // Actualizar la información del alumno con los datos proporcionados
            alumnoExistente.setNif(alumnoDTO.getNif());
            alumnoExistente.setNombre(alumnoDTO.getNombre());
            alumnoExistente.setApellido1(alumnoDTO.getApellido1());
            alumnoExistente.setApellido2(alumnoDTO.getApellido2());
            alumnoExistente.setCiudad(alumnoDTO.getCiudad());
            alumnoExistente.setDireccion(alumnoDTO.getDireccion());
            alumnoExistente.setTelefono(alumnoDTO.getTelefono());
            alumnoExistente.setFechaNacimiento(alumnoDTO.getFechaNacimiento());
            alumnoExistente.setSexo(alumnoDTO.getSexo());
            
            // Guardar los cambios en la base de datos
            Alumno alumnoActualizado = alumnoService.actualizarAlumno(alumnoExistente);
            
            return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
        } else {
        	return new ResponseEntity<>("No se pudo editar el alumno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
