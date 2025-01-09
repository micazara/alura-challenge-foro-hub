package com.alura.foro_hub.domain.topico.dto;

import jakarta.validation.constraints.*;

public record DatosRegistroTopico(
    @NotBlank
    String titulo, 
    @NotBlank
    String mensaje, 
    @NotNull
    Long autor, 
    @NotNull
    Long curso) {

}
