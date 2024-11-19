package br.com.tokio.telas.funcionario;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.tokio.telas.TelaInicial;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;

public class AreaFuncionario {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaFuncionario window = new AreaFuncionario();
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
	public AreaFuncionario() {
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
		
		 // Botão para retornar à tela inicial
        JButton btnTelaInicial = new JButton("Tela inicial");
        btnTelaInicial.setBounds(620, 500, 139, 40);
        frame.getContentPane().add(btnTelaInicial);

        // Evento para voltar à tela inicial
        btnTelaInicial.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.show(); // Mostra a nova tela
            frame.dispose(); // Fecha a tela atual
        });
	}
	
    // vai exibir a tela (é chamado no click da tela inicial) 
	    public void show() {
	        frame.setVisible(true);
	    }	    
}
