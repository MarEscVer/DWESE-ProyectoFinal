package com.example.ApiFinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.ApiFinal.models.usuario.JPAUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

	@Autowired
	JPAUserDetailsService userDetailsService;

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/login").permitAll()
				.requestMatchers("/asignaturas/*").hasRole("ADMIN").requestMatchers("/about", "/alumnos/*", "/services")
				.hasRole("USER").requestMatchers("/register").permitAll().anyRequest().authenticated());
		return http.build();
	}

    @Bean
    public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(15);
	}
}
