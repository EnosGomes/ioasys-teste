package com.creta.filmes.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.entity.Usuario;

public interface FilmeService {
	
	public Filme saveFilme(Usuario usuario, Filme filme);		
	public List<Filme> getAllFilmes();	
	/*public List<Filme> getFilmePorAtor(String ator);*/
	public List<Filme> getFilmePorDiretor(Set<String> diretores);
	public List<Filme> getFilmePorGenero(int codigoGenero);
	public List<Filme> getFilmePorNome(String nome);
	public void votarEmFilme(Long id);
	public List<Filme> getFilmeMaisVotado() throws SQLException;
	public Map<String, Object> getFilmesPaginados(Pageable pageable);
}
