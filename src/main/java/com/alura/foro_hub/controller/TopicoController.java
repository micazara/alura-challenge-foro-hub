package com.alura.foro_hub.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;

import com.alura.foro_hub.domain.curso.Curso;
import com.alura.foro_hub.domain.curso.CursoRepository;
import com.alura.foro_hub.domain.topico.Topico;
import com.alura.foro_hub.domain.topico.TopicoRepository;
import com.alura.foro_hub.domain.topico.TopicoService;
import com.alura.foro_hub.domain.topico.dto.DatosRespuestaTopico;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import com.alura.foro_hub.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro_hub.domain.topico.dto.DatosRegistroTopico;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> mostrarTodos(@PageableDefault(size = 8) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarUnTopico(@PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {
        // Optional<Topico> optionalTopico = topicoRepository.findById(id);
        // if (optionalTopico.isEmpty()) {
        //     throw new EntityNotFoundException();
        // }

        // Topico topico = optionalTopico.get();
        // topicoService.verificarTopicosDuplicados(datos.titulo(), datos.mensaje());
        // topico.setTitulo(datos.titulo());
        // topico.setMensaje(datos.mensaje());

        // Curso curso = cursoRepository.findById(datos.curso().getId())
        //         .orElseThrow(() -> new EntityNotFoundException());
        // topico.setCurso(curso);

        // Usuario usuario = usuarioRepository.findById(datos.usuario().getId())
        //         .orElseThrow(() -> new EntityNotFoundException());
        // topico.setAutor(usuario);

        Topico topicoActualizado = topicoService.actualizar(id, datos);

        return ResponseEntity.ok(new DatosRespuestaTopico(topicoActualizado));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

}
