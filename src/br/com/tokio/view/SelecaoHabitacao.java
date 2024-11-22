package br.com.tokio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.tokio.model.Cliente;
import br.com.tokio.model.Seguro;

public class SelecaoHabitacao {

	private JFrame frame;
	private JComboBox<String> comboBox; // Caixa de seleção (JComboBox)

	Cliente clienteRecebido;
	Seguro seguroRecebido;
	String corretoraRecebida;
	String habitacaoSelecionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecaoHabitacao window = new SelecaoHabitacao();
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
	public SelecaoHabitacao() {
		initialize();
	}

	public SelecaoHabitacao(Cliente cliente, Seguro seguro, String corretora) {
		this.clienteRecebido = cliente;
		this.seguroRecebido = seguro;
		this.corretoraRecebida = corretora;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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

		JPanel panel = new JPanel();
		panel.setBounds(139, 139, 505, 345);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Adicionando um JLabel para instrução
		JLabel lblSelecione = new JLabel("Selecione um tipo de habitação:");
		lblSelecione.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecione.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelecione.setBounds(139, 51, 226, 30);
		panel.add(lblSelecione);

		// Criando a ComboBox (caixa de seleção)
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(102, 122, 300, 36);
		panel.add(comboBox);
		comboBox.addItem("Veraneiro");
		comboBox.addItem("Moradia");

		// Botão para exibir o item selecionado
		JButton btnExibir = new JButton("Habitação selecionada:");
		btnExibir.setBounds(130, 224, 245, 36);
		panel.add(btnExibir);
		btnExibir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExibir.setBackground(new Color(225, 193, 85));

		// Evento para o botão (Exibir Seleção)
		btnExibir.addActionListener(e -> {
			// variavel selecionada
			habitacaoSelecionada = (String) comboBox.getSelectedItem();
			JOptionPane.showMessageDialog(frame, "Você selecionou: " + habitacaoSelecionada);
		});

		// -------------------------------------------------------------

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		btnVoltar.addActionListener(e -> {
			TelaCorretora telaCorretora = new TelaCorretora();
			telaCorretora.show();
			frame.dispose();
		});

		btnAvancar.addActionListener(e -> {
			SelecaoPacoteCobertura selecaoPacoteCobertura = new SelecaoPacoteCobertura(clienteRecebido,seguroRecebido,corretoraRecebida,habitacaoSelecionada);
			// 
			selecaoPacoteCobertura.show();
			frame.dispose();
		});
	}

	public void show() {
		frame.setVisible(true);
	}
}
