package br.com.tokio.testes;

import java.sql.Date;

import br.com.tokio.model.Cliente;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.Seguro;

public class TestesMaris {

	public static void main(String[] args) {
		
		Cliente c1 = new Cliente ("jo", "123123123", "11995748562", "tavares@gmail.com",
			"sainz");
		
		Imovel i1 = new Imovel(1_000_000, 200, "Brasil", "São Paulo", "São Paulo", 
				"Av. Paulista", 1000, "02476154", 1);
		/*
		 * só dá pra testar depois que tiver as coisas de plano
		 * 
		Seguro s1 = new Seguro(100_000, null, null, new Date(System.currentTimeMillis()), 
				null, 1);
		*/
		
		
	}

}
