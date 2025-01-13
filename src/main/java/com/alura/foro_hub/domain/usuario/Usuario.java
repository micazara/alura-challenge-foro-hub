package com.alura.foro_hub.domain.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Usuario")
@Table(name = "Usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo_electronico;
    private String contrasena;

    public Usuario actualizar(Usuario datos) {
        if (datos.nombre != null) {
            this.nombre = datos.nombre;
        }
        if (datos.correo_electronico != null) {
            this.correo_electronico = datos.correo_electronico;
        }
        if (datos.contrasena != null) {
            this.contrasena = datos.contrasena;
        }
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve los roles o permisos del usuario
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // Devuelve la contraseña del usuario
        return contrasena;
    }

    @Override
    public String getUsername() {
        // Devuelve el nombre de usuario
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Devuelve true si la cuenta no ha expirado
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Devuelve true si la cuenta no está bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
