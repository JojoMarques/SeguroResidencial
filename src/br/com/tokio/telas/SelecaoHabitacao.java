package br.com.tokio.telas;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SelecaoHabitacao {

	private JFrame frame;
	private JComboBox<String> comboBox; // Caixa de seleção (JComboBox)

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecaoHabitacao window = new SelecaoHabitacao();
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
	public SelecaoHabitacao() {
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
		
		// Adicionando um JLabel para instrução
		JLabel lblSelecione = new JLabel("Selecione um tipo de habitação:");
		lblSelecione.setBounds(241, 179, 185, 30);
		frame.getContentPane().add(lblSelecione);

		// Criando a ComboBox (caixa de seleção)
		comboBox = new JComboBox<>();
		comboBox.setBounds(241, 220, 300, 25);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Veraneiro");
		comboBox.addItem("Moradia");	
	}

}
