package com.example.ApiFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.alumno.AlumnoDTO;
import com.example.ApiFinal.models.asignatura.Asignatura;
import com.example.ApiFinal.service.impl.AlumnoServiceImpl;
import com.example.ApiFinal.service.impl.AsignaturaServiceImpl;


@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

		@Autowired
		AlumnoServiceImpl alumnoService;
		
		@Autowired
		AsignaturaServiceImpl asignaturaService;
		
		@GetMapping("/")
		public String alumnos(Model model) {
			List<Alumno> alumnos = alumnoService.getAllAlumnos();
			System.out.println(alumnos);
			model.addAttribute("alumnos", alumnos);
			
			return "alumnos";
		}
		

		@GetMapping("/asignaturas")
		public String alumnosMatricula(@RequestParam(required = false, name = "codigo") String codigo, 
				@RequestParam(required = false, name = "exito") String exito,
				Model model) {

			// Obtengo el parámetro de la URL (en caso de no esta vuelvo a la lista de
			// asignaturas
			if (codigo == null) {
				return "redirect:/alumnos/";
			}
			Optional<Alumno> alumno = alumnoService.findAlumnoById(Long.parseLong(codigo));
			model.addAttribute("alumno",alumno.get());
			model.addAttribute("exito", exito);
			return "alumnosAsignaturas";
		}
		
		@GetMapping("/edit")
		public String editAlumn(@RequestParam(name="alumn", required=true) String alumn,
				@RequestParam(name="error", required=false) String error,
				Model model) {
			
			Optional<Alumno> alumno = alumnoService.findAlumnoById(Long.parseLong(alumn));
			System.out.println(alumno.get().getAlumnoAsignaturas());
			model.addAttribute("alumno",alumno.get());	
			if(error!=null) {
				model.addAttribute("error", error);
			}
			return "editAlumno";
		}
		
		
		@PostMapping("/edit")
		public String updateAlumn(@ModelAttribute Alumno alumno,Model model) {
			System.out.println("Id del alumno: "+alumno.getId());
			System.out.println("Asignaturas del alumno: "+alumno.getAlumnoAsignaturas().toString());
			if (alumnoService.actualizarAlumno(alumno)==null) {
				return "redirect:/alumnos/edit?error=error&alumn"+alumno.getId();
			}
			return "redirect:/alumnos/";
		}
		
		@GetMapping("/add")
		public String alumnosInsertarGet(@RequestParam(required=false,name="error") String error,
				@RequestParam(required=false,name="alumno") String alumno,
				Model model) {
			
			AlumnoDTO alumnoDTO = new AlumnoDTO();
			model.addAttribute("alumno", alumnoDTO);
			model.addAttribute("error", error);
			return "addAlumno";
		}
		
		@PostMapping("/add")
		public String alumnosInsertarPost(@ModelAttribute AlumnoDTO alumnoDTO,Model model) {
			if(alumnoService.findAlumnoByNif(alumnoDTO.getNif())==null) {
				Alumno alumn = new Alumno(alumnoDTO);		
				System.out.println("pasa por aqui");
				if (alumnoService.insertarAlumno(alumn)==null) {
					return "redirect:/alumnos/add?error=Existe&alumno="+alumnoDTO.getNombre();
				}else {
					return "redirect:/alumnos/";					
				}
			}else {
				return "redirect:/alumnos/add?error=ExisteNif&alumno="+alumnoDTO.getNif();
			}
		}
		
		@GetMapping("/asignaturas/delete")
		public String asignaturaAlumnoDelete(@RequestParam(required=true, name="asig") String asig,
				@RequestParam(required=true, name="alumn") String alumn){
			
			Asignatura asignatura = asignaturaService.findAsignaturaById(Long.parseLong(asig)).get();
			if(asignatura !=null) {
				Alumno alumno = alumnoService.findAlumnoById(Long.parseLong(alumn)).get();				
				//asignatura.removeNota(alumno);
				asignaturaService.actualizarAsignatura(asignatura);
				return "redirect:/alumnos/asignaturas?codigo="+alumn+"&exito=delAsigAlum";
			}else {
				return "redirect:/alumnos/";
			}			
		}
}
