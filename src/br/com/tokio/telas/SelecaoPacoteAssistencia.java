package br.com.tokio.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelecaoPacoteAssistencia {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				SelecaoPacoteAssistencia window = new SelecaoPacoteAssistencia();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelecaoPacoteAssistencia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Seleção de Pacote");
		frame.setBounds(300, 200, 800, 600); // Tamanho menor
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); // Usando layout nulo para posicionamento manual

		// Título do pacote
		JLabel lblTitle2 = new JLabel("PACOTE BÁSICO", JLabel.CENTER);
		lblTitle2.setBounds(325, 137, 250, 40);
		frame.getContentPane().add(lblTitle2);
		lblTitle2.setFont(new Font("Arial", Font.BOLD, 14));

		// Botão para selecionar
		JButton btnSelect2 = new JButton("ESCOLHER");
		btnSelect2.setBounds(325, 217, 250, 40);
		frame.getContentPane().add(btnSelect2);

		// Preço
		JLabel lblPrice2 = new JLabel("R$ 20,12/mês", JLabel.CENTER);
		lblPrice2.setBounds(325, 177, 250, 40);
		frame.getContentPane().add(lblPrice2);
		lblPrice2.setFont(new Font("Arial", Font.PLAIN, 12));

		// Criando um painel para o pacote
		JPanel panel2 = new JPanel();
		panel2.setBounds(325, 137, 250, 120); // Posição e tamanho do painel
		panel2.setLayout(new GridLayout(3, 1)); // Dividido em 3 linhas
		panel2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel2);

		// Título do pacote
		JLabel lblTitle = new JLabel("PACOTE BÁSICO", JLabel.CENTER);
		lblTitle.setBounds(30, 137, 250, 40);
		frame.getContentPane().add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));

		// Botão para selecionar
		JButton btnSelect = new JButton("ESCOLHER");
		btnSelect.setBounds(30, 217, 250, 40);
		frame.getContentPane().add(btnSelect);

		// Preço
		JLabel lblPrice = new JLabel("R$ 20,12/mês", JLabel.CENTER);
		lblPrice.setBounds(30, 177, 250, 40);
		frame.getContentPane().add(lblPrice);
		lblPrice.setFont(new Font("Arial", Font.PLAIN, 12));

		// Criando um painel para o pacote
		JPanel panel = new JPanel();
		panel.setBounds(30, 137, 250, 120); // Posição e tamanho do painel
		panel.setLayout(new GridLayout(3, 1)); // Dividido em 3 linhas
		panel.setBackground(Color.LIGHT_GRAY);

		// Adicionando o painel ao frame
		frame.getContentPane().add(panel);

	}
}
