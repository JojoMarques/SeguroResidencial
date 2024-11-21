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

import br.com.tokio.view.cliente.EditarCliente;
import br.com.tokio.view.cliente.LoginCliente;

import javax.swing.JPasswordField;

public class ConfirmarDados {

	private JFrame frame;

	private JTextField txtEmailtestegmailcom;
	private JTextField textField_1;
	private JPasswordField password1;
	private JPasswordField passwordConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmarDados window = new ConfirmarDados();
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
	public ConfirmarDados() {
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
		panelHeader.setBackground(new Color(51, 153, 102));
		panelHeader.setBounds(0, 0, 784, 100);
		frame.getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
		Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		panelHeader.setLayout(null);

		JButton btnLogo = new JButton(icon);
		btnLogo.setBorder(BorderFactory.createEmptyBorder());
		btnLogo.setFocusPainted(false);
		btnLogo.setBounds(5, 15, 243, 69);
		btnLogo.setBackground(new Color(0, 153, 102));
		panelHeader.add(btnLogo);

		JLabel lblAreaDoCliente = new JLabel("Confirmar dados");
		lblAreaDoCliente.setForeground(new Color(255, 255, 255));
		lblAreaDoCliente.setBackground(new Color(255, 255, 255));
		lblAreaDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblAreaDoCliente.setBounds(279, 15, 226, 69);
		panelHeader.add(lblAreaDoCliente);

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

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

		// Painel para as informações do cliente
		JPanel panelInformacoes = new JPanel();
		panelInformacoes.setBounds(150, 100, 500, 435); // Posição e tamanho do painel
		panelInformacoes.setLayout(null); // Layout absoluto dentro do painel
		frame.getContentPane().add(panelInformacoes);

		// Título do painel
		JLabel lblTitulo = new JLabel("Confirme seus dados:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(150, 35, 200, 30); // Posição no painel
		panelInformacoes.add(lblTitulo);

		// Campo de exibição de nome
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(38, 96, 80, 25); // Posição no painel
		panelInformacoes.add(lblNome);

		JTextField txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(150, 96, 200, 25); // Posição no painel
		txtNome.setEditable(false); // Campo apenas para exibição
		txtNome.setText("Nome do Cliente"); // Valor de exemplo
		panelInformacoes.add(txtNome);

		// Campo de exibição de CPF
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpf.setBounds(38, 136, 80, 25); // Posição no painel
		panelInformacoes.add(lblCpf);

		JTextField txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpf.setBounds(150, 136, 200, 25); // Posição no painel
		txtCpf.setEditable(false); // Campo apenas para exibição
		txtCpf.setText("123.456.789-00"); // Valor de exemplo
		panelInformacoes.add(txtCpf);

		// Botão de editar informações
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBackground(new Color(225, 193, 85));
		btnConfirmar.setBounds(150, 377, 200, 30); // Posição no painel
		panelInformacoes.add(btnConfirmar);

		txtEmailtestegmailcom = new JTextField();
		txtEmailtestegmailcom.setText("email.teste@gmail.com");
		txtEmailtestegmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmailtestegmailcom.setEditable(false);
		txtEmailtestegmailcom.setBounds(150, 172, 200, 25);
		panelInformacoes.add(txtEmailtestegmailcom);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(38, 172, 80, 25);
		panelInformacoes.add(lblEmail);

		textField_1 = new JTextField();
		textField_1.setText("(11) 9 9546-4421");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setEditable(false);
		textField_1.setBounds(150, 208, 200, 25);
		panelInformacoes.add(textField_1);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(38, 208, 80, 25);
		panelInformacoes.add(lblTelefone);

		JLabel lblNewLabel_1 = new JLabel("Crie uma senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 288, 130, 18);
		panelInformacoes.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Confirme sua senha:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 326, 141, 14);
		panelInformacoes.add(lblNewLabel_1_1);

		password1 = new JPasswordField();
		password1.setBounds(150, 283, 200, 25);
		panelInformacoes.add(password1);

		passwordConfirmar = new JPasswordField();
		passwordConfirmar.setBounds(150, 322, 200, 25);
		panelInformacoes.add(passwordConfirmar);
		
		// Evento para abrir a tela EditarCliente
		btnConfirmar.addActionListener(e -> {
			if(confirmarSenha()) {
				InserirImovel inserirImovel = new InserirImovel();
				inserirImovel.show(); // Mostra a tela de edição
				frame.dispose(); // Fecha a tela atual
			}
			else {
				JOptionPane.showMessageDialog(frame, "As senhas estão diferentes", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		btnVoltar.addActionListener(e -> {
			SelecaoPacoteAssistencia selecaoPacoteAssistencia = new SelecaoPacoteAssistencia();
			selecaoPacoteAssistencia.show();
			frame.dispose();
		});

	}

	public boolean confirmarSenha() {
		String senha1 = new String(password1.getPassword());
		String senha2 = new String(passwordConfirmar.getPassword());
		if (senha1.equals(senha2))
			return true;
		else
			return false;

	}

	public void show() {
		frame.setVisible(true);
	}
}
