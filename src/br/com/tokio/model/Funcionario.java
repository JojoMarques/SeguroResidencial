package br.com.tokio.model;

import java.sql.Date;

public class Funcionario extends Usuario {
	
	private int idFuncionario;
	private String acessoFunc;
	private String senhaFunc;
	private Date dataAdmissao;

	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, String telefone, String email, String acessoFunc, String senhaFunc,
			Date dataAdmissao) {
		super(nome, cpf, telefone, email);
		this.acessoFunc = acessoFunc;
		this.senhaFunc = senhaFunc;
		this.dataAdmissao = dataAdmissao;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getAcessoFunc() {
		return acessoFunc;
	}

	public void setAcessoFunc(String acessoFunc) {
		this.acessoFunc = acessoFunc;
	}

	public String getSenhaFunc() {
		return senhaFunc;
	}

	public void setSenhaFunc(String senhaFunc) {
		this.senhaFunc = senhaFunc;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

}
