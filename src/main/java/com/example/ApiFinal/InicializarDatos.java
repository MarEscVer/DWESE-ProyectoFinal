package com.example.ApiFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ApiFinal.models.usuario.Usuario;
import com.example.ApiFinal.repository.UsuarioRepository;

@Component
public class InicializarDatos implements CommandLineRunner {

	@Autowired
	UsuarioRepository repositorio;
	
	@Override
	public void run(String... args) throws Exception {

		Usuario normalUser = new Usuario();
        normalUser.setActivo(true);
        normalUser.setApellidos("ApellidosUsuarioNormal");
        normalUser.setEmail("usuario@normal.com");
        normalUser.setNombre("UsuarioNormal");
        normalUser.setPassword("1234");
        normalUser.setRole("USER");
        normalUser.setUserName("usuario_normal");
        
        repositorio.save(normalUser);
        
        // Crear usuario administrador
        Usuario adminUser = new Usuario();
        adminUser.setActivo(true);
        adminUser.setApellidos("ApellidosUsuarioAdmin");
        adminUser.setEmail("usuario@admin.com");
        adminUser.setNombre("UsuarioAdmin");
        adminUser.setPassword("admin");
        adminUser.setRole("ADMIN");
        adminUser.setUserName("usuario_admin");

        repositorio.save(adminUser);
		
	}

}
