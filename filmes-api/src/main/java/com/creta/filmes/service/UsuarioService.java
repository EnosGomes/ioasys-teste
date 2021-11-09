package com.creta.filmes.service;

import java.util.List;

import com.creta.filmes.entity.Usuario;


public interface UsuarioService {

	public List<Usuario> getAllUsuarios();
	
	public Usuario saveUsuario(Usuario usuario);
	
	public Usuario atualizarUsuario(Usuario usuario, Long id);	
	
	public Usuario fromDTO(Usuario objDto);
	
	public void desativaUsuario(Long idUsuario);
	
	public List<Usuario> getAllUsuariosPorOrdem();

}
