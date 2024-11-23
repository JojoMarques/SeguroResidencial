package br.com.tokio.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.tokio.view.cliente.LoginCliente;
import br.com.tokio.view.funcionario.LoginFuncionario;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

public class TelaInicial {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela 'tela inicial', que direciona para o login do cliente, 
	 * login do funcionário, duvidas frequentes e cotação/ 'contrate aqui'
	 */
	public TelaInicial() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
        // Criando o botão
        JButton btnDuvidas = new JButton();
        btnDuvidas.setBackground(new Color(50, 171, 159));
        btnDuvidas.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnDuvidas.setText("Dúvidas frequentes");
        btnDuvidas.setBounds(621, 520, 153, 30); 

        // Adiciona o botão ao JFrame
        frame.getContentPane().add(btnDuvidas);


		// Botão "Contrate Aqui"
		JButton btnContrate = new JButton("Contrate Aqui");
		btnContrate.setBackground(new Color(225, 193, 85));
		btnContrate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnContrate.setBounds(303, 448, 178, 50); // Posição e tamanho (x, y, largura, altura)
		frame.getContentPane().add(btnContrate);

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

		// Botão "Área do Cliente"
		JButton btnCliente = new JButton("Área do Cliente");
		btnCliente.setBackground(new Color(225, 193, 85));
		btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCliente.setBounds(462, 30, 151, 40);
		panelHeader.add(btnCliente);

		// Botão "Área do Funcionário"
		JButton btnFuncionario = new JButton("Área do Funcionário");
		btnFuncionario.setBackground(new Color(225, 193, 85));
		btnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFuncionario.setBounds(623, 30, 151, 40);
		panelHeader.add(btnFuncionario);

		JLabel lblSeguro = new JLabel("Seguro Residencial Tokio Marine");
		lblSeguro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguro.setBounds(180, 391, 427, 40);
		frame.getContentPane().add(lblSeguro);
		lblSeguro.setForeground(Color.BLACK);
		lblSeguro.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblSeguro.setBackground(Color.WHITE);

		JLabel lblSlogan = new JLabel("Proteja seu lar, viva tranquilo");
		lblSlogan.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlogan.setBounds(188, 341, 412, 50);
		frame.getContentPane().add(lblSlogan);

		ImageIcon heroIcon = new ImageIcon(getClass().getResource("/resources/images/residencia.png"));
		Image heroImage = heroIcon.getImage().getScaledInstance( 637, 219, Image.SCALE_SMOOTH);
		heroIcon = new ImageIcon(heroImage);
		panelHeader.setLayout(null);
		
		JPanel panelImageHero = new JPanel();
		panelImageHero.setBounds(73, 111, 637, 219);
		frame.getContentPane().add(panelImageHero);
		
		JLabel lblImagemPrincipal = new JLabel(heroIcon);
		panelImageHero.add(lblImagemPrincipal);

		// Evento que direciona para o login do funcionário
		btnFuncionario.addActionListener(e -> {
			LoginFuncionario areaFuncionario = new LoginFuncionario();
			areaFuncionario.show(); 
			frame.dispose();
		});

		// Evento que direciona para o login do cliente
		btnCliente.addActionListener(e -> {
			LoginCliente loginCliente = new LoginCliente();
			loginCliente.show(); 
			frame.dispose();
		});

		// Evento que direciona para o contrate aqui
		btnContrate.addActionListener(e -> {
			Simulacao simulacao = new Simulacao();
			simulacao.show(); 
			
			frame.dispose();
		});
		
		// Evento que direciona para as duvidas frequentes o login do funcionário
		btnDuvidas.addActionListener(e -> {
			
			DuvidasFrequentes duvidasFrequentes = new DuvidasFrequentes();
			duvidasFrequentes.show(); 
			
			frame.dispose();
		});
	}

	
	public void show() {
		frame.setVisible(true);
	}
}
