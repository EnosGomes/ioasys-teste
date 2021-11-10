package com.creta.filmes.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/usuarios")
@Api("Usuarios API")
public class UsuarioController {

	private final ModelMapper modelMapper;

	public UsuarioController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	@ApiOperation("Retorna todos os usuários")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Retorna uma lista de todos os usuários cadastrados"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Nenhum usuário cadastrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(usuarioService.getAllUsuarios());
	}
	
	@ApiOperation("Filtra os usuários por nome")
	@GetMapping("/orderByName")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna uma lista de filmes filtrados por nome"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Nenhum usuário encontrado"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> getAllUsuarioPorNome() {
		return ResponseEntity.ok(usuarioService.getAllUsuariosPorOrdem());
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@ApiOperation("Salva um novo usuário")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Usuário criado com sucesso"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
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
	@ApiOperation("Atualiza um usuário pelo seu id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna um filme filtrado pelo nome"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Nenhum usuário encontrado com esse id"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<Void> atualizaUsuario(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Usuario userDetails) {
		
		usuarioService.atualizarUsuario(userDetails, id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/desativa/{id}")
	@ApiOperation("Desativa um usuário pelo seu id")
	@ApiResponses(value = {
		    @ApiResponse(code = 204, message = "Usuário desativado com sucesso"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Nenhum usuário encontrado com esse id para desativação"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<Void> desativaUsuario(@PathVariable(value = "id") Long id) {		
		usuarioService.desativaUsuario(id);		
		
		return ResponseEntity.noContent().build();
	}

}
