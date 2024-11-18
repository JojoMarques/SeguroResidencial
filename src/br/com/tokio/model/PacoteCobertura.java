package br.com.tokio.model;

import java.util.ArrayList;

public class PacoteCobertura {

	private int idCobertura;
	private String tipo;
	private String descricao;
	private double preco;
	private ArrayList<Evento> eventos; // tem que pensar nesse, em como as coisas são passadas

	public PacoteCobertura(String tipo, String descricao, double preco, ArrayList<Evento> eventos) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
		this.eventos = eventos;
	}

	public PacoteCobertura() {
	}

	public int getIdCobertura() {
		return idCobertura;
	}

	public void setIdCobertura(int idCobertura) {
		this.idCobertura = idCobertura;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public ArrayList<Evento> getCoberturas() {
		return eventos;
	}

	public void setCoberturas(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

}
