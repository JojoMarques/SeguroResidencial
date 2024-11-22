package br.com.tokio.view.cliente;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.SwingConstants;

import br.com.tokio.view.SelecaoHabitacao;
import br.com.tokio.view.TelaInicial;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

public class AreaCliente {

	private JFrame frame;
	private JTextField textFieldValor;
	private JTextField textFieldData;
	private JTextField textField;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaCliente window = new AreaCliente();
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
	public AreaCliente(int idCliente) {
		this.idRecebido = idCliente;
		initialize();
	}
	
	public AreaCliente() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println(idRecebido);
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

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		// Painel para as informações do cliente
		JPanel panelInformacoes = new JPanel();
		panelInformacoes.setBounds(391, 139, 393, 344); // Posição e tamanho do painel
		panelInformacoes.setLayout(null); // Layout absoluto dentro do painel
		frame.getContentPane().add(panelInformacoes);

		// Botão de editar informações
		JButton btnInformacoes = new JButton("Suas informações");
		btnInformacoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInformacoes.setBackground(new Color(225, 193, 85));
		btnInformacoes.setBounds(96, 249, 200, 30); // Posição no painel
		panelInformacoes.add(btnInformacoes);
		
		JButton btnApolice = new JButton("Sua apólice");
		btnApolice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnApolice.setBackground(new Color(225, 193, 85));
		btnApolice.setBounds(96, 156, 200, 30);
		panelInformacoes.add(btnApolice);
		
		JButton btnSinistro = new JButton("Solicitar sinistro");
		btnSinistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSinistro.setBackground(new Color(225, 193, 85));
		btnSinistro.setBounds(96, 63, 200, 30);
		panelInformacoes.add(btnSinistro);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 139, 393, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProximoPagto = new JLabel("Próximo Pagamento:");
		lblProximoPagto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProximoPagto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProximoPagto.setBounds(98, 34, 196, 14);
		panel.add(lblProximoPagto);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblData.setBounds(42, 102, 46, 14);
		panel.add(lblData);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValor.setBounds(42, 154, 46, 14);
		panel.add(lblValor);
		
		textFieldValor = new JTextField();
		textFieldValor.setBackground(SystemColor.control);
		textFieldValor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldValor.setBounds(98, 146, 196, 30);
		panel.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		textFieldData = new JTextField();
		textFieldData.setBackground(SystemColor.control);
		textFieldData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldData.setColumns(10);
		textFieldData.setBounds(98, 94, 196, 30);
		panel.add(textFieldData);
		
		JLabel lblPlano = new JLabel("Seu plano é válido até:");
		lblPlano.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPlano.setBounds(21, 265, 153, 14);
		panel.add(lblPlano);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBackground(SystemColor.control);
		textField.setBounds(173, 257, 196, 30);
		panel.add(textField);

		// Evento para abrir a tela EditarCliente
		btnInformacoes.addActionListener(e -> {
			InformacoesCliente informacoesCliente = new InformacoesCliente(idRecebido);
			informacoesCliente.show(); // Mostra a tela de edição
			frame.dispose(); // Fecha a tela atual
		});
		
		btnSinistro.addActionListener(e -> {
			SolicitarSinistro solicitarSinistro = new SolicitarSinistro();
			solicitarSinistro.show();
			frame.dispose();
		});
		
		btnApolice.addActionListener(e -> {
			VisualizarApolice visualizarApolice = new VisualizarApolice();
			visualizarApolice.show();
			frame.dispose();
		});
		
		

	}

	/**
	 * Método para exibir a tela.
	 */public void show() {
			frame.setVisible(true);
		}
	 
}