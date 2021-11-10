package com.creta.filmes.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.creta.filmes.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private int ano;
	private int duracaoMinutos;
	private String descricao;
	
	
	@ElementCollection
	@CollectionTable(name = "DIRETOR")
	private Set<String> diretores = new HashSet<>();
	
	/*
	@ElementCollection
	@CollectionTable(name = "atores")
	private Set<String> atores = new HashSet<>();*/
	
	private Genero genero;
	private long quantidadeVotos;
	private double mediaVotos;
	private Long codigo;

	public Filme(
			String nome, 
			int ano, 
			int duracaoMinutos, 
			String descricao, 
			long quantidadeVotos, 
			double mediaVotos) {
		this.nome = nome;
		this.ano = ano;
		this.duracaoMinutos = duracaoMinutos;
		this.descricao = descricao;
		this.quantidadeVotos = 0;
		this.mediaVotos = 0.0;
	}	
	
	public void adicionaVotaFilme() {
		this.quantidadeVotos++;
	}
}
