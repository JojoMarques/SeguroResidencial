package br.com.tokio.model;

public class Imovel {

	private int idImovel;
	private double valorImovel;
	private double area;
	private String pais;
	private String cidade;
	private String bairro;
	private String logradouro;
	private int numero;
	private String cep;

	public Imovel() {
	}

	public Imovel(double valorImovel, double area, String pais, String cidade, String bairro, String logradouro,
			int numero, String cep) {
		super();
		this.valorImovel = valorImovel;
		this.area = area;
		this.pais = pais;
		this.cidade = cidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
	}

	public int getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(int idImovel) {
		this.idImovel = idImovel;
	}

	public double getValorImovel() {
		return valorImovel;
	}

	public void setValorImovel(double valorImovel) {
		this.valorImovel = valorImovel;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
