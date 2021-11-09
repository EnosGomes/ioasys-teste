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
	@CollectionTable(name = "diretores")
	private Set<String> diretores = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "atores")
	private Set<String> atores = new HashSet<>();
	
	private Genero genero;
	private long quantidadeVotos;
	private double mediaVotos;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + duracaoMinutos;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		if (ano != other.ano)
			return false;
		if (duracaoMinutos != other.duracaoMinutos)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", ano=" + ano + ", duracaoMinutos=" + duracaoMinutos + ", descricao="
				+ descricao + ", quantidadeVotos=" + quantidadeVotos + ", mediaVotos=" + mediaVotos + "]";
	}

}
