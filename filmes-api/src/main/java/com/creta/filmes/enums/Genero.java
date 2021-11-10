package com.creta.filmes.enums;

public enum Genero {
	
	ACAO (1,"Ação"),
	TERROR(2, "Terror"),
	AVENTURA(3, "Aventura"),
	SUSPENSE(4, "Suspense"),
	COMEDIA(5, "Comédia"),
	ROMANCE(6, "Romance");
	
	private int cod;
	private String descricao;
	
	private Genero(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Genero toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Genero x : Genero.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
