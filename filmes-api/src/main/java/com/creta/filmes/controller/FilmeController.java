package com.creta.filmes.controller;

import java.net.URI;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.service.FilmeService;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmeController {

	private final ModelMapper modelMapper;

	public FilmeController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	FilmeService filmeService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(filmeService.getAllFilmes());
	}

	@GetMapping("/paginada")
	public ResponseEntity<Map<String, Object>> getAllFilmesPaginados(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		
		Pageable paging = PageRequest.of(page, size);
		
		Map<String, Object> buscaPaginada = filmeService.getFilmesPaginados(paging);

		return new ResponseEntity<>(buscaPaginada, HttpStatus.OK);
	}

	@GetMapping("/filtroByDiretor")
	public ResponseEntity<?> getFilmePorDiretor(@RequestParam("diretor") String diretor) {
		return ResponseEntity.ok(filmeService.getFilmePorDiretor(diretor));
	}

	@GetMapping("/filtroByGenero")
	public ResponseEntity<?> getFilmePorGenero(@RequestParam("genero") String genero) {
		return ResponseEntity.ok(filmeService.getFilmePorGenero(genero));
	}

	@GetMapping("/filtroByNome")
	public ResponseEntity<?> getFilmePorNome(@RequestParam("nome") String nome) {
		return ResponseEntity.ok(filmeService.getFilmePorNome(nome));
	}

	@GetMapping("/filtroByAtor")
	public ResponseEntity<?> getFilmePorAtor(@RequestParam("ator") String ator) {
		return ResponseEntity.ok(filmeService.getFilmePorAtor(ator));
	}

	// @PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> saveFilme(@Valid @RequestBody Filme filme) {
		Filme filmeSalvo = filmeService.saveFilme(filme);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(filmeSalvo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
