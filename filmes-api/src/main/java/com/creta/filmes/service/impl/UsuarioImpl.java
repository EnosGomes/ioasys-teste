package com.creta.filmes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creta.filmes.entity.Usuario;
import com.creta.filmes.enums.TipoUsuario;
import com.creta.filmes.repository.UsuarioRepository;
import com.creta.filmes.service.UsuarioService;

@Service
public class UsuarioImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void desativaUsuario(Long idUsuario) {
		Optional<Usuario> user=  usuarioRepository.findById(idUsuario);
		
		if(user.isPresent()) {
			usuarioRepository.desativaUsuario(idUsuario);
		} else {
			throw new RuntimeException("Usuário não encontrado!");
		}
	}
	
	@Override
	public Usuario fromDTO(Usuario objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getDataNascimento(), TipoUsuario.NORMAL, true);
	}

	@Override
	public Usuario atualizarUsuario(Usuario usuario, Long id) {
		Usuario user = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
		
		user.setNome(usuario.getNome());
		user.setDataNascimento(usuario.getDataNascimento());
		
		return usuarioRepository.save(user); 		
	}

	@Override
	public List<Usuario> getAllUsuariosPorOrdem() {
		return usuarioRepository.findAllByOrderByNomeAsc();
	}
}
