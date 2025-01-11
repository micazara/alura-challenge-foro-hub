package com.alura.foro_hub.domain.topico.dto;

import java.time.LocalDate;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.topico.Topico;
import com.alura.foro_hub.domain.usuario.Usuario;

// este record se usa luego de que se crea un nuevo topico
public record DatosRespuestaTopico(String titulo, String mensaje, LocalDate fecha_creacion, int status,
        Usuario usuario, Curso curso) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }

}
