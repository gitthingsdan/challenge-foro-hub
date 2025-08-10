package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosCreacionTopico(
		@NotBlank String titulo,
		@NotBlank String mensaje,
		@NotBlank String autor,
		@NotBlank String curso
) {
}
