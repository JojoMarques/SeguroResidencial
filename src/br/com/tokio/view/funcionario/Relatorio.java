package br.com.tokio.view.funcionario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.tokio.view.TelaInicial;

import javax.swing.JTable;

public class Relatorio {

	private JFrame frame;
	private JTable table;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio window = new Relatorio();
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
	public Relatorio() {
		initialize();
	}

	public Relatorio(int idCliente) {
    	this.idRecebido = idCliente;
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
		panelHeader.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
		Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		JButton btnLogoTelaInicial = new JButton(icon);
		btnLogoTelaInicial.setBorder(BorderFactory.createEmptyBorder());
		btnLogoTelaInicial.setFocusPainted(false);
		btnLogoTelaInicial.setBackground(new Color(0, 153, 102));
		btnLogoTelaInicial.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogoTelaInicial);

		JLabel lblRelatorio = new JLabel("Relatório");
		lblRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorio.setForeground(Color.WHITE);
		lblRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblRelatorio.setBackground(Color.WHITE);
		lblRelatorio.setBounds(266, 18, 251, 63);
		panelHeader.add(lblRelatorio);

		// Evento para voltar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a nova tela
			frame.dispose(); // Fecha a tela atual
		});

		JPanel panel = new JPanel();
		panel.setBounds(132, 111, 551, 439);
		frame.getContentPane().add(panel);

		table = new JTable();
		panel.add(table);

	}

	// vai exibir a tela (é chamado no click da tela inicial)
	public void show() {
		frame.setVisible(true);
	}
}
