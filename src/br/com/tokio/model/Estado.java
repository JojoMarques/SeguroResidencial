package br.com.tokio.model;

public enum Estado {
	
	ACRE("AC"),
	ALAGOAS("AL"),
	AMAPA("AP"),
	AMAZONAS("AM"),
	BAHIA("BA"),
	CEARA("CE"),
	ESPIRITO_SANTO("ES"),
	GOIAS("GO"),
	MARANHAO("MA"),
	MATO_GROSSO("MT"),
	MATO_GROSSO_DO_SUL("MS"),
	MINAS_GERAIS("MG"),
	PARA("PA"),
	PARAIBA("PB"),
	PARANA("PR"),
	PERNAMBUCO("PE"),
	PIAUI("PI"),
	RIO_DE_JANEIRO("RJ"),
	RIO_GRANDE_DO_NORTE("RN"),
	RIO_GRANDE_DO_SUL("RS"),
	RONDONIA("RO"),
	RORAIMA("RR"),
	SANTA_CATARINA("SC"),
	SAO_PAULO("SP"),
	SERGIPE("SE"),
	TOCANTINS("TO"),
	DISTRITO_FEDERAL("DF");

	private final String sigla;

	// Construtor do enum para associar o código com a string
	Estado(String codigo) {
		this.sigla = codigo;
	}

	// Método para obter o código associado ao enum (o valor que será salvo no
	// banco)
	public String getSigla() {
		return sigla;
	}

	// Método para converter uma String para o valor do Enum
	public static Estado fromCodigo(String codigo) {
		for (Estado estado : Estado.values()) {
			if (estado.getSigla().equalsIgnoreCase(codigo)) {
				return estado;
			}
		}
		throw new IllegalArgumentException("Sigla de estado inválido: " + codigo);
	}
}
