package com.creta.filmes.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.creta.filmes.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private LocalDate dataNascimento;
	private TipoUsuario tipoUsuario;
	private boolean isAtivo;
	
	public Usuario(String nome, LocalDate dataNascimento, TipoUsuario tipoUsuario, boolean isAtivo) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.tipoUsuario = tipoUsuario;
		this.isAtivo = true;
	}
}
