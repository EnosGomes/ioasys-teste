package com.creta.filmes.controller;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.entity.Usuario;
import com.creta.filmes.service.FilmeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/filmes")
@Api("Filmes API")
public class FilmeController {

	private final ModelMapper modelMapper;

	public FilmeController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	FilmeService filmeService;

	@GetMapping
	@ApiOperation("Retorna todos os filmes")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de filmes"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(filmeService.getAllFilmes());
	}
	
	@ApiOperation("Retorna os filmes de forma paginada")
	@GetMapping("/paginada")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de filmes paginados"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<Map<String, Object>> getAllFilmesPaginados(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		
		Pageable paging = PageRequest.of(page, size);
		
		Map<String, Object> buscaPaginada = filmeService.getFilmesPaginados(paging);

		return new ResponseEntity<>(buscaPaginada, HttpStatus.OK);
	}
	
	@ApiOperation("Filtra os filmes por diretor")
	@GetMapping("/filtroByDiretor")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de filmes filtrada por diretor"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> getFilmePorDiretor(@RequestParam("diretores") Set<String> diretores) {
		return ResponseEntity.ok(filmeService.getFilmePorDiretor(diretores));
	}
	
	@ApiOperation("Filtra os filmes por Gênero")
	@GetMapping("/filtroByGenero")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de filmes filtrada por gênero"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> getFilmePorGenero(@RequestParam("genero") int codGenero) {
		return ResponseEntity.ok(filmeService.getFilmePorGenero(codGenero));
	}
	
	@ApiOperation("Filtra os filmes por nome")
	@GetMapping("/filtroByNome")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de filmes filtrada por nome"),
		    @ApiResponse(code = 404, message = "Nenhum filme encontrado"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> getFilmePorNome(@RequestParam("nome") String nome) {
		return ResponseEntity.ok(filmeService.getFilmePorNome(nome));
	}
	
	/*
	@GetMapping("/filtroByAtor")
	public ResponseEntity<?> getFilmePorAtor(@RequestParam("ator") String ator) {
		return ResponseEntity.ok(filmeService.getFilmePorAtor(ator));
	} */

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	@ApiOperation("Salva um novo filme")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Filme foi salvo com sucesso"),
		    @ApiResponse(code = 400, message = "Filme com campos incorretos"),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção")
		})
	public ResponseEntity<?> saveFilme(@Valid @RequestBody Usuario usuario, Filme filme) {
		Filme filmeSalvo = filmeService.saveFilme(usuario,filme);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(filmeSalvo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
