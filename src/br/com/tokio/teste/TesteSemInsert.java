package br.com.tokio.teste;
import java.sql.Date;
import java.util.List;

import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.ImovelDAO;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.dao.SinistroDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.Seguro;
import br.com.tokio.model.Sinistro;
import br.com.tokio.connection.ConnectionFactory;

public class TesteSemInsert {

    public static void main(String[] args) {
        // Criação da conexão com o banco
        var connection = new ConnectionFactory().conectar();

        // Criação dos DAOs
        ImovelDAO imovelDAO = new ImovelDAO(connection);
        SeguroDAO seguroDAO = new SeguroDAO(connection);
        SinistroDAO sinistroDAO = new SinistroDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);

        // Criação e inserção do Sinistro
        Sinistro sinistro1 = new Sinistro("Furto", Date.valueOf("2023-05-10"), "Furto de veículo", true, 5, 1);
        Sinistro sinistro2 = new Sinistro("Acidente", Date.valueOf("2023-06-15"), "Acidente de carro", false, 4, 2);
        sinistroDAO.insert(sinistro1);
        sinistroDAO.insert(sinistro2);
        System.out.println("Sinistros inseridos com sucesso.");

        // Recuperando e exibindo os dados inseridos
        System.out.println("Clientes cadastrados:");
        for (Cliente cliente : clienteDAO.selectAll()) {
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Senha: " + cliente.getSenhaCliente());
            System.out.println("----------------------------------------");
        }

        System.out.println("\nListando Imóveis:");
        for (Imovel imovel : imovelDAO.selectAll()) {
            System.out.println("ID Imóvel: " + imovel.getIdImovel());
            System.out.println("Valor: " + imovel.getValorImovel());
            System.out.println("Área: " + imovel.getArea());
            System.out.println("Cidade: " + imovel.getCidade());
            System.out.println("----------------------------------");
        }

        System.out.println("\nListando Seguros:");
        for (Seguro seguro : seguroDAO.selectAll()) {
            System.out.println("ID Seguro: " + seguro.getIdSeguro());
            System.out.println("Valor do Prêmio: " + seguro.getValorPremio());
            System.out.println("Data Início: " + seguro.getDataInicio());
            System.out.println("Data Fim: " + seguro.getDataFim());
            System.out.println("----------------------------------");
        }

        System.out.println("\nListando Sinistros:");
        for (Sinistro sinistro : sinistroDAO.selectAll()) {
            System.out.println("ID Sinistro: " + sinistro.getIdSinistro());
            System.out.println("Tipo: " + sinistro.getTipoSinistro());
            System.out.println("Data: " + sinistro.getDataSinistro());
            System.out.println("Status: " + sinistro.isStatus());
            System.out.println("----------------------------------");
        }
    }
}
