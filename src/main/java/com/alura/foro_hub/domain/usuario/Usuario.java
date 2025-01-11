package com.alura.foro_hub.domain.usuario;

import com.alura.foro_hub.domain.topico.dto.DatosActualizarTopico;

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
public class Usuario {
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

}
