package com.example.ApiFinal.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ApiFinal.models.jwt.JwtAuthenticationResponse;
import com.example.ApiFinal.models.request.SignInRequest;
import com.example.ApiFinal.models.request.SignUpRequest;
import com.example.ApiFinal.models.usuario.Role;
import com.example.ApiFinal.models.usuario.Usuario;
import com.example.ApiFinal.repository.UsuarioRepository;
import com.example.ApiFinal.service.AuthenticationService;
import com.example.ApiFinal.service.JwtService;

import lombok.Builder;

/**
 * Implementación del servicio de autenticación.
 */
@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     *  Constructor para inyección de dependencias (si usas Spring)
     * @param usuarioRepository
     * @param passwordEncoder
     * @param jwtService
     * @param authenticationManager
     */
    public AuthenticationServiceImpl(UsuarioRepository usuarioRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param request La solicitud de registro.
     * @return Una respuesta de autenticación JWT.
     */
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if(usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        // Corrige la forma de construir el objeto 'User'
        Usuario user = new Usuario();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER); // Asegúrate de que Role.USER esté definido correctamente
        usuarioRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /**
     * Autentica un usuario existente en el sistema.
     * @param request La solicitud de inicio de sesión.
     * @return Una respuesta de autenticación JWT.
     */
    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        // Maneja la autenticación
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
