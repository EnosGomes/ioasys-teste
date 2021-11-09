package com.creta.filmes.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.enums.Genero;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

	List<Filme> findByDiretoresIn(Set<String> diretores); //recebe set de diretores na request
	List<Filme> findByGenero(Genero genero);
	List<Filme> findByNome(String nome);
	//List<Filme> findByAtores(Set ator);

	@Modifying
	@Query(value = "update Filme f set f.quantidadeVotos = ? where u.id = ?", 
	nativeQuery = true)
	public void inserirVotoEmFilme(long quantidadeVotos, long idFilme);	

	public List<Filme> findTopByOrderByQuantidadeVotosAsc();
}
