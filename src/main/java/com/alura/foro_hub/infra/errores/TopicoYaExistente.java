package com.alura.foro_hub.infra.errores;

public class TopicoYaExistente extends RuntimeException {
    public TopicoYaExistente(String mensaje) {
        super(mensaje);
    }

}
