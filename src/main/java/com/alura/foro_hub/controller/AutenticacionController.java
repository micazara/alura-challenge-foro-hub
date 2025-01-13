package com.alura.foro_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alura.foro_hub.domain.usuario.DatosAuntenticacionUsuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity auntenticarUsuario(@RequestBody @Valid DatosAuntenticacionUsuario datos) {
        Authentication token = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.clave());
        this.authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }

}
