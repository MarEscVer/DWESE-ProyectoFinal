package com.example.ApiFinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que indica que el recurso solicitado no fue encontrado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GlobalExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
     * Constructor de la excepción con un mensaje.
     *
     * @param message El mensaje que describe la excepción
     */
    public GlobalExceptionHandler(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción con un mensaje y una causa.
     *
     * @param message El mensaje que describe la excepción
     * @param cause   La causa de la excepción
     */
    public GlobalExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}