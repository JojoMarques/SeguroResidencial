package br.com.tokio.model;

import java.sql.Date;

public class Seguro {
	private int idSeguro;
	private double valorPremio;
	private int idCobertura;
	private int idAssistencia;
	private Date dataInicio;
	private Date dataFim;
	private int idCliente;

	public Seguro(double valorPremio, int idCobertura, int idAssistencia, Date dataInicio,
			Date dataFim, int idCliente) {
		this.valorPremio = valorPremio;
		this.idCobertura = idCobertura;
		this.idAssistencia = idAssistencia;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.idCliente = idCliente;
	}

	public Seguro() {
	}

	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public double getValorPremio() {
		return valorPremio;
	}

	public void setValorPremio(double valorPremio) {
		this.valorPremio = valorPremio;
	}

	public int getIdCobertura() {
		return idCobertura;
	}

	public void setIdCobertura(int idCobertura) {
		this.idCobertura = idCobertura;
	}

	public int getIdAssistencia() {
		return idAssistencia;
	}

	public void setIdAssistencia(int idAssistencia) {
		this.idAssistencia = idAssistencia;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
