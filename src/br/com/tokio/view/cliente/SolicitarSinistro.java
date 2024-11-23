package br.com.tokio.view.cliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.SinistroDAO;
import br.com.tokio.model.Sinistro;
import br.com.tokio.view.TelaInicial;

import javax.swing.JTextArea;

public class SolicitarSinistro {

	private JFrame frame;
	private JTextField txtTipo;
	private JTextField txtData;
	private JTextArea txtDescricao;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitarSinistro window = new SolicitarSinistro();
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
	public SolicitarSinistro() {
		initialize();
	}
	
	public SolicitarSinistro(int idCliente) {
		this.idRecebido = idCliente;
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
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
		btnLogoTelaInicial.setFocusPainted(false);
		btnLogoTelaInicial.setBorder(BorderFactory.createEmptyBorder());
		btnLogoTelaInicial.setBackground(new Color(0, 153, 102));
		btnLogoTelaInicial.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogoTelaInicial);

		JLabel lblCotacao = new JLabel("Solicitar Sinistro");
		lblCotacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblCotacao.setForeground(Color.WHITE);
		lblCotacao.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCotacao.setBackground(Color.WHITE);
		lblCotacao.setBounds(280, 34, 224, 31);
		panelHeader.add(lblCotacao);

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

		// -----------------

		JPanel panel = new JPanel();
		panel.setBounds(110, 100, 563, 426);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblDadosSimulacao = new JLabel("Dados para simulação:");
		lblDadosSimulacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosSimulacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDadosSimulacao.setBounds(200, 35, 163, 39);
		panel.add(lblDadosSimulacao);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(74, 100, 100, 25);
		panel.add(lblTipo);

		txtTipo = new JTextField();
		txtTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTipo.setBounds(181, 100, 200, 25);
		panel.add(txtTipo);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblData.setBounds(74, 150, 100, 25);
		panel.add(lblData);

		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtData.setBounds(181, 150, 200, 25);
		panel.add(txtData);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescricao.setBounds(74, 200, 100, 25);
		panel.add(lblDescricao);

		txtDescricao = new JTextArea();
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescricao.setBounds(181, 200, 200, 97);
		panel.add(txtDescricao);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(225, 193, 85));
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnviar.setBounds(223, 341, 116, 39);
		panel.add(btnEnviar);

		btnEnviar.addActionListener(e -> {
			String tipo = txtTipo.getText();
			String data = txtData.getText();
			String descricao = txtDescricao.getText();

//			JOptionPane.showMessageDialog(frame, "Dados enviados com sucesso!\n" + "tipo: " + tipo + "\n" + "data: "
//					+ data + "\n" + "descricao: " + descricao);

			Connection connection = new ConnectionFactory().conectar();

			SinistroDAO sinistroDAO = new SinistroDAO(connection);

			Sinistro sinistro = new Sinistro(tipo,Date.valueOf(data),descricao,false, 1, 1);
			sinistroDAO.insert(sinistro);

			AreaCliente areaCliente = new AreaCliente();
			areaCliente.show();
			frame.dispose();
		});

		// Evento para retornar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

		btnVoltar.addActionListener(e -> {
			AreaCliente areaCliente = new AreaCliente();
			areaCliente.show();
			frame.dispose();
		});

	}

	public void show() {
		frame.setVisible(true);
	}
}
