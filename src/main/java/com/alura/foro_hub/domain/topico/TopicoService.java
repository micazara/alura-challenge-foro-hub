package com.alura.foro_hub.domain.topico;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.curso.CursoRepository;
import com.alura.foro_hub.domain.topico.dto.DatosRespuestaTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import com.alura.foro_hub.infra.errores.ObjetoNoExistente;
import com.alura.foro_hub.infra.errores.TopicoYaExistente;

import jakarta.transaction.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Topico registrar(DatosRegistroTopico datos) {
        verificarTopicosDuplicados(datos.titulo(), datos.mensaje());
        var topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                LocalDate.now(),
                1,
                seleccionarUsuario(datos.autor()),
                seleccionarCurso(datos.curso()));
        return topicoRepository.save(topico);
        // return new DatosDetalleTopico(topico);
    }

    private Curso seleccionarCurso(Long id) {
        if (!cursoRepository.findById(id).isPresent()) {
            throw new ObjetoNoExistente("No se encontró un curso con el id ingresado");
        }
        return cursoRepository.findById(id).get();
    }

    private Usuario seleccionarUsuario(Long id) {
        if (!usuarioRepository.findById(id).isPresent()) {
            throw new ObjetoNoExistente("No se encontró un usuario con el id ingresado");
        }
        return usuarioRepository.findById(id).get();
    }

    private void verificarTopicosDuplicados(String titulo, String mensaje) {
        if (topicoRepository.existsByTituloAndMensaje(titulo, mensaje)) {
            throw new TopicoYaExistente("Ya existe un topico con el titulo y el mensaje ingresados");
        }
    }

}
