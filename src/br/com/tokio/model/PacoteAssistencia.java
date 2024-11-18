package br.com.tokio.model;

import java.util.ArrayList;

public class PacoteAssistencia {

	private int idAssistencia;
	private String tipo;
	private String descricao;
	private double preco;
	private ArrayList<Servico> servicos; // Há de pensar nisso aqui

	public PacoteAssistencia(String tipo, String descricao, double preco, ArrayList<Servico> servicos) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
		this.servicos = servicos;
	}

	public PacoteAssistencia() {
	}

	public int getIdAssistencia() {
		return idAssistencia;
	}

	public void setIdAssistencia(int idAssistencia) {
		this.idAssistencia = idAssistencia;
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

	public ArrayList<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(ArrayList<Servico> servicos) {
		this.servicos = servicos;
	}

}
