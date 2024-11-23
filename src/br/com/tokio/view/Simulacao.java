package br.com.tokio.view;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.model.Cliente;
import br.com.tokio.model.Seguro;

public class Simulacao {

	private JFrame frame;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTextField txtCobertura;
	private JTextField txtNome;

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


	/**
	 * Cria a tela 'simulação', a primeira tela do processo de cotação/contratação, onde o usuário insere seus dados pessoais
	 */
	public Simulacao() {
		initialize();
	}

	
	private void initialize() {
		
		Cliente cliente = new Cliente();
		Seguro  seguro = new Seguro();
		
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
		lblCPF.setBounds(76, 145, 100, 25);
		panel.add(lblCPF);

		txtCPF = new JTextField();
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCPF.setBounds(183, 145, 200, 25);
		panel.add(txtCPF);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(76, 189, 100, 25);
		panel.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefone.setBounds(183, 189, 200, 25);
		panel.add(txtTelefone);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(76, 239, 100, 25);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setBounds(183, 239, 200, 25);
		panel.add(txtEmail);

		JLabel lblCobertura = new JLabel("Valor cobertura:");
		lblCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCobertura.setBounds(76, 289, 100, 25);
		panel.add(lblCobertura);

		txtCobertura = new JTextField();
		txtCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCobertura.setBounds(183, 289, 200, 25);
		panel.add(txtCobertura);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(225, 193, 85));
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnviar.setBounds(221, 380, 116, 39);
		panel.add(btnEnviar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(76, 104, 100, 25);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(183, 104, 200, 25);
		panel.add(txtNome);

		btnEnviar.addActionListener(e -> {
			String cpf = txtCPF.getText();
			String nome = txtNome.getText();
			String telefone = txtTelefone.getText();
			String email = txtEmail.getText();
			String cobertura = txtCobertura.getText();
			
			
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			cliente.setTelefone(telefone);
			cliente.setEmail(email);
			seguro.setValorPremio(Float.parseFloat(cobertura));

			JOptionPane.showMessageDialog(frame, "Dados enviados com sucesso!\n"+ "Nome: " + nome + "\n" +  "CPF: " + cpf + "\n" + 
				 "Telefone: " + telefone + "\n" + "E-mail: " + email + "\n" + "Cobertura: " + cobertura);
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
			TelaCorretora telaCorretora = new TelaCorretora(cliente, seguro);
			telaCorretora.show();
			frame.dispose();
		});
	}

	public void show() {
		frame.setVisible(true);
	}
}
