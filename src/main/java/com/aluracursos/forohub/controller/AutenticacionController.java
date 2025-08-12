package com.aluracursos.forohub.controller;

import jakarta.validation.Valid;
import com.aluracursos.forohub.domain.usuario.DatosAutenticacion;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.infra.security.DatosTokenJWT;
import com.aluracursos.forohub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
	@Autowired
	private TokenService tokenService;

	@Autowired
	private AuthenticationManager manager;

	@PostMapping
	public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
		Authentication autenticacion = manager.authenticate(authenticationToken);

		String tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

		return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
	}
}
