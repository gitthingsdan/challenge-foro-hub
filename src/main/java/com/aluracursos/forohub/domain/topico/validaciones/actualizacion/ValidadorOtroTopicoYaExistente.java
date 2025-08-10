package com.aluracursos.forohub.domain.topico.validaciones.actualizacion;

import com.aluracursos.forohub.domain.ConflictoException;
import com.aluracursos.forohub.domain.topico.DatosActualizacionTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorOtroTopicoYaExistente implements ValidadorActualizacionTopicos {
	@Autowired
	private TopicoRepository repository;

	@Override
	public void validar(DatosActualizacionTopico datos, Long id) {
		Topico topicoActual = repository.findById(id).orElseThrow(EntityNotFoundException::new);

		String tituloFinal = datos.titulo() != null ? datos.titulo() : topicoActual.getTitulo();
		String mensajeFinal = datos.mensaje() != null ? datos.mensaje() : topicoActual.getMensaje();

		boolean otroTopicoYaExistente = repository.existsByTituloAndMensajeAndIdIsNot(tituloFinal, mensajeFinal, id);

		if (otroTopicoYaExistente) {
			throw new ConflictoException("Ya existe otro tópico con el mismo título y mensaje.");
		}
	}
}
