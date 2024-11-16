package br.com.tokio.model;

import java.sql.Date;

public class Sinistro {

	private int idSinistro;
	private int idSeguro;
	private String tipoSinistro;
	private Date dataSinistro;
	private String descricao;
	private boolean status;

	public Sinistro(int idSeguro, String tipoSinistro, Date dataSinistro, String descricao, boolean status) {
		this.idSeguro = idSeguro;
		this.tipoSinistro = tipoSinistro;
		this.dataSinistro = dataSinistro;
		this.descricao = descricao;
		this.status = status;
	}

	public Sinistro() {

	}

	public int getIdSinistro() {
		return idSinistro;
	}

	public void setIdSinistro(int idSinistro) {
		this.idSinistro = idSinistro;
	}

	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
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
