package br.com.tokio.model;

public class Servico {
	private int idServico;
	private String nome;
	private String descricao;

	public Servico(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Servico() {
	}

	public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
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
