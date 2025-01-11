package com.alura.foro_hub.domain.topico;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.curso.CursoRepository;
import com.alura.foro_hub.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import com.alura.foro_hub.infra.errores.TopicoYaExistente;

import jakarta.persistence.EntityNotFoundException;
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
        Usuario usuario = seleccionarUsuario(datos.autor());
        Curso curso = seleccionarCurso(datos.curso());
        verificarTopicosDuplicados(datos.titulo(), datos.mensaje());
        var topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                LocalDate.now(),
                1,
                usuario,
                curso);
        return topicoRepository.save(topico);
    }

    @Transactional
    public Topico actualizar(Long id, DatosActualizarTopico datos) {
        // Buscar el tópico por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));

        // Verificar que el título y el mensaje no sean duplicados
        verificarTopicosDuplicados(datos.titulo(), datos.mensaje());

        // Actualizar los datos del tópico
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());

        // Actualizar el curso asociado
        Curso curso = seleccionarCurso(datos.curso());
        topico.setCurso(curso);

        // Actualizar el autor (usuario) asociado
        Usuario usuario = seleccionarUsuario(datos.usuario());
        topico.setAutor(usuario);

        // Guardar y devolver el tópico actualizado
        return topicoRepository.save(topico);
    }

    private Curso seleccionarCurso(Curso curso) {
        Optional<Curso> encontrado = cursoRepository.findById(curso.getId());
        if (encontrado.isPresent()) {
            return encontrado.get();
        }
        throw new EntityNotFoundException();
    }

    private Usuario seleccionarUsuario(Usuario usuario) {
        Optional<Usuario> encontrado = usuarioRepository.findById(usuario.getId());
        if (encontrado.isPresent()) {
            return encontrado.get();
        }
        throw new EntityNotFoundException();
    }

    public void verificarTopicosDuplicados(String titulo, String mensaje) {
        if (topicoRepository.existsByTituloAndMensaje(titulo, mensaje)) {
            throw new TopicoYaExistente("Ya existe un topico con el titulo y el mensaje ingresados");
        }
    }

}
