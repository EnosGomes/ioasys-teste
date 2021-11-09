package com.creta.filmes.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.repository.FilmeRepository;
import com.creta.filmes.service.FilmeService;

@Service
public class FilmeServiceImpl implements FilmeService {

	@Autowired
	FilmeRepository filmeRepository;

	@Override
	public List<Filme> getAllFilmes() {
		return filmeRepository.findAll();
	}

	@Override
	public Filme saveFilme(Filme filme) {
		return filmeRepository.save(filme);
	}

	@Override
	public List<Filme> getFilmePorAtor(String ator) {
		return null;
	}

	@Override
	public List<Filme> getFilmePorDiretor(String diretores) {
		return null;
	}

	@Override
	public List<Filme> getFilmePorGenero(String genero) {
		return null;
	}

	@Override
	public List<Filme> getFilmePorNome(String nome) {
		return null;
	}

	@Override
	public void votarEmFilme(long quantidadeVotos, Long idFilme) {
		filmeRepository.inserirVotoEmFilme(quantidadeVotos, idFilme);
	}

	@Override
	public List<Filme> getFilmeMaisVotado() {
		return filmeRepository.findTopByOrderByQuantidadeVotosAsc();
	}

	@Override
	public Map<String, Object> getFilmesPaginados(Pageable pageable) {
		Pageable paging = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		List<Filme> buscaPaginadaResponse = new ArrayList<>();
		Page<Filme> buscaPaginada = filmeRepository.findAll(paging);
		buscaPaginadaResponse = buscaPaginada.getContent();

	      Map<String, Object> response = new HashMap<>();
	      response.put("tutorials", buscaPaginadaResponse);
	      response.put("currentPage", buscaPaginada.getNumber());
	      response.put("totalItems", buscaPaginada.getTotalElements());
	      response.put("totalPages", buscaPaginada.getTotalPages());	
		
		return response;
	}
}
