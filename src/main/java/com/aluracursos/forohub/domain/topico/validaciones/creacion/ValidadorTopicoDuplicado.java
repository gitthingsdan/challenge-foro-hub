package com.aluracursos.forohub.domain.topico.validaciones.creacion;

import com.aluracursos.forohub.domain.ConflictoException;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.topico.DatosCreacionTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoDuplicado implements ValidadorCreacionTopicos {
	@Autowired
	private TopicoRepository repository;

	@Override
	public void validar(DatosCreacionTopico datos) {
		boolean topicoDuplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
		if (topicoDuplicado) {
			throw new ConflictoException("No se puede crear otro tópico con el mismo título y mensaje.");
		}
	}
}
