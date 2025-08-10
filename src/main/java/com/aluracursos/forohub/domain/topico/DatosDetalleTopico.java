package com.aluracursos.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
		Long id,
		String titulo,
		String mensaje,
		LocalDateTime fechaDeCreacion,
		Status status,
		String autor,
		String curso
) {
	public DatosDetalleTopico(Topico topico) {
		this(
				topico.getId(),
				topico.getTitulo(),
				topico.getMensaje(),
				topico.getFechaDeCreacion(),
				topico.getStatus(),
				topico.getAutor(),
				topico.getCurso()
		);
	}
}
