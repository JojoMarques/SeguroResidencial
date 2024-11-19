package br.com.tokio.telas.funcionario;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.FuncionarioDAO;
import br.com.tokio.telas.TelaInicial;

public class LoginFuncionario {

	private JFrame frame;
	private JTextField txtAcesso;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFuncionario window = new LoginFuncionario();
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
	public LoginFuncionario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Configuração do layout absoluto
		frame.getContentPane().setLayout(null);

		// Label para Acesso
		JLabel lblAcesso = new JLabel("Acesso:");
		lblAcesso.setBounds(200, 200, 80, 25); // Posição e tamanho (x, y, largura, altura)
		frame.getContentPane().add(lblAcesso);

		// Campo de texto para Acesso
		txtAcesso = new JTextField();
		txtAcesso.setBounds(300, 200, 200, 25); // Posição e tamanho
		frame.getContentPane().add(txtAcesso);
		txtAcesso.setColumns(10);

		// Label para Senha
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(200, 250, 80, 25); // Posição e tamanho
		frame.getContentPane().add(lblSenha);

		// Campo de texto para Senha
		txtSenha = new JPasswordField();
		txtSenha.setBounds(300, 250, 200, 25); // Posição e tamanho
		frame.getContentPane().add(txtSenha);

		// Botão de Login
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(300, 300, 200, 30); // Posição e tamanho
		frame.getContentPane().add(btnLogin);

		// Botão para retornar à Tela Inicial
		JButton btnTelaInicial = new JButton("Tela inicial");
		btnTelaInicial.setBounds(620, 500, 139, 40); // Posição e tamanho
		frame.getContentPane().add(btnTelaInicial);

		// Evento para voltar à tela inicial
		btnTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a tela inicial
			frame.dispose(); // Fecha a tela atual
		});

		btnLogin.addActionListener(e -> {
			String acesso = txtAcesso.getText();
			String senha = new String(txtSenha.getPassword());

			// Instanciando a conexão
			Connection connection = new ConnectionFactory().conectar();

			// Instanciar o ClienteDAO e verificar a autenticação
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
			int resultadoAutenticacao = funcionarioDAO.autenticacao(acesso, senha);

			if (resultadoAutenticacao == 1) {
				// Se CPF e Senha estiverem corretos, direciona para a próxima tela
				AreaFuncionario areaFuncionario = new AreaFuncionario();
				areaFuncionario.show(); // Mostra a nova tela
				frame.dispose(); // Fecha a tela atual
			} else if (resultadoAutenticacao == 2)
				JOptionPane.showMessageDialog(frame, "Senha incorreta!", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			else if (resultadoAutenticacao == 3)
				JOptionPane.showMessageDialog(frame, "Acesso incorreto!", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(frame, "Acesso e Senha incorretos!", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
		});
	}

	public void show() {
		frame.setVisible(true);
	}
}
