package com.example.ApiFinal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
     * Método para manejar la solicitud GET a la ruta "/asignaturas/".
     * Muestra todas las asignaturas disponibles.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
	@GetMapping("/")
	public String asignaturas(Model model) {
		model.addAttribute("asignaturas", asignaturaService.getAllAsignaturas());
		
		return "asignaturas";
	}
	
	/**
     * Método para manejar la solicitud GET a la ruta "/asignaturas/alumnos".
     * Muestra los alumnos matriculados en una asignatura.
     * @param codigo ID de la asignatura.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
	@GetMapping("/alumnos")
	public String asignaturasAlumnos(@RequestParam(required=false, name="codigo") String codigo, Model model) {
		if(codigo==null) {
			return "redirect:/asignaturas/";
		}
		
		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaById(Long.parseLong(codigo));
		
		model.addAttribute("asignatura", asignatura.get());
		
		return "asignaturasAlumnos";
	}
	
	/**
     * Método para manejar la solicitud GET a la ruta "/asignaturas/add".
     * Muestra el formulario para agregar una nueva asignatura.
     * @param error Parámetro para indicar un error.
     * @param nombre Nombre de la asignatura (en caso de error).
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
	@GetMapping("/add")
	public String addAsignaturaGet(@RequestParam(required=false,name="error") String error,
			@RequestParam(required=false,name="asig") String nombre,
			Model model) {
		
		AsignaturaDTO asig = new AsignaturaDTO();
		
		model.addAttribute("asig", asig);
		model.addAttribute("error", error);
		return "addAsignatura";
	}
	
	/**
     * Método para manejar la solicitud POST a la ruta "/asignaturas/add".
     * Inserta una nueva asignatura en la base de datos.
     * @param asig Objeto AsignaturaDTO con los datos de la nueva asignatura.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
	@PostMapping("/add")
	public String addAsignaturaPost(@ModelAttribute AsignaturaDTO asig,Model model) {
		
		Asignatura asigBD = new Asignatura();
		asigBD.setNombre(asig.getNombre());
		asigBD.setTipo(asig.getTipo());
		asigBD.setCreditos(asig.getCreditos());
		
		if (asignaturaService.insertarAsignatura(asigBD)==null) {
			return "redirect:/asignaturas/add?error=Existe&asig="+asig.getNombre();
		}
		
		return "redirect:/asignaturas/";
	}
	
	 /**
     * Método para manejar la solicitud GET a la ruta "/asignaturas/edit".
     * Muestra el formulario de edición de una asignatura.
     * @param asig ID de la asignatura.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
	@GetMapping("/edit")
	public String editAsig(@RequestParam(name="asig") String asig,Model model) {
		Optional<Asignatura> asignatura = asignaturaService.findAsignaturaById(Long.parseLong(asig));
		model.addAttribute("asignatura",asignatura.get());
		return "editAsignatura";
	}
	
	/**
     * Método para manejar la solicitud POST a la ruta "/asignaturas/edit".
     * Actualiza los datos de una asignatura.
     * @param asig Objeto Asignatura con los datos actualizados.
     * @return Nombre de la vista a la que se redirige.
     */
	@PostMapping("/edit")
	public String updateAsig(@ModelAttribute Asignatura asig) {
		
		if (asignaturaService.actualizarAsignatura(asig)==null) {
			return "redirect:/asignaturas/edit?error=error&asig"+asig.getId();
		}
		return "redirect:/asignaturas/";
	}
	
	/**
     * Método para manejar la solicitud GET a la ruta "/asignaturas/alumnos/delete".
     * Elimina un alumno de una asignatura.
     * @param asig ID de la asignatura.
     * @param alumn ID del alumno.
     * @return Nombre de la vista a la que se redirige.
     */
	@DeleteMapping("/alumnos/delete")
	public String asignaturaAlumnoDelete(@RequestParam(required=true, name="asig") String asig,
			@RequestParam(required=true, name="alumn") String alumn){
		
		Asignatura asignatura = asignaturaService.findAsignaturaById(Long.parseLong(asig)).get();
		if(asignatura !=null) {
			Alumno alumno = alumnoService.findAlumnoById(Long.parseLong(alumn)).get();				
			//asignatura.removeNota(alumno);
			asignaturaService.actualizarAsignatura(asignatura);
			return "redirect:/asignaturas/alumnos?codigo="+asig;
		}else {
			return "redirect:/asignaturas/";
		}			
	}
}
