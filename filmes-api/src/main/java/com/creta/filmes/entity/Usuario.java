package com.creta.filmes.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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
	private String email;
	private String senha;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Usuario(String nome, LocalDate dataNascimento, 
			TipoUsuario tipoUsuario, boolean isAtivo, String email, String senha) {
		
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.senha = senha;
	}
	
	 @PrePersist
     private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isAtivo = true;
	}
}
