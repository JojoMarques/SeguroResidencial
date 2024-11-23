package br.com.tokio.view.cliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.Seguro;
import br.com.tokio.view.TelaInicial;

public class AreaCliente {

	private JFrame frame;
	private JTextField textFieldValor;
	private JTextField textFieldData;
	private JTextField textField;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaCliente window = new AreaCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela 'área do cliente', onde ele pode ser direcionado para o visualizar dados, vizualizar apólice
	 * e solicitar sinistro [o sinistro é registrado no banco de dados],
	 * agora passando os dados do cliente logado
	 * @param int - id do cliente
	 */
	public AreaCliente(int idCliente) {
		this.idRecebido = idCliente;
		initialize();
	}

	/**
	 * Cria a tela 'área do cliente', onde ele pode ser direcionado para o visualizar dados, vizualizar apólice
	 * e solicitar sinistro [o sinistro é registrado no banco de dados]
	 */
	public AreaCliente() {
		initialize();
	}

	
	private void initialize() {
		Connection connection = new ConnectionFactory().conectar();

		System.out.println("id q chegou aqui na area cliente:" + idRecebido);
		frame = new JFrame();
		frame.setBounds(100,100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(51, 153, 102));
		panelHeader.setBounds(0, 0, 784, 100);
		frame.getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
		Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		panelHeader.setLayout(null);

		JButton btnLogo = new JButton(icon);
		btnLogo.setBorder(BorderFactory.createEmptyBorder());
		btnLogo.setFocusPainted(false);
		btnLogo.setBounds(5, 15, 243, 69);
		btnLogo.setBackground(new Color(0, 153, 102));
		panelHeader.add(btnLogo);

		JLabel lblAreaDoCliente = new JLabel("Área do Cliente");
		lblAreaDoCliente.setForeground(new Color(255, 255, 255));
		lblAreaDoCliente.setBackground(new Color(255, 255, 255));
		lblAreaDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblAreaDoCliente.setBounds(279, 15, 226, 69);
		panelHeader.add(lblAreaDoCliente);

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		// Painel para as informações do cliente
		JPanel panelInformacoes = new JPanel();
		panelInformacoes.setBounds(391, 139, 393, 344); // Posição e tamanho do painel
		panelInformacoes.setLayout(null); // Layout absoluto dentro do painel
		frame.getContentPane().add(panelInformacoes);

		// Botão de editar informações
		JButton btnInformacoes = new JButton("Suas informações");
		btnInformacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInformacoes.setBackground(new Color(225, 193, 85));
		btnInformacoes.setBounds(96, 249, 200, 30); // Posição no painel
		panelInformacoes.add(btnInformacoes);

		JButton btnApolice = new JButton("Sua apólice");
		btnApolice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApolice.setBackground(new Color(225, 193, 85));
		btnApolice.setBounds(96, 156, 200, 30);
		panelInformacoes.add(btnApolice);

		JButton btnSinistro = new JButton("Solicitar sinistro");
		btnSinistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSinistro.setBackground(new Color(225, 193, 85));
		btnSinistro.setBounds(96, 63, 200, 30);
		panelInformacoes.add(btnSinistro);

		JPanel panel = new JPanel();
		panel.setBounds(0, 139, 393, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblProximoPagto = new JLabel("Próximo Pagamento:");
		lblProximoPagto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProximoPagto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProximoPagto.setBounds(98, 34, 196, 14);
		panel.add(lblProximoPagto);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblData.setBounds(42, 102, 46, 14);
		panel.add(lblData);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValor.setBounds(42, 154, 46, 14);
		panel.add(lblValor);

		textFieldValor = new JTextField();
		textFieldValor.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldValor.setBackground(SystemColor.control);
		textFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldValor.setBounds(98, 146, 196, 30);

		ClienteDAO clienteDAO = new ClienteDAO(connection);
		Imovel imovel = clienteDAO.selectImovelByClienteId(idRecebido);
		Seguro seguro = clienteDAO.selectSeguroByClienteId(idRecebido);

		// Buscar valores dos pacotes associados ao seguro
		double valorAssistencia = clienteDAO.getValorAssistencia(seguro.getIdAssistencia());
		double valorCobertura = clienteDAO.getValorCobertura(seguro.getIdCobertura());

		// Fatores ajustados para o seguro residencial
		double fatorArea = 1 + (imovel.getArea() / 1000); // Fator de risco baseado na área
		double fatorBasePremio = 0.005; // Taxa base de 0,5% sobre o valor do imóvel
		double premioBase = imovel.getValorImovel() * fatorBasePremio; // Prêmio base calculado

		// Adicionar valores dos pacotes ao prêmio
		double mensalidade = (premioBase * fatorArea) + valorAssistencia + valorCobertura + 30.00; 
																								
		textFieldValor.setText(String.valueOf(mensalidade));
		panel.add(textFieldValor);
		textFieldValor.setColumns(10);

		SeguroDAO  seguroDAO = new SeguroDAO(connection);
		seguro = seguroDAO.selectByCliente(idRecebido);
		Date dataInicio = seguro.getDataInicio();
		Date dataVencimento = new Date(dataInicio.getTime() + (30L * 24 * 60 * 60 * 1000)); // add um mês na mensalidade

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato modificado

		textFieldData = new JTextField();
		textFieldData.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldData.setBackground(SystemColor.control);
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldData.setColumns(10);
		textFieldData.setBounds(98, 94, 196, 30);
		textFieldData.setText(sdf.format(dataVencimento));

		panel.add(textFieldData);

		JLabel lblPlano = new JLabel("Seu plano é válido até:");
		lblPlano.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPlano.setBounds(21, 265, 153, 14);
		panel.add(lblPlano);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBackground(SystemColor.control);
		textField.setBounds(160, 257, 98, 30);
		textField.setText(sdf.format(seguro.getDataFim()));

		panel.add(textField);

		// Evento para abrir a tela EditarCliente
		btnInformacoes.addActionListener(e -> {
			InformacoesCliente informacoesCliente = new InformacoesCliente(idRecebido);
			informacoesCliente.show(); // Mostra a tela de edição
			frame.dispose(); // Fecha a tela atual
		});

		btnSinistro.addActionListener(e -> {
			SolicitarSinistro solicitarSinistro = new SolicitarSinistro(idRecebido);
			solicitarSinistro.show();
			frame.dispose();
		});

		btnApolice.addActionListener(e -> {
			VisualizarApolice visualizarApolice = new VisualizarApolice(idRecebido);
			visualizarApolice.show();
			frame.dispose();
		});

	}

	
	public void show() {
		frame.setVisible(true);
	}

}
