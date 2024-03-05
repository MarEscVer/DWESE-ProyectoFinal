package com.example.ApiFinal.models.request;

import lombok.Getter;

/**
 * Clase que representa la solicitud de inicio de sesión.
 */
@Getter
public class SignInRequest {
    private String email;
    private String password;

    /**
     * Establece el correo electrónico de la solicitud de inicio de sesión.
     * @param email El correo electrónico para establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Establece la contraseña de la solicitud de inicio de sesión.
     * @param password La contraseña para establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
