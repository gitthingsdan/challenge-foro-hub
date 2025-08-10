package com.aluracursos.forohub.infra.exceptions;

import com.aluracursos.forohub.domain.ConflictoException;
import com.aluracursos.forohub.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GestorErrores {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> gestionarError404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DatosErrorValidacion>> gestionarError400(MethodArgumentNotValidException ex) {
		List<FieldError> errores = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
	}

	@ExceptionHandler(ConflictoException.class)
	public ResponseEntity<String> gestionarErrorDeConflicto(ConflictoException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

	@ExceptionHandler(ValidacionException.class)
	public ResponseEntity<String> gestionarErrorDeValidacion(ValidacionException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> gestionarErrorDeLecturaJSON() {
		return ResponseEntity.badRequest().body("Por favor, verifique el formateo correcto de su petición JSON. Si ingresó el campo \"status\", asegúrese también de que su valor corresponda a 'SIN_RESPUESTA','RESUELTO' o 'SPAM'.");
	}

	public record DatosErrorValidacion(String campo, String mensaje) {
		public DatosErrorValidacion(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}
