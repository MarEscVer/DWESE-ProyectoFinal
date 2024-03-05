package com.example.ApiFinal.models.request;

import lombok.Data;

/**
 * Clase que representa la solicitud de registro de usuario.
 */
@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
