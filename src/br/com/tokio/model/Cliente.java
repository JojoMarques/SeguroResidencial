package br.com.tokio.model;

public class Cliente extends Usuario {

	private int idCliente;
	private int senhaCliente;
	private int idImovel;

	public Cliente(String nome, String cpf, String telefone, String email, int senhaCliente, int idImovel) {
		super(nome, cpf, telefone, email);
		this.senhaCliente = senhaCliente;
		this.idImovel = idImovel;
	}

	public Cliente() {
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(int senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

	public int getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(int idImovel) {
		this.idImovel = idImovel;
	}
}
