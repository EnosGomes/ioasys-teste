package com.creta.filmes.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.entity.Usuario;
import com.creta.filmes.enums.Genero;
import com.creta.filmes.enums.TipoUsuario;
import com.creta.filmes.exception.AuthorizationException;
import com.creta.filmes.exception.BusinessException;
import com.creta.filmes.repository.FilmeRepository;
import com.creta.filmes.security.UserSS;
import com.creta.filmes.service.FilmeService;
import com.creta.filmes.service.UserService;

@Service
public class FilmeServiceImpl implements FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;
	
	public FilmeServiceImpl(FilmeRepository filmeRepository) {
		this.filmeRepository = filmeRepository;
	}

	@Override
	public List<Filme> getAllFilmes() {
		return filmeRepository.findAll();
	}

	@Override
	public Filme saveFilme(Usuario usuario, Filme filme) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(TipoUsuario.ADMIN) && !usuario.getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		return filmeRepository.save(filme);
	}
	
	/*
	@Override
	public List<Filme> getFilmePorAtor(String ator) {
		return null;
	} */

	@Override
	public List<Filme> getFilmePorDiretor(Set<String> diretores) {
		return filmeRepository.findByDiretoresIn(diretores);
	}

	@Override
	public List<Filme> getFilmePorGenero(int codigoGenero) {
		return filmeRepository.findByGenero(Genero.toEnum(codigoGenero));
	}

	@Override
	public List<Filme> getFilmePorNome(String nome) {
		return filmeRepository.findByNome(nome);
	}

	public void votarEmFilme(Long id) {
		Optional<Filme> filme = filmeRepository.findById(id);
		if(filme.isPresent()) {
			filme.get().adicionaVotaFilme();
			filmeRepository.save(filme.get());
		}else {
			throw new BusinessException("Não pode votar em um filme inexistente!");
		}
	}

	@Override
	public List<Filme> getFilmeMaisVotado() throws SQLException {
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
