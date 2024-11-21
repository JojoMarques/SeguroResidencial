package br.com.tokio.model;

import java.util.List;

public interface Autenticar {

	public List<Integer> autenticacao(String user, String senha);
	
}