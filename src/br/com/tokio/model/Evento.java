package br.com.tokio.model;

public class Evento {
	private int idEvento;
	private String nome;
	private String descricao;

	public Evento(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Evento() {
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
