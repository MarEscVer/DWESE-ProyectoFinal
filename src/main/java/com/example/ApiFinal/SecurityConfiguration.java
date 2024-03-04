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


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{
	 @Autowired
	    JwtAuthenticationFilter jwtAuthenticationFilter;
	    @Autowired
	    UsuarioService usuarioService;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(request ->
	                        request
	                                .requestMatchers("/auth/**").permitAll()
	                                .requestMatchers(HttpMethod.GET, "/alumnos/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
	                                .requestMatchers(HttpMethod.POST, "/alumnos/**").hasAuthority(Role.ROLE_ADMIN.toString())
	                                .requestMatchers(HttpMethod.PUT, "/api/v1/stocks/**").hasAuthority(Role.ROLE_ADMIN.toString())
	                                .requestMatchers(HttpMethod.DELETE, "/api/v1/stocks/**").hasAuthority(Role.ROLE_ADMIN.toString())
	                                .requestMatchers("/api/v1/users/**").hasAuthority("ROLE_ADMIN")
	                                .anyRequest().authenticated())
	                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
	                .authenticationProvider(authenticationProvider()).addFilterBefore(
	                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(usuarioService.userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
	            throws Exception {
	        return config.getAuthenticationManager();
	    }
	}