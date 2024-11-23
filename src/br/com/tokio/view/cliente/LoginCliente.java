package br.com.tokio.view.cliente;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.view.TelaInicial;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

public class LoginCliente {

	private JFrame frame;
	private JTextField txtCpf;
	private JPasswordField txtSenha;
	int idClienteRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCliente window = new LoginCliente();
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
	public LoginCliente() {
		initialize();
	}

	public LoginCliente(int idCliente) {
		this.idClienteRecebido = idCliente;
		initialize();
	}

	/**
	 * Inicializa a tela 'login cliente' onde um usuário acessar a área do cliente com CPF e senha
	 */
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

		JButton btnLogoTelaInicial = new JButton(icon);
		btnLogoTelaInicial.setBorder(BorderFactory.createEmptyBorder());
		btnLogoTelaInicial.setFocusPainted(false);
		btnLogoTelaInicial.setBackground(new Color(0, 153, 102));
		btnLogoTelaInicial.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogoTelaInicial);

		JLabel lblAreaDoCliente = new JLabel("Acesso a área");
		lblAreaDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoCliente.setForeground(Color.WHITE);
		lblAreaDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAreaDoCliente.setBackground(Color.WHITE);
		lblAreaDoCliente.setBounds(280, 21, 224, 31);
		panelHeader.add(lblAreaDoCliente);

		JLabel lblAreaDoCliente2 = new JLabel("dos clientes");
		lblAreaDoCliente2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoCliente2.setForeground(Color.WHITE);
		lblAreaDoCliente2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAreaDoCliente2.setBackground(Color.WHITE);
		lblAreaDoCliente2.setBounds(283, 52, 218, 32);
		panelHeader.add(lblAreaDoCliente2);

		JPanel panel = new JPanel();
		panel.setBounds(130, 136, 498, 335);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpf.setBounds(70, 86, 80, 25);
		panel.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpf.setColumns(10);
		txtCpf.setBounds(149, 86, 200, 25);
		panel.add(txtCpf);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(70, 136, 80, 25);
		panel.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setBounds(149, 136, 200, 25);
		panel.add(txtSenha);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(225, 193, 85));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(149, 186, 200, 30);
		panel.add(btnLogin);

		// Evento para voltar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a nova tela
			frame.dispose(); // Fecha a tela atual
		});

		// Evento de clique no botão de login
		btnLogin.addActionListener(e -> {
			String cpf = txtCpf.getText();
			String senha = new String(txtSenha.getPassword());

			// Instanciando a conexão
			Connection connection = new ConnectionFactory().conectar();
			
			// Instanciar o ClienteDAO e verificar a autenticação
			ClienteDAO clienteDAO = new ClienteDAO(connection);
			List<Integer> resultadoAutenticacao = clienteDAO.autenticacao(cpf, senha);

			int resultado = resultadoAutenticacao.get(0);
			
			if (resultado == 1) {
				AreaCliente areaCliente = new AreaCliente(resultadoAutenticacao.get(1));
				System.out.println("lixo: " + resultadoAutenticacao.get(0));
				areaCliente.show(); // Mostra a nova tela
				frame.dispose();
			} else if (resultado == 2) {
				JOptionPane.showMessageDialog(frame, "Dados incorretos", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(frame, "Dados inválidos", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			
		});

	}

	public void show() {
		frame.setVisible(true);
	}

}
