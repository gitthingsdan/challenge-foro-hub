package com.aluracursos.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	boolean existsByTituloAndMensaje(String titulo, String mensaje);

	boolean existsByTituloAndMensajeAndIdIsNot(String titulo, String mensaje, Long id);
}
