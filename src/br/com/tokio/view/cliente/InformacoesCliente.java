package br.com.tokio.view.cliente;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.view.TelaInicial;

import java.awt.Font;
import java.awt.GridLayout;

public class InformacoesCliente {

	private JFrame frame;
	private JTextField txtEmailtestegmailcom;
	private JTextField textField_1;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformacoesCliente window = new InformacoesCliente();
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
	public InformacoesCliente() {
		initialize();
	}
	
	public InformacoesCliente(int idCliente) {
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

		JLabel lblAreaDoCliente = new JLabel("Área do Cliente");
		lblAreaDoCliente.setForeground(new Color(255, 255, 255));
		lblAreaDoCliente.setBackground(new Color(255, 255, 255));
		lblAreaDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblAreaDoCliente.setBounds(279, 15, 226, 69);
		panelHeader.add(lblAreaDoCliente);
		
		ImageIcon iconLeft = new ImageIcon(getClass().getResource("/resources/images/chevron_left.png"));
		Image imgLeft = iconLeft.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		iconLeft = new ImageIcon(imgLeft);

		JPanel panelNav = new JPanel();
		panelNav.setBackground(new Color(0, 153, 102));
		panelNav.setBounds(651, 30, 109, 39);
		panelHeader.add(panelNav);
		panelNav.setLayout(new GridLayout(0, 2, 10, 0));
		
		// volta para a pagina anterior
		JButton btnVoltar = new JButton(iconLeft);
		btnVoltar.setBackground(new Color(225, 193, 85));
		panelNav.add(btnVoltar);

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		// Painel para as informações do cliente
		JPanel panelInformacoes = new JPanel();
		panelInformacoes.setBounds(150, 100, 500, 344); // Posição e tamanho do painel
		panelInformacoes.setLayout(null); // Layout absoluto dentro do painel
		frame.getContentPane().add(panelInformacoes);

		Connection connection = new ConnectionFactory().conectar();
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		Cliente cliente = new Cliente();
		cliente = clienteDAO.selectById(idRecebido);
		
		// Título do painel
		JLabel lblTitulo = new JLabel("Informações do Cliente");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(150, 35, 200, 30); // Posição no painel
		panelInformacoes.add(lblTitulo);

		// Campo de exibição de nome
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(50, 96, 80, 25); // Posição no painel
		panelInformacoes.add(lblNome);

		JTextField txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(150, 96, 200, 25); // Posição no painel
		txtNome.setEditable(false); // Campo apenas para exibição
		txtNome.setText(cliente.getNome()); // Valor de exemplo
		panelInformacoes.add(txtNome);

		// Campo de exibição de CPF
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpf.setBounds(50, 136, 80, 25); // Posição no painel
		panelInformacoes.add(lblCpf);

		JTextField txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpf.setBounds(150, 136, 200, 25); // Posição no painel
		txtCpf.setEditable(false); // Campo apenas para exibição
		txtCpf.setText(cliente.getCpf()); // Valor de exemplo
		panelInformacoes.add(txtCpf);

		// Botão de editar informações
		JButton btnEditarInformacoes = new JButton("Editar Informações");
		btnEditarInformacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditarInformacoes.setBackground(new Color(225, 193, 85));
		btnEditarInformacoes.setBounds(150, 275, 200, 30); // Posição no painel
		panelInformacoes.add(btnEditarInformacoes);
		
		txtEmailtestegmailcom = new JTextField();
		txtEmailtestegmailcom.setText(cliente.getEmail());
		txtEmailtestegmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmailtestegmailcom.setEditable(false);
		txtEmailtestegmailcom.setBounds(150, 172, 200, 25);
		panelInformacoes.add(txtEmailtestegmailcom);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(50, 172, 80, 25);
		panelInformacoes.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setText(cliente.getTelefone());
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setEditable(false);
		textField_1.setBounds(150, 208, 200, 25);
		panelInformacoes.add(textField_1);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(50, 208, 80, 25);
		panelInformacoes.add(lblTelefone);

		// Evento para abrir a tela EditarCliente
		btnEditarInformacoes.addActionListener(e -> {
			EditarCliente editarCliente = new EditarCliente(idRecebido);
			editarCliente.show(); // Mostra a tela de edição
			frame.dispose(); // Fecha a tela atual
		});

		btnVoltar.addActionListener(e -> {
			AreaCliente areaCliente = new AreaCliente(idRecebido);
			areaCliente.show();
			frame.dispose();
		});
		
	}

	/**
	 * Método para exibir a tela.
	 */
	public void show() {
		frame.setVisible(true);
	}
}
