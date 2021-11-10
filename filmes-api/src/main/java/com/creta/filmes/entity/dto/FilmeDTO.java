package com.creta.filmes.entity.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.creta.filmes.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {
	
	private Long id;
	
	@NotBlank
	@Size(min=2, max=40, message = "Nome deve ter ao menos 2 caracteres")
	private String nome;
	
	@Min(1900)
    @Max(2021)
	private int ano;
	
	@NotEmpty
	@Min(1)
    @Max(1000)
	private int duracaoMinutos;
	
	@NotBlank
	@Size(min=5, max=40, message = "Descrição deve ter ao menos 5 caracteres")
	private String descricao;
	
	private Set<String> diretores = new HashSet<>();
	//private Set<String> atores = new HashSet<>();	
	
	@NotNull
	private Genero genero;
	
	@Min(1)
    @Max(1000)
	private Long codigo;

}
