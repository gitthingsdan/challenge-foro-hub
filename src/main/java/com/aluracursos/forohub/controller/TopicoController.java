package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	@Autowired
	private GestorTopicos gestorTopicos;
	@Autowired
	private TopicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DatosDetalleTopico> crear(@RequestBody @Valid DatosCreacionTopico datos, UriComponentsBuilder uriComponentsBuilder) {
		DatosDetalleTopico detalleTopico = gestorTopicos.crear(datos);
		URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(detalleTopico.id()).toUri();

		return ResponseEntity.created(uri).body(detalleTopico);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DatosDetalleTopico> detallar(@PathVariable Long id) {
		DatosDetalleTopico detalleTopico = gestorTopicos.detallar(id);
		return ResponseEntity.ok(detalleTopico);
	}

	@GetMapping
	public ResponseEntity<List<DatosListaTopico>> listar() {
		List<DatosListaTopico> listaTopicos = gestorTopicos.listar();
		return ResponseEntity.ok(listaTopicos);
	}

	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosDetalleTopico> actualizarParcialmente(@RequestBody DatosActualizacionTopico datos, @PathVariable Long id) {
		DatosDetalleTopico detalleTopico = gestorTopicos.actualizar(datos, id);
		return ResponseEntity.ok(detalleTopico);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DatosDetalleTopico> actualizarCompletamente(@RequestBody @Valid DatosActualizacionTopico datos, @PathVariable Long id) {
		DatosDetalleTopico detalleTopico = gestorTopicos.actualizar(datos, id);
		return ResponseEntity.ok(detalleTopico);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		gestorTopicos.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
