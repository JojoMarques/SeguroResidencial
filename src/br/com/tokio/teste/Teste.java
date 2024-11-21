package br.com.tokio.teste;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.EventoDAO;
import br.com.tokio.dao.ImovelDAO;
import br.com.tokio.dao.PacoteAssistenciaDAO;
import br.com.tokio.dao.PacoteCoberturaDAO;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.dao.ServicoDAO;
import br.com.tokio.dao.SinistroDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Evento;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.PacoteAssistencia;
import br.com.tokio.model.PacoteCobertura;
import br.com.tokio.model.Seguro;
import br.com.tokio.model.Servico;
import br.com.tokio.model.Sinistro;
import br.com.tokio.connection.ConnectionFactory;

public class Teste {

    public static void main(String[] args) {
        // Criação da conexão com o banco
        Connection connection = new ConnectionFactory().conectar();

        // Criação dos DAOs
        ImovelDAO imovelDAO = new ImovelDAO(connection);
        SeguroDAO seguroDAO = new SeguroDAO(connection);
        SinistroDAO sinistroDAO = new SinistroDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        PacoteAssistenciaDAO pacoteAssistenciaDAO = new PacoteAssistenciaDAO(connection);
        PacoteCoberturaDAO pacoteCoberturaDAO = new PacoteCoberturaDAO(connection);
        EventoDAO eventoDAO = new EventoDAO(connection);
        ServicoDAO servicoDAO = new ServicoDAO(connection);
        
        
        Cliente cliente1 = new Cliente("Carlos Silva", "12345678901", "11987654321", "carlos@gmail.com", "senha123");
        Cliente cliente2 = new Cliente("Maria Oliveira", "10987654321", "11998877665", "maria@gmail.com", "senha456");
        
        clienteDAO.insert(cliente1);
        clienteDAO.insert(cliente2);

        System.out.println("Clientes inseridos com sucesso.");

        // Criação e inserção do Imovel
        Imovel imovel1 = new Imovel(250000, 120.5, "Brasil", "São Paulo", "Centro", "Av. Paulista", 100, "01311000", 1);
        Imovel imovel2 = new Imovel(350000, 150.0, "Brasil", "Rio de Janeiro", "Copacabana", "Av. Atlântica", 200, "22070000", 2);
        imovelDAO.insert(imovel1);
        imovelDAO.insert(imovel2);
        System.out.println("Imóveis inseridos com sucesso.");
        
        
        // Criando alguns serviços para PacoteAssistencia
        Servico servico1 = new Servico("Serviço de Reparo", "Reparo de vidros quebrados");
        Servico servico2 = new Servico("Serviço de Troca", "Troca de pneus de emergência");
        Servico servico3 = new Servico("Serviço de Revisão", "Revisão geral do veículo");
        Servico servico4 = new Servico("Serviço de Troca de Óleo", "Troca de óleo do motor do veículo");
        Servico servico5 = new Servico("Serviço de Pintura", "Pintura e retoques no veículo");
        //salvando no banco
        servicoDAO.insert(servico1);
        servicoDAO.insert(servico2);
        servicoDAO.insert(servico3);
        servicoDAO.insert(servico4);
        servicoDAO.insert(servico5);
        
        // Criando eventos para PacoteCobertura
        Evento evento1 = new Evento("Reparo Emergencial", "Reparo de emergência em locais remotos");
        Evento evento2 = new Evento("Assistência Dúvida", "Suporte em caso de dúvidas durante o processo");
        Evento evento3 = new Evento("Reboque", "Serviço de reboque para veículos quebrados");
        Evento evento4 = new Evento("Acompanhamento de Roteiro", "Acompanhamento durante viagens longas");
        //salvando no banco
        eventoDAO.insert(evento1);
        eventoDAO.insert(evento2);
        eventoDAO.insert(evento3);
        eventoDAO.insert(evento4);

        
        // Criando listas de eventos para PacoteCobertura
        List<Evento> eventosPacoteCobertura1 = eventoDAO.selectAll();
    

        List<Evento> eventosPacoteCobertura2 = eventoDAO.selectAll();
      
        // Criando pacotes de cobertura
        PacoteCobertura pacoteCobertura1 = new PacoteCobertura("Básica", "Cobertura de eventos emergenciais", 500.00, eventosPacoteCobertura1);
        PacoteCobertura pacoteCobertura2 = new PacoteCobertura("Completa", "Cobertura de todos os eventos de assistência", 200.00, eventosPacoteCobertura2);

        // Criando pacotes de assistência
        List<Servico> servicosPacoteAssistencia1 = servicoDAO.selectAll();

        List<Servico> servicosPacoteAssistencia2 = servicoDAO.selectAll();
        
        PacoteAssistencia pacoteAssistencia1 = new PacoteAssistencia("Básica", "Assistência para serviços emergenciais", 300.00, servicosPacoteAssistencia1);
        PacoteAssistencia pacoteAssistencia2 = new PacoteAssistencia("Completa", "Assistência completa com serviços diversos", 800.00, servicosPacoteAssistencia2);
        
        // Inserindo pacotes de cobertura e assistência no banco de dados
        System.out.println("----------------------------- inserindo PACOTES");

        pacoteCoberturaDAO.insert(pacoteCobertura1);
        pacoteCoberturaDAO.insert(pacoteCobertura2);
        pacoteAssistenciaDAO.insert(pacoteAssistencia1);
        pacoteAssistenciaDAO.insert(pacoteAssistencia2);
        
        System.out.println("Pacotes de Cobertura e Assistência inseridos com sucesso!");

        // Criação e inserção do Seguro (temos que pegar o idCliente previamente inserido, ex: idCliente = 1, 2)
        Seguro seguro1 = new Seguro(2000.00, 1, 1, Date.valueOf("2023-05-10"), Date.valueOf("2023-05-10"), 1, 1);
        Seguro seguro2 = new Seguro(3000.00, 1, 2,  Date.valueOf("2023-05-10"),  Date.valueOf("2023-05-10"), 2, 2);
        seguroDAO.insert(seguro1);
        seguroDAO.insert(seguro2);
        System.out.println("Seguros inseridos com sucesso.");

        // Criação e inserção do Sinistro
        Sinistro sinistro1 = new Sinistro("Furto", Date.valueOf("2023-05-10"), "Furto de veículo", true, 1, 1);
        Sinistro sinistro2 = new Sinistro("Acidente", Date.valueOf("2023-06-15"), "Acidente de carro", false, 2, 2);
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