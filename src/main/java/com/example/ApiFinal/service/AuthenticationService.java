package com.example.ApiFinal.service;

import com.example.ApiFinal.models.jwt.JwtAuthenticationResponse;
import com.example.ApiFinal.models.request.SignInRequest;
import com.example.ApiFinal.models.request.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SignInRequest request);
}
