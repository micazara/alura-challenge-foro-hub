package com.alura.foro_hub.infra.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DatosInformacionError> tratarError404(EntityNotFoundException ex) {
        String mensaje = ex.getMessage() != null
                ? ex.getMessage()
                : "El recurso solicitado no existe.";
        return ResponseEntity.status(404).body(new DatosInformacionError(mensaje));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity tratarTipoDeDatoIncorrecto(MethodArgumentTypeMismatchException ex) {
        String tipoDeDato = ex.getRequiredType().getSimpleName();
        return ResponseEntity.badRequest()
                .body(new DatosInformacionError("Se ingresó un tipo de dato incorrecto. Debe ser " + tipoDeDato));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(TopicoYaExistente.class)
    public ResponseEntity tratarErrorTopicoYaExistente(TopicoYaExistente ex) {
        return ResponseEntity.badRequest().body(new DatosInformacionError(ex.getMessage()));
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record DatosInformacionError(String mensaje) {

    }
}
