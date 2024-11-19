package br.com.tokio.model;

public class Cliente extends Usuario{

	private int idCliente;
	private String senhaCliente;

	public Cliente(String nome, String cpf, String telefone, String email, String senhaCliente) {
		super(nome, cpf, telefone, email);
		this.senhaCliente = senhaCliente;
	}

	public Cliente() {
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

}
