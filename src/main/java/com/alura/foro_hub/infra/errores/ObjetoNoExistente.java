package com.alura.foro_hub.infra.errores;

public class ObjetoNoExistente extends RuntimeException {
    public ObjetoNoExistente(String mensaje) {
        super(mensaje);
    }
}
