package br.com.tokio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ImovelDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Estado;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.Seguro;
import br.com.tokio.view.cliente.LoginCliente;

public class InserirImovel {

	private JFrame frame;
	private JTextField txtArea;
	private JTextField txtValorImovel;
	private JTextField txtCEP;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JTextField txtBairro;

	Cliente clienteRecebido;
	Seguro seguroRecebido;
	String corretoraRecebida;
	String habitacaoRecebida;
	int pacoteCoberturaSelecionada;
	int pacoteAssistenciaSelecionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirImovel window = new InserirImovel();
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
	public InserirImovel() {
		initialize();
	}

	public InserirImovel(Cliente cliente, Seguro seguro, String corretora, String habitacao, int pacoteCobertura,
			int pacoteAssistencia) {
		this.clienteRecebido = cliente;
		this.seguroRecebido = seguro;
		this.corretoraRecebida = corretora;
		this.habitacaoRecebida = habitacao;
		this.pacoteCoberturaSelecionada = pacoteCobertura;
		this.pacoteAssistenciaSelecionada = pacoteAssistencia;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	   
		Connection connection = new ConnectionFactory().conectar();
	    ImovelDAO imovelDAO = new ImovelDAO(connection);
	    
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

		JLabel lblImovelDados = new JLabel("Dados do imóvel");
		lblImovelDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblImovelDados.setForeground(Color.WHITE);
		lblImovelDados.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblImovelDados.setBackground(Color.WHITE);
		lblImovelDados.setBounds(280, 34, 224, 31);
		panelHeader.add(lblImovelDados);

		JPanel panelNav = new JPanel();
		panelNav.setBackground(new Color(0, 153, 102));
		panelNav.setBounds(651, 30, 109, 39);
		panelHeader.add(panelNav);
		panelNav.setLayout(new GridLayout(0, 2, 10, 0));

		JPanel panel = new JPanel();
		panel.setBounds(110, 100, 563, 426);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblImovel = new JLabel("Insira os dados do seu imóvel");
		lblImovel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImovel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImovel.setBounds(156, 11, 251, 39);
		panel.add(lblImovel);

		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCEP.setBounds(63, 61, 100, 25);
		panel.add(lblCEP);

		txtCEP = new JTextField();
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCEP.setBounds(173, 61, 200, 25);
		panel.add(txtCEP);

		JLabel lblLogradouro = new JLabel("Logradouro: ");
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogradouro.setBounds(63, 97, 100, 25);
		panel.add(lblLogradouro);

		JLabel lblNumero = new JLabel("Número: ");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumero.setBounds(63, 133, 100, 25);
		panel.add(lblNumero);

		txtLogradouro = new JTextField();
		txtLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogradouro.setBounds(173, 97, 200, 25);
		panel.add(txtLogradouro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCidade.setBounds(63, 207, 100, 25);
		panel.add(lblCidade);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNumero.setBounds(173, 133, 200, 25);
		panel.add(txtNumero);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstado.setBounds(63, 246, 100, 25);
		panel.add(lblEstado);

		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCidade.setBounds(173, 207, 200, 25);
		panel.add(txtCidade);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(225, 193, 85));
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnviar.setBounds(221, 380, 116, 39);
		panel.add(btnEnviar);

		JLabel lblArea = new JLabel("Área do imóvel");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblArea.setBounds(63, 282, 100, 25);
		panel.add(lblArea);

		txtArea = new JTextField();
		txtArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtArea.setBounds(173, 282, 200, 25);
		panel.add(txtArea);

		JLabel lblValorImovel = new JLabel("Valor do imóvel");
		lblValorImovel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValorImovel.setBounds(63, 324, 100, 25);
		panel.add(lblValorImovel);

		txtValorImovel = new JTextField();
		txtValorImovel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtValorImovel.setBounds(173, 324, 200, 25);
		panel.add(txtValorImovel);

		JLabel lblBairro = new JLabel("Bairro: ");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBairro.setBounds(63, 169, 100, 25);
		panel.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtBairro.setBounds(173, 169, 200, 25);
		panel.add(txtBairro);

		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(173, 246, 200, 22);
		panel.add(comboBoxEstado);

		for (Estado estado : Estado.values()) {
			comboBoxEstado.addItem(estado.getSigla()); // Adiciona a sigla de cada estado no ComboBox
		}

		btnEnviar.addActionListener(e -> {
			String cep = txtCEP.getText();
			String logradouro = txtLogradouro.getText();
			String numero = txtNumero.getText();
			String cidade = txtCidade.getText();
			String bairro = txtBairro.getText();
			String areaImovel = txtArea.getText();
			String valorImovel = txtValorImovel.getText();

			String siglaSelecionada = (String) comboBoxEstado.getSelectedItem();
			Estado estadoSelecionado = Estado.fromCodigo(siglaSelecionada);

			String nomeEstado = estadoSelecionado.name();

//			JOptionPane.showMessageDialog(frame,
//					"Dados enviados com sucesso!\n" + "CEP: " + cep + "\n" + "Logradouro: " + logradouro + "\n"
//							+ "Número " + numero + "\n" + "Cidade: " + cidade + "\n" + "Estado: " + estado + "\n"
//							+ "Área do imóvel: " + areaImovel + "\n" + "Valor imóvel: " + valorImovel);
	


			// verifica se todos tem valor.
			if (!cep.isEmpty() && !logradouro.isEmpty() && !numero.isEmpty() && !cidade.isEmpty() && !bairro.isEmpty()
					&& !areaImovel.isEmpty() && !valorImovel.isEmpty() && !nomeEstado.isEmpty()) {

				int num = Integer.parseInt(numero);
				double area = Double.parseDouble(areaImovel);
				double valor = Double.parseDouble(valorImovel);

				System.out.println(clienteRecebido.getIdUsuario() + "-" + clienteRecebido.getNome());
				
				Imovel imovel = new Imovel();
				//Imovel imovel = new Imovel(valor, area, "Brasil", nomeEstado, cidade, bairro, logradouro, num, cep,clienteRecebido.getIdCliente() );
				imovel.setValorImovel(valor);
				imovel.setArea(area);
				imovel.setPais("Brasil");
				imovel.setEstado(nomeEstado);
				imovel.setCidade(cidade);
				imovel.setBairro(bairro);
				imovel.setLogradouro(logradouro);
				imovel.setNumero(num);
				imovel.setCep(cep);
				System.out.println("id cliente aq na inserir imovel: "+clienteRecebido.getIdCliente());
				System.out.println(imovel.getValorImovel());
				System.out.println(imovel.getIdCliente());
				imovel.setIdCliente(clienteRecebido.getIdCliente());
				
				imovelDAO.insert(imovel);

				LoginCliente loginCliente = new LoginCliente();
				loginCliente.show();
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "Preencha todos os campos", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		// Evento para retornar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

	}

	public void show() {
		frame.setVisible(true);
	}
}
