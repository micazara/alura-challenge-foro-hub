package com.alura.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findAll(Pageable paginacion);

}
