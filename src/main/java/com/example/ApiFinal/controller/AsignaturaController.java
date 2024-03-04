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

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.asignatura.Asignatura;
import com.example.ApiFinal.models.asignatura.AsignaturaDTO;
import com.example.ApiFinal.service.impl.AlumnoServiceImpl;
import com.example.ApiFinal.service.impl.AsignaturaServiceImpl;

/**
 * Controlador para gestionar las operaciones relacionadas con las asignaturas.
 */
@Controller
@RequestMapping("/asignaturas")
public class AsignaturaController {

	@Autowired
	AsignaturaServiceImpl asignaturaService;
	
	@Autowired
	AlumnoServiceImpl alumnoService;
	
	/**
     * Método para obtener todas las asignaturas.
     * @return ResponseEntity con la lista de asignaturas y el código de estado HTTP correspondiente.
     */
    @GetMapping("/")
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        return new ResponseEntity<>(asignaturas, HttpStatus.OK);
    }

    /**
     * Método para obtener una asignatura por su ID.
     * @param id ID de la asignatura.
     * @return ResponseEntity con la asignatura y el código de estado HTTP correspondiente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable("id") Long id) {
        Optional<Asignatura> asignaturaOptional = asignaturaService.findAsignaturaById(id);
        return asignaturaOptional.map(asignatura -> new ResponseEntity<>(asignatura, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Método para agregar una nueva asignatura.
     * @param asignaturaDTO DTO de la nueva asignatura.
     * @return ResponseEntity con la asignatura creada y el código de estado HTTP correspondiente.
     */
    @PostMapping("/")
    public ResponseEntity<Asignatura> addAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(asignaturaDTO.getNombre());
        asignatura.setTipo(asignaturaDTO.getTipo());
        asignatura.setCreditos(asignaturaDTO.getCreditos());

        Asignatura nuevaAsignatura = asignaturaService.insertarAsignatura(asignatura);
        return new ResponseEntity<>(nuevaAsignatura, HttpStatus.CREATED);
    }

    /**
     * Método para actualizar una asignatura existente.
     * @param id ID de la asignatura a actualizar.
     * @param asignaturaDTO DTO con los nuevos datos de la asignatura.
     * @return ResponseEntity con la asignatura actualizada y el código de estado HTTP correspondiente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable("id") Long id, @RequestBody AsignaturaDTO asignaturaDTO) {
        Optional<Asignatura> asignaturaOptional = asignaturaService.findAsignaturaById(id);
        if (asignaturaOptional.isPresent()) {
            Asignatura asignatura = asignaturaOptional.get();
            asignatura.setNombre(asignaturaDTO.getNombre());
            asignatura.setTipo(asignaturaDTO.getTipo());
            asignatura.setCreditos(asignaturaDTO.getCreditos());

            Asignatura asignaturaActualizada = asignaturaService.actualizarAsignatura(asignatura);
            return new ResponseEntity<>(asignaturaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
