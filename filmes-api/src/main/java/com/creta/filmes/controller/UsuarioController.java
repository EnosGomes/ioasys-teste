package com.creta.filmes.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.creta.filmes.entity.Usuario;
import com.creta.filmes.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	private final ModelMapper modelMapper;

	public UsuarioController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(usuarioService.getAllUsuarios());
	}
	
	@GetMapping("/orderByName")
	public ResponseEntity<?> getAllUsuarioPorNome() {
		return ResponseEntity.ok(usuarioService.getAllUsuariosPorOrdem());
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> salvarUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.saveUsuario(usuario);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuarioSalvo.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}	

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizaUsuario(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Usuario userDetails) {
		
		usuarioService.atualizarUsuario(userDetails, id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/desativa/{id}")
	public ResponseEntity<Void> desativaUsuario(@PathVariable(value = "id") Long id) {
		
		usuarioService.desativaUsuario(id);
		
		return ResponseEntity.noContent().build();
	}

}
