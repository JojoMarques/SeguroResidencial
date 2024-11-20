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
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.UIManager;

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
		frame.setBounds(400, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Configuração do layout absoluto
		frame.getContentPane().setLayout(null);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(51, 153, 102));
		panelHeader.setBounds(0, 0, 784, 100);
		frame.getContentPane().add(panelHeader);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
        Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
        icon=new ImageIcon(img);
        panelHeader.setLayout(null);
		
		JButton btnLogoTelaInicial = new JButton(icon);
		btnLogoTelaInicial.setBorder(BorderFactory.createEmptyBorder());
        btnLogoTelaInicial.setFocusPainted(false);
		btnLogoTelaInicial.setBackground(new Color(0, 153, 102));
		btnLogoTelaInicial.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogoTelaInicial);
		
		JLabel lblAreaDoFuncionario = new JLabel("Acesso a área ");
		lblAreaDoFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoFuncionario.setForeground(Color.WHITE);
		lblAreaDoFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAreaDoFuncionario.setBackground(Color.WHITE);
		lblAreaDoFuncionario.setBounds(264, 21, 255, 31);
		panelHeader.add(lblAreaDoFuncionario);
		
		JLabel lblAreaDoFuncionario2 = new JLabel("dos funcionários");
		lblAreaDoFuncionario2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAreaDoFuncionario2.setForeground(Color.WHITE);
		lblAreaDoFuncionario2.setBackground(Color.WHITE);
		lblAreaDoFuncionario2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoFuncionario2.setBounds(270, 52, 243, 32);
		panelHeader.add(lblAreaDoFuncionario2);
		
		JPanel panel = new JPanel();
		panel.setBounds(127, 123, 530, 315);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAcesso = new JLabel("Acesso:");
		lblAcesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAcesso.setBounds(86, 112, 80, 25);
		panel.add(lblAcesso);
		
		txtAcesso = new JTextField();
		txtAcesso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAcesso.setColumns(10);
		txtAcesso.setBounds(166, 112, 200, 25);
		panel.add(txtAcesso);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(86, 162, 80, 25);
		panel.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setBounds(166, 162, 200, 25);
		panel.add(txtSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(225, 193, 85));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(166, 212, 200, 30);
		panel.add(btnLogin);
		
		JTextPane txtpnRestritoAosCorretores = new JTextPane();
		txtpnRestritoAosCorretores.setBackground(UIManager.getColor("Button.background"));
		txtpnRestritoAosCorretores.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnRestritoAosCorretores.setText("Restrito aos corretores / colaboradores e parceiros.");
		txtpnRestritoAosCorretores.setBounds(10, 24, 383, 20);
		panel.add(txtpnRestritoAosCorretores);
		
		// Evento para retornar à tela inicial
        btnLogoTelaInicial.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.show();
            frame.dispose();
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
