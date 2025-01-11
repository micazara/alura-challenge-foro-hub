package com.alura.foro_hub.domain.topico.dto;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.usuario.Usuario;

import jakarta.validation.constraints.*;

public record DatosActualizarTopico(
    @NotBlank
    String titulo, 
    @NotBlank
    String mensaje, 
    @NotNull
    Usuario usuario, 
    @NotNull
    Curso curso) {

}
