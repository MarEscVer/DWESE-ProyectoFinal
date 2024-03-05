package com.example.ApiFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiFinal.models.jwt.JwtAuthenticationResponse;
import com.example.ApiFinal.models.request.SignInRequest;
import com.example.ApiFinal.models.request.SignUpRequest;
import com.example.ApiFinal.service.AuthenticationService;

import lombok.RequiredArgsConstructor;


/**
 * Controlador para manejar las solicitudes de autenticación.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;

	/**
     * Maneja la solicitud de registro de un nuevo usuario.
     *
     * @param request La solicitud de registro de un nuevo usuario.
     * @return La respuesta de autenticación JWT.
     */
	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	/**
     * Maneja la solicitud de inicio de sesión de un usuario existente.
     *
     * @param request La solicitud de inicio de sesión de un usuario existente.
     * @return La respuesta de autenticación JWT.
     */
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}

}
