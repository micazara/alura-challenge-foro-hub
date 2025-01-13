package com.alura.foro_hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    UserDetails findByNombre(String username) throws UsernameNotFoundException;

}
