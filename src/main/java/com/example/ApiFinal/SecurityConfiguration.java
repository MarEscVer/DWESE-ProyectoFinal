package com.example.ApiFinal;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ApiFinal.models.usuario.Role;
import com.example.ApiFinal.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * Configuración de seguridad de la aplicación.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	UsuarioService usuarioService;

	/**
	 * Configura las reglas de seguridad HTTP.
	 *
	 * @param http La configuración de seguridad HTTP.
	 * @return El filtro de seguridad configurado.
	 * @throws Exception Si hay algún error durante la configuración de seguridad.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/alumnos/**", "/asignaturas/**")
						.hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
						.requestMatchers(HttpMethod.POST, "/alumnos/**", "/asignaturas/**")
						.hasAuthority(Role.ROLE_ADMIN.toString()).requestMatchers(HttpMethod.PUT, "/asignaturas/**")
						.hasAuthority(Role.ROLE_ADMIN.toString()).anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
     * Crea un codificador de contraseñas BCrypt.
     *
     * @return El codificador de contraseñas BCrypt.
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
     * Crea un proveedor de autenticación DAO.
     *
     * @return El proveedor de autenticación DAO configurado.
     */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usuarioService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
     * Obtiene el administrador de autenticación.
     *
     * @param config La configuración de autenticación.
     * @return El administrador de autenticación.
     * @throws Exception Si hay algún error durante la obtención del administrador de autenticación.
     */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}