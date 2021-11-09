package com.creta.filmes.enums;

public enum TipoUsuario {
	
	ADMINISTRADOR("Administrador"),
	NORMAL("Normal");
	
	private String tipo;
	
	private TipoUsuario(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}
