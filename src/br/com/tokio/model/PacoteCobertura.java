package br.com.tokio.model;

import java.util.ArrayList;

public class PacoteCobertura {

	private int idCobertura;
	private String tipo;
	private String descricao;
	private double preco;
	private ArrayList<String> coberturas; // tem que pensar nesse, em como as coisas são passadas

	public PacoteCobertura(String tipo, String descricao, double preco, ArrayList<String> coberturas) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
		this.coberturas = coberturas;
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

	public ArrayList<String> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(ArrayList<String> coberturas) {
		this.coberturas = coberturas;
	}

}
