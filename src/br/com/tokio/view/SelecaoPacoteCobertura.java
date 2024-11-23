package br.com.tokio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.PacoteCoberturaDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.PacoteCobertura;
import br.com.tokio.model.Seguro;

public class SelecaoPacoteCobertura {

	private JFrame frame;
	Cliente clienteRecebido;
	Seguro seguroRecebido;
	String corretoraRecebida;
	String habitacaoRecebida;
	int pacoteCoberturaSelecionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecaoPacoteCobertura window = new SelecaoPacoteCobertura();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela 'seleção pacote cobertura', onde, no processo de cotação/contratação, se escolhe o pacote de cobertura (no momento são 3)
	 */
	public SelecaoPacoteCobertura() {
		initialize();
	}

	/**
	 * Cria a tela 'seleção pacote cobertura', onde, no processo de cotação/contratação, se escolhe o pacote de cobertura (no momento são 3)
	 * agora passando os dados das telas anteriores
	 *
	 */
	public SelecaoPacoteCobertura(Cliente cliente, Seguro seguro, String corretora, String habitacao) {
		this.clienteRecebido = cliente;
		this.seguroRecebido = seguro;
		this.corretoraRecebida = corretora;
		this.habitacaoRecebida = habitacao;
		initialize();
	}

	
	private void initialize() {
		Connection connection = new ConnectionFactory().conectar();
		PacoteCoberturaDAO pacoteCoberturaDAO = new PacoteCoberturaDAO(connection);
		frame = new JFrame("Seleção de Pacote");
		frame.setBounds(100,100, 800, 600); // Tamanho menor
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); // Usando layout nulo para posicionamento manual

		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(51, 153, 102));
		panelHeader.setBounds(0, 0, 784, 100);
		frame.getContentPane().add(panelHeader);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
		Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		panelHeader.setLayout(null);

		JButton btnLogo = new JButton(icon);
		btnLogo.setFocusPainted(false);
		btnLogo.setBorder(BorderFactory.createEmptyBorder());
		btnLogo.setBackground(new Color(0, 153, 102));
		btnLogo.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogo);

		JLabel lblCotacao = new JLabel("Cotação do");
		lblCotacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblCotacao.setForeground(Color.WHITE);
		lblCotacao.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCotacao.setBackground(Color.WHITE);
		lblCotacao.setBounds(280, 21, 224, 31);
		panelHeader.add(lblCotacao);

		JLabel lblCotacao2 = new JLabel("seguro residencial");
		lblCotacao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCotacao2.setForeground(Color.WHITE);
		lblCotacao2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCotacao2.setBackground(Color.WHITE);
		lblCotacao2.setBounds(283, 52, 218, 32);
		panelHeader.add(lblCotacao2);

		JPanel panelNav = new JPanel();
		panelNav.setBackground(new Color(0, 153, 102));
		panelNav.setBounds(651, 30, 109, 39);
		panelHeader.add(panelNav);
		panelNav.setLayout(new GridLayout(0, 2, 10, 0));

		ImageIcon iconLeft = new ImageIcon(getClass().getResource("/resources/images/chevron_left.png"));
		Image imgLeft = iconLeft.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconLeft = new ImageIcon(imgLeft);

		// volta para a pagina anterior
		JButton btnVoltar = new JButton(iconLeft);
		btnVoltar.setBackground(new Color(225, 193, 85));
		panelNav.add(btnVoltar);

		ImageIcon iconRight = new ImageIcon(getClass().getResource("/resources/images/chevron_right.png"));
		Image imgRight = iconRight.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconRight = new ImageIcon(imgRight);

		// vai para a proxima pagina
		JButton btnAvancar = new JButton(iconRight);
		btnAvancar.setBackground(new Color(225, 193, 85));
		panelNav.add(btnAvancar);
		// -----------------------------------------------------------------------------------

		JPanel panel = new JPanel();
		panel.setBounds(10, 111, 757, 439);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Título da seção
		JLabel lblEscolhaSuaCobertura = new JLabel("Escolha seu pacote de cobertura:");
		lblEscolhaSuaCobertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaSuaCobertura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEscolhaSuaCobertura.setBounds(197, 11, 369, 30);
		panel.add(lblEscolhaSuaCobertura);

		// Pacote Cobertura 1
		JPanel panelPacote1 = new JPanel();
		panelPacote1.setBounds(5, 81, 245, 328);
		panelPacote1.setBackground(new Color(169, 196, 145));
		panelPacote1.setLayout(new GridLayout(4, 1));
		panel.add(panelPacote1);

		// Obter informações do pacote 1
		PacoteCobertura pacoteCobertura1 = pacoteCoberturaDAO.selectById(1);

		// Nome do pacote
		JLabel lblNomePacote1 = new JLabel(pacoteCobertura1.getTipo().toUpperCase(), JLabel.CENTER);
		lblNomePacote1.setFont(new Font("Arial", Font.BOLD, 14));
		panelPacote1.add(lblNomePacote1);

		// Preço do pacote
		JLabel lblValorPacote1 = new JLabel("R$ " + pacoteCobertura1.getPreco() + " /mês", JLabel.CENTER);
		lblValorPacote1.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPacote1.add(lblValorPacote1);

		// Lista de eventos do pacote 1
		String eventos1 = pacoteCoberturaDAO.selectEventos(1);
		JList<String> listEventos1 = new JList<>(eventos1.split(", "));
		listEventos1.setBackground(new Color(216, 216, 216));
		listEventos1.setFont(new Font("Arial", Font.PLAIN, 12));
		listEventos1.setFixedCellHeight(20);
		panelPacote1.add(new JScrollPane(listEventos1));

		// Botão do pacote 1
		JPanel panelBtn1 = new JPanel();
		panelBtn1.setLayout(null);
		panelBtn1.setBackground(new Color(216, 216, 216));
		panelPacote1.add(panelBtn1);

		JButton btnEscolher1 = new JButton("ESCOLHER");
		btnEscolher1.setBounds(33, 16, 185, 36);
		btnEscolher1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEscolher1.setBackground(new Color(225, 193, 85));
		panelBtn1.add(btnEscolher1);

		btnEscolher1.addActionListener(e -> {
			pacoteCoberturaSelecionada = pacoteCobertura1.getIdCobertura();
			SelecaoPacoteAssistencia selecaoPacoteAssistencia = new SelecaoPacoteAssistencia(clienteRecebido,
					seguroRecebido, corretoraRecebida, habitacaoRecebida, pacoteCoberturaSelecionada); // aqui tem que
			selecaoPacoteAssistencia.show();
			frame.dispose();

		});

		// Replicar estrutura para Pacote 2 e 3 com mudanças específicas
		// Pacote Cobertura 2
		JPanel panelPacote2 = new JPanel();
		panelPacote2.setBounds(255, 81, 245, 328);
		panelPacote2.setBackground(new Color(145, 189, 148));
		panelPacote2.setLayout(new GridLayout(4, 1));
		panel.add(panelPacote2);

		PacoteCobertura pacoteCobertura2 = pacoteCoberturaDAO.selectById(2);

		JLabel lblNomePacote2 = new JLabel(pacoteCobertura2.getTipo().toUpperCase(), JLabel.CENTER);
		lblNomePacote2.setFont(new Font("Arial", Font.BOLD, 14));
		panelPacote2.add(lblNomePacote2);

		JLabel lblValorPacote2 = new JLabel("R$ " + pacoteCobertura2.getPreco() + " /mês", JLabel.CENTER);
		lblValorPacote2.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPacote2.add(lblValorPacote2);

		String eventos2 = pacoteCoberturaDAO.selectEventos(2);
		JList<String> listEventos2 = new JList<>(eventos2.split(", "));
		listEventos2.setBackground(new Color(216, 216, 216));
		listEventos2.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPacote2.add(new JScrollPane(listEventos2));

		JPanel panelBtn2 = new JPanel();
		panelBtn2.setLayout(null);
		panelBtn2.setBackground(new Color(216, 216, 216));
		panelPacote2.add(panelBtn2);

		JButton btnEscolher2 = new JButton("ESCOLHER");
		btnEscolher2.setBounds(33, 16, 185, 36);
		btnEscolher2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEscolher2.setBackground(new Color(225, 193, 85));
		panelBtn2.add(btnEscolher2);

		btnEscolher2.addActionListener(e -> {
			pacoteCoberturaSelecionada = pacoteCobertura2.getIdCobertura();
			SelecaoPacoteAssistencia selecaoPacoteAssistencia = new SelecaoPacoteAssistencia(clienteRecebido,
					seguroRecebido, corretoraRecebida, habitacaoRecebida, pacoteCoberturaSelecionada); // aqui tem que
																										// passar o id
																										// do pacote
																										// cobertura :)
			selecaoPacoteAssistencia.show();
			frame.dispose();

		});

		// Pacote Cobertura 3
		JPanel panelPacote3 = new JPanel();
		panelPacote3.setBounds(505, 81, 245, 328);
		panelPacote3.setBackground(new Color(141, 186, 173));
		panelPacote3.setLayout(new GridLayout(4, 1));
		panel.add(panelPacote3);

		PacoteCobertura pacoteCobertura3 = pacoteCoberturaDAO.selectById(3);

		JLabel lblNomePacote3 = new JLabel(pacoteCobertura3.getTipo().toUpperCase(), JLabel.CENTER);
		lblNomePacote3.setFont(new Font("Arial", Font.BOLD, 14));
		panelPacote3.add(lblNomePacote3);

		JLabel lblValorPacote3 = new JLabel("R$ " + pacoteCobertura3.getPreco() + " /mês", JLabel.CENTER);
		lblValorPacote3.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPacote3.add(lblValorPacote3);

		String eventos3 = pacoteCoberturaDAO.selectEventos(3);
		JList<String> listEventos3 = new JList<>(eventos3.split(", "));
		listEventos3.setBackground(new Color(216, 216, 216));
		listEventos3.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPacote3.add(new JScrollPane(listEventos3));

		JPanel panelBtn3 = new JPanel();
		panelBtn3.setLayout(null);
		panelBtn3.setBackground(new Color(216, 216, 216));
		panelPacote3.add(panelBtn3);

		JButton btnEscolher3 = new JButton("ESCOLHER");
		btnEscolher3.setBounds(33, 16, 185, 36);
		btnEscolher3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEscolher3.setBackground(new Color(225, 193, 85));
		panelBtn3.add(btnEscolher3);

		btnEscolher3.addActionListener(e -> {
			pacoteCoberturaSelecionada = pacoteCobertura3.getIdCobertura();
			SelecaoPacoteAssistencia selecaoPacoteAssistencia = new SelecaoPacoteAssistencia(clienteRecebido,
					seguroRecebido, corretoraRecebida, habitacaoRecebida, pacoteCoberturaSelecionada); // aqui tem que
																										// passar o id
																										// do pacote
																										// cobertura :)
			selecaoPacoteAssistencia.show();
			pacoteCobertura3.getIdCobertura();
			frame.dispose();
		});

		// ---------------------------------------------

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		btnVoltar.addActionListener(e -> {
			SelecaoHabitacao selecaoHabitacao = new SelecaoHabitacao();
			selecaoHabitacao.show();
			frame.dispose();
		});

		btnAvancar.addActionListener(e -> {
			SelecaoPacoteAssistencia selecaoPacoteAssistencia = new SelecaoPacoteAssistencia();
			selecaoPacoteAssistencia.show();
			frame.dispose();
		});

	}

	public void show() {
		frame.setVisible(true);
	}
}
