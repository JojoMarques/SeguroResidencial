package br.com.tokio.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

public class SelecaoPacoteCobertura {

	private JFrame frame;

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
	 * Create the application.
	 */
	public SelecaoPacoteCobertura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Seleção de Pacote");
		frame.setBounds(400, 200, 800, 600); // Tamanho menor
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

		JLabel lblEscolhaSuaCobertura = new JLabel("Escolha seu pacote de cobertura:");
		lblEscolhaSuaCobertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaSuaCobertura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEscolhaSuaCobertura.setBackground(Color.BLACK);
		lblEscolhaSuaCobertura.setBounds(197, 11, 369, 30);
		panel.add(lblEscolhaSuaCobertura);


		// Criando um painel para o pacote
		JPanel panelPacote1 = new JPanel();
		panelPacote1.setBounds(5, 81, 245, 277);
		panel.add(panelPacote1);
		panelPacote1.setLayout(new GridLayout(4, 1)); // Dividido em 3 linhas
		panelPacote1.setBackground(new Color(169, 196, 145));

		// Título do pacote
		JLabel lblNomePacote1 = new JLabel("PACOTE BÁSICO", JLabel.CENTER);
		lblNomePacote1.setBackground(new Color(151, 204, 136));
		panelPacote1.add(lblNomePacote1);
		lblNomePacote1.setFont(new Font("Arial", Font.BOLD, 14));

		// Preço
		JLabel lblValorPacote1 = new JLabel("R$ 20,12/mês", JLabel.CENTER);
		lblValorPacote1.setBackground(new Color(151, 204, 136));
		panelPacote1.add(lblValorPacote1);
		lblValorPacote1.setFont(new Font("Arial", Font.PLAIN, 12));

		JList listEventos1 = new JList();
		listEventos1.setBackground(new Color(216, 216, 216));
		panelPacote1.add(listEventos1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(216, 216, 216));
		panelPacote1.add(panel_1);
		panel_1.setLayout(null);

		// Botão para selecionar
		JButton btnEscolher1 = new JButton("ESCOLHER");
		btnEscolher1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEscolher1.setBounds(33, 16, 185, 36);
		panel_1.add(btnEscolher1);
		btnEscolher1.setBackground(new Color(225, 193, 85));

		JPanel panelPacote2 = new JPanel();
		panelPacote2.setBounds(255, 81, 245, 277);
		panel.add(panelPacote2);
		panelPacote2.setBackground(new Color(145, 189, 148));
		panelPacote2.setLayout(new GridLayout(4, 1));

		JLabel lblNomePacote2 = new JLabel("PACOTE BÁSICO", SwingConstants.CENTER);
		lblNomePacote2.setBackground(new Color(151, 204, 136));
		lblNomePacote2.setFont(new Font("Arial", Font.BOLD, 14));
		panelPacote2.add(lblNomePacote2);

		JLabel lblValorPacote2 = new JLabel("R$ 20,12/mês", SwingConstants.CENTER);
		lblValorPacote2.setBackground(new Color(151, 204, 136));
		lblValorPacote2.setFont(new Font("Arial", Font.PLAIN, 12));
		panelPacote2.add(lblValorPacote2);

		JList listEventos2 = new JList();
		listEventos2.setBackground(new Color(216, 216, 216));
		panelPacote2.add(listEventos2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(216, 216, 216));
		panelPacote2.add(panel_2);
		panel_2.setLayout(null);

		JButton btnEscolher2 = new JButton("ESCOLHER");
		btnEscolher2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEscolher2.setBounds(33, 16, 185, 36);
		panel_2.add(btnEscolher2);
		btnEscolher2.setBackground(new Color(225, 193, 85));

		// Criando um painel para o pacote
		JPanel panelPacote3 = new JPanel();
		panelPacote3.setBounds(505, 81, 245, 277);
		panel.add(panelPacote3);
		panelPacote3.setLayout(new GridLayout(4, 1)); // Dividido em 3 linhas
		panelPacote3.setBackground(new Color(141, 186, 173));

		// Título do pacote
		JLabel lblNomePacote3 = new JLabel("PACOTE BÁSICO", JLabel.CENTER);
		lblNomePacote3.setBackground(new Color(151, 204, 136));
		panelPacote3.add(lblNomePacote3);
		lblNomePacote3.setFont(new Font("Arial", Font.BOLD, 14));

		// Preço
		JLabel lblValorPacote3 = new JLabel("R$ 20,12/mês", JLabel.CENTER);
		lblValorPacote3.setBackground(new Color(151, 204, 136));
		panelPacote3.add(lblValorPacote3);
		lblValorPacote3.setFont(new Font("Arial", Font.PLAIN, 12));

		JList listEventos3 = new JList();
		listEventos3.setBackground(new Color(216, 216, 216));
		panelPacote3.add(listEventos3);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(216, 216, 216));
		panelPacote3.add(panel_3);
		panel_3.setLayout(null);

		// Botão para selecionar
		JButton btnEscolher3 = new JButton("ESCOLHER");
		btnEscolher3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEscolher3.setBounds(33, 16, 185, 36);
		panel_3.add(btnEscolher3);
		btnEscolher3.setBackground(new Color(225, 193, 85));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(767, 100, 17, 461);
		frame.getContentPane().add(scrollBar);

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
