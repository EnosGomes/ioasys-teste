package com.creta.filmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creta.filmes.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	//@Modifying
	@Query(value = "update Usuario u set u.isAtivo = 0 where u.id = ?", 
	  nativeQuery = true)
	public void desativaUsuario(Long idUsuario);  
	
	public List<Usuario> findAllByOrderByNomeAsc();
		
	public List<Usuario> findByIsAtivoFalseOrderByNomeAsc();
}
