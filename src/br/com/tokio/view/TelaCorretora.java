package br.com.tokio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.CorretoraDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Corretora;
import br.com.tokio.model.Seguro;

public class TelaCorretora {

	private JFrame frame;
	private JComboBox<String> comboBox; // Caixa de seleção (JComboBox)
	Cliente clienteRecebido;
	Seguro seguroRecebido;
	String corretoraSelecionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCorretora window = new TelaCorretora();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela 'tela corretora', onde, no processo de cotação/contratação, se escolhe a corretora 
	 */
	public TelaCorretora() {
		initialize();
	}

	/**
	 * Cria a tela 'tela corretora', onde, no processo de cotação/contratação, se escolhe a corretora,
	 * agora passando os dados das telas anteriores
	  * @param Cliente - o cliente do seguro
	  * @param Seguro - o seguro contratado
	 */
	public TelaCorretora(Cliente cliente, Seguro seguro) {
		this.clienteRecebido = cliente;
		this.seguroRecebido = seguro;
		initialize();		
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100, 800, 600);
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
		JLabel lblSelecione = new JLabel("Selecione uma corretora:");
		lblSelecione.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecione.setBounds(154, 51, 183, 30);
		panel.add(lblSelecione);
		lblSelecione.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// Criando a ComboBox (caixa de seleção)
		comboBox = new JComboBox<>();
		comboBox.setBounds(99, 122, 307, 36);
		panel.add(comboBox);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setBackground(Color.WHITE);

		// Botão para exibir o item selecionado
		JButton btnExibir = new JButton("Corretora selecionada:");
		btnExibir.setBounds(130, 224, 245, 36);
		panel.add(btnExibir);
		btnExibir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExibir.setBackground(new Color(225, 193, 85));
		
		// Evento para o botão (Exibir Seleção)
		btnExibir.addActionListener(e -> {
			corretoraSelecionada = (String) comboBox.getSelectedItem();
			
			if (!corretoraSelecionada.isBlank()) {
				SelecaoHabitacao selecaoHabitacao = new SelecaoHabitacao(clienteRecebido,seguroRecebido,corretoraSelecionada);
				selecaoHabitacao.show();
				frame.dispose();
			} else {
				
				JOptionPane.showMessageDialog(frame, "Escolha uma corretora", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		Connection connection = new ConnectionFactory().conectar();
		CorretoraDAO corretoraDAO = new CorretoraDAO(connection);

		List<Corretora> corretoras = corretoraDAO.selectAll();

		for (Corretora c : corretoras) {
			comboBox.addItem(c.getNomeCorretora());
		}

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		btnVoltar.addActionListener(e -> {
			Simulacao simulacao = new Simulacao();
			simulacao.show();
			frame.dispose();
		});

		btnAvancar.addActionListener(e -> {
			SelecaoHabitacao selecaoHabitacao = new SelecaoHabitacao(clienteRecebido,seguroRecebido,corretoraSelecionada);
			selecaoHabitacao.show();
			frame.dispose();
		});
	}

	public void show() {
		frame.setVisible(true);
	}

}