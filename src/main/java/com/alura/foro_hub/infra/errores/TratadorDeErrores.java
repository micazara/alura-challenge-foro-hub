package com.alura.foro_hub.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.status(404).body("El recurso solicitado no fue encontrado");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity tratarTipoDeDatoIncorrecto(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(400).body("Se ingresó un tipo de dato incorrecto. Debe ser del tipo " + ex.getRequiredType().getSimpleName());
    }
}
