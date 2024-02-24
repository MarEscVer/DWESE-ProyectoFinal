package com.example.ApiFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ApiFinal.models.usuario.Usuario;
import com.example.ApiFinal.service.UsuarioService;

/**
 * Controlador para gestionar las operaciones relacionadas con los usuarios.
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * Método para mostrar el formulario de registro de usuarios.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuario";
    }

    /**
     * Método para procesar el formulario de registro de usuarios.
     * @param usuario El usuario registrado.
     * @param model Modelo para pasar datos a la vista.
     * @return Nombre de la vista a la que se redirige.
     */
    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute Usuario usuario, Model model) {
        Usuario nuevoUsuario = usuarioService.insertUsuario(usuario);
        if (nuevoUsuario != null) {
            model.addAttribute("mensaje", "Usuario registrado con éxito.");
            return "registroExitoso";
        } else {
            model.addAttribute("error", "Error al registrar el usuario.");
            return "registroUsuario";
        }
    }
}
