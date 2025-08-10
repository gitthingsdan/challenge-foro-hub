package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.topico.validaciones.actualizacion.ValidadorActualizacionTopicos;
import com.aluracursos.forohub.domain.topico.validaciones.creacion.ValidadorCreacionTopicos;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorTopicos {
	@Autowired
	private TopicoRepository repository;

	@Autowired
	List<ValidadorCreacionTopicos> validadoresCreacionTopicos;

	@Autowired
	List<ValidadorActualizacionTopicos> validadoresActualizacionTopicos;

	public DatosDetalleTopico crear(DatosCreacionTopico datos) {
		validadoresCreacionTopicos.forEach(vct -> vct.validar(datos));
		Topico topico = new Topico(datos);
		repository.save(topico);

		return new DatosDetalleTopico(topico);
	}

	public DatosDetalleTopico detallar(Long id) {
		Topico topico = repository.getReferenceById(id);
		return new DatosDetalleTopico(topico);
	}

	public List<DatosListaTopico> listar() {
		List<DatosListaTopico> listaTopicos = repository.findAll().stream().map(DatosListaTopico::new).toList();
		return listaTopicos;
	}

	public DatosDetalleTopico actualizar(DatosActualizacionTopico datos, Long id) {
		validadoresActualizacionTopicos.forEach(vat -> vat.validar(datos, id));
		Topico topico = repository.getReferenceById(id);
		topico.actualizar(datos);

		return new DatosDetalleTopico(topico);
	}

	public void eliminar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFoundException();
		}
	}
}
