package br.com.tokio.model;

public class Corretora {
	private int idCorretora; 
	private String nomeCorretora; 
	private String endereco;
	
	public Corretora(String nomeCorretora, String endereco) {
		this.nomeCorretora = nomeCorretora;
		this.endereco = endereco;
	}

	public Corretora() {
	}

	public int getIdCorretora() {
		return idCorretora;
	}

	public void setIdCorretora(int idCorretora) {
		this.idCorretora = idCorretora;
	}

	public String getNomeCorretora() {
		return nomeCorretora;
	}

	public void setNomeCorretora(String nomeCorretora) {
		this.nomeCorretora = nomeCorretora;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	} 
	
	
	
}
