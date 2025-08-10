package com.aluracursos.forohub.domain.topico.validaciones.actualizacion;

import com.aluracursos.forohub.domain.topico.DatosActualizacionTopico;

public interface ValidadorActualizacionTopicos {
	void validar(DatosActualizacionTopico datos, Long id);
}
