package com.example.ejb.domain;

public class Carta {
	
	private String valor;
	private String tipo;
	private String pathImage;
	

	public Carta(String tipo, String valor, String pathImage) {
		super();
		this.valor = valor;
		this.tipo = tipo;
		this.pathImage = pathImage;
	}
	
	public Carta(String string) {
		this.pathImage = string + ".png";
		
		tipo = string;
		String[] carta = string.split("_");
		tipo = carta[1];
		valor= carta[0];
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	

}
