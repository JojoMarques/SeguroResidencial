package br.com.tokio.telas;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ContrateAqui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContrateAqui window = new ContrateAqui();
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
	public ContrateAqui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnTelaInicial = new JButton("Tela inicial");
		btnTelaInicial.setBounds(620, 500, 139, 40); // Posição e tamanho (x, y, largura, altura)
		frame.getContentPane().add(btnTelaInicial);

		// direciona para a tela inicial
		btnTelaInicial.addActionListener(e -> {
			// Abre a tela AreaCliente
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a nova tela

			// Fecha a tela atual (opcional)
			frame.dispose();
		});

		JButton btnSimulacao = new JButton("Simulação");
		btnSimulacao.setBounds(328, 253, 139, 40); // Posição e tamanho (x, y, largura, altura)
		frame.getContentPane().add(btnSimulacao);

		// direciona para a tela inicial
		btnSimulacao.addActionListener(e -> {
		// Abre a tela AreaCliente
		Simulacao simulacao = new Simulacao();
		simulacao.show(); // Mostra a nova tela

			// Fecha a tela atual (opcional)
			frame.dispose();
		});
	}

	// vai exibir a tela (é chamado no click da tela inicial)
	public void show() {
		frame.setVisible(true);
	}

}
