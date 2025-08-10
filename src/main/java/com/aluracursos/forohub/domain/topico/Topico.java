package com.aluracursos.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaDeCreacion;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String autor;
	private String curso;

	public Topico(DatosCreacionTopico datos) {
		this.id = null;
		this.titulo = datos.titulo();
		this.mensaje = datos.mensaje();
		this.fechaDeCreacion = LocalDateTime.now();
		this.status = Status.SIN_RESPUESTA;
		this.autor = datos.autor();
		this.curso = datos.curso();
	}

	public void actualizar(DatosActualizacionTopico datos) {
		if (datos.titulo() != null) this.titulo = datos.titulo();
		if (datos.mensaje() != null) this.mensaje = datos.mensaje();
		if (datos.status() != null) this.status = datos.status();
		if (datos.autor() != null) this.autor = datos.autor();
		if (datos.curso() != null) this.curso = datos.curso();
	}
}
