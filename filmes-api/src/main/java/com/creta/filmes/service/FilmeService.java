package com.creta.filmes.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.creta.filmes.entity.Filme;

public interface FilmeService {
	
	public Filme saveFilme(Filme filme);	
	
	public List<Filme> getAllFilmes();	
	public List<Filme> getFilmePorAtor(String ator);
	public List<Filme> getFilmePorDiretor(String diretores);
	public List<Filme> getFilmePorGenero(String genero);
	public List<Filme> getFilmePorNome(String nome);
	
	public void votarEmFilme(long quantidadeVotos, Long idFilme);
	public List<Filme> getFilmeMaisVotado();
	public Map<String, Object> getFilmesPaginados(Pageable pageable);
}
