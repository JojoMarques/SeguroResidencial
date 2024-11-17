package br.com.tokio.model;

import java.sql.Date;

public class Sinistro {

	private int idSinistro;
	private String tipoSinistro;
	private Date dataSinistro;
	private String descricao;
	private boolean status;
	private int idSeguro;
	private int idCliente;

	public Sinistro(String tipoSinistro, Date dataSinistro, String descricao, 
			boolean status, int idSeguro, int idCliente) {
		this.idSeguro = idSeguro;
		this.tipoSinistro = tipoSinistro;
		this.dataSinistro = dataSinistro;
		this.descricao = descricao;
		this.status = status;
		this.idSeguro = idSeguro;
		this.idCliente = idCliente;
	}

	public Sinistro() {

	}
	

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public int getIdSinistro() {
		return idSinistro;
	}

	public void setIdSinistro(int idSinistro) {
		this.idSinistro = idSinistro;
	}

	public String getTipoSinistro() {
		return tipoSinistro;
	}

	public void setTipoSinistro(String tipoSinistro) {
		this.tipoSinistro = tipoSinistro;
	}

	public Date getDataSinistro() {
		return dataSinistro;
	}

	public void setDataSinistro(Date dataSinistro) {
		this.dataSinistro = dataSinistro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
