package br.com.tokio.telas;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;

public class Simulacao {

	private JFrame frame;
	private JTextField txtCPF;
	private JTextField txtCEP;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtCobertura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Simulacao window = new Simulacao();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

//
	/**
	 * Create the application.
	 */
	public Simulacao() {
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

		JButton btnLogoTelaInicial = new JButton(icon);
		btnLogoTelaInicial.setFocusPainted(false);
		btnLogoTelaInicial.setBorder(BorderFactory.createEmptyBorder());
		btnLogoTelaInicial.setBackground(new Color(0, 153, 102));
		btnLogoTelaInicial.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogoTelaInicial);

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
		panel.setBounds(110, 100, 563, 426);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCPF_1 = new JLabel("Dados para simulação:");
		lblCPF_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCPF_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCPF_1.setBounds(200, 35, 163, 39);
		panel.add(lblCPF_1);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCPF.setBounds(74, 100, 100, 25);
		panel.add(lblCPF);

		txtCPF = new JTextField();
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCPF.setBounds(181, 100, 200, 25);
		panel.add(txtCPF);

		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCEP.setBounds(74, 150, 100, 25);
		panel.add(lblCEP);

		txtCEP = new JTextField();
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCEP.setBounds(181, 150, 200, 25);
		panel.add(txtCEP);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(74, 200, 100, 25);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefone.setBounds(181, 200, 200, 25);
		panel.add(txtTelefone);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(74, 250, 100, 25);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setBounds(181, 250, 200, 25);
		panel.add(txtEmail);

		JLabel lblCobertura = new JLabel("Valor cobertura:");
		lblCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCobertura.setBounds(74, 300, 100, 25);
		panel.add(lblCobertura);

		txtCobertura = new JTextField();
		txtCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCobertura.setBounds(181, 300, 200, 25);
		panel.add(txtCobertura);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(225, 193, 85));
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnviar.setBounds(221, 380, 116, 39);
		panel.add(btnEnviar);

		btnEnviar.addActionListener(e -> {
			String cpf = txtCPF.getText();
			String cep = txtCEP.getText();
			String telefone = txtTelefone.getText();
			String email = txtEmail.getText();
			String cobertura = txtCobertura.getText();

			JOptionPane.showMessageDialog(frame, "Dados enviados com sucesso!\n" + "CPF: " + cpf + "\n" + "CEP: " + cep
					+ "\n" + "Telefone: " + telefone + "\n" + "E-mail: " + email + "\n" + "Cobertura: " + cobertura);
		});
		
		// Evento para retornar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});
		
		btnVoltar.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});
		
		btnAvancar.addActionListener(e -> {
			TelaCorretora telaCorretora = new TelaCorretora();
			telaCorretora.show();
			frame.dispose();
		});
	}

	public void show() {
		frame.setVisible(true);
	}
}
