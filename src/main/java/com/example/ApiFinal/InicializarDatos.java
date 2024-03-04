package com.example.ApiFinal;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.ApiFinal.models.alumno.Alumno;
import com.example.ApiFinal.models.alumno.AlumnoDTO;
import com.example.ApiFinal.models.usuario.Role;
import com.example.ApiFinal.models.usuario.Usuario;
import com.example.ApiFinal.repository.AlumnoRepository;
import com.example.ApiFinal.repository.UsuarioRepository;

@Component
public class InicializarDatos implements CommandLineRunner {

	@Autowired
	UsuarioRepository repositorio;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    AlumnoRepository alumnoRepository;

	@Override
	public void run(String... args) throws Exception {
		try {
			Usuario normalUser = new Usuario();
			normalUser.setFirstName("NombreUsuarioNormal");
			normalUser.setLastName("ApellidosUsuarioNormal");
			normalUser.setEmail("usuario@normal.com");
			normalUser.setPassword(passwordEncoder.encode("1234"));
			normalUser.getRoles().add(Role.ROLE_USER);

			repositorio.save(normalUser);

			// Crear usuario administrador
			Usuario adminUser = new Usuario();
			adminUser.setFirstName("NombreUsuarioAdmin");
			adminUser.setLastName("ApellidosUsuarioAdmin");
			adminUser.setEmail("usuario@admin.com");
			adminUser.setPassword(passwordEncoder.encode("admin"));
			adminUser.getRoles().add(Role.ROLE_ADMIN);

			repositorio.save(adminUser);
			
			// Crear alumnos
            AlumnoDTO alumnoDTO1 = new AlumnoDTO();
            alumnoDTO1.setNif("12345678A");
            alumnoDTO1.setNombre("Nombre1");
            alumnoDTO1.setApellido1("Apellido11");
            alumnoDTO1.setApellido2("Apellido21");
            alumnoDTO1.setCiudad("Ciudad1");
            alumnoDTO1.setDireccion("Direccion1");
            alumnoDTO1.setTelefono("123456789");
            alumnoDTO1.setFechaNacimiento(new Date(System.currentTimeMillis()));
            alumnoDTO1.setSexo("M");

            Alumno alumno1 = new Alumno(alumnoDTO1);

            AlumnoDTO alumnoDTO2 = new AlumnoDTO();
            alumnoDTO2.setNif("87654321B");
            alumnoDTO2.setNombre("Nombre2");
            alumnoDTO2.setApellido1("Apellido12");
            alumnoDTO2.setApellido2("Apellido22");
            alumnoDTO2.setCiudad("Ciudad2");
            alumnoDTO2.setDireccion("Direccion2");
            alumnoDTO2.setTelefono("987654321");
            alumnoDTO2.setFechaNacimiento(new Date(System.currentTimeMillis()));
            alumnoDTO2.setSexo("F");

            Alumno alumno2 = new Alumno(alumnoDTO2);

            // Guardar alumnos en el repositorio
            alumnoRepository.save(alumno1);
            alumnoRepository.save(alumno2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
