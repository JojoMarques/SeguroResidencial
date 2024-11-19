package br.com.tokio.telas;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.CorretoraDAO;
import br.com.tokio.model.Corretora;

public class TelaCorretora {

	private JFrame frame;
	private JComboBox<String> comboBox; // Caixa de seleção (JComboBox)

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCorretora window = new TelaCorretora();
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
	public TelaCorretora() {
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
		JLabel lblSelecione = new JLabel("Selecione uma corretora:");
		lblSelecione.setBounds(319, 149, 183, 30);
		frame.getContentPane().add(lblSelecione);

		// Criando a ComboBox (caixa de seleção)
		comboBox = new JComboBox<>();
		comboBox.setBounds(241, 220, 300, 25);
		frame.getContentPane().add(comboBox);

		// Botão para exibir o item selecionado
		JButton btnExibir = new JButton("Corretora selecionada:");
		btnExibir.setBounds(319, 287, 150, 30);
		frame.getContentPane().add(btnExibir);

		// Evento para o botão (Exibir Seleção)
		btnExibir.addActionListener(e -> {
			String selecionado = (String) comboBox.getSelectedItem();
			JOptionPane.showMessageDialog(frame, "Você selecionou: " + selecionado);
		});

		Connection connection = new ConnectionFactory().conectar();
		CorretoraDAO corretoraDAO = new CorretoraDAO(connection);

		List<Corretora> corretoras = corretoraDAO.selectAll();

		for (Corretora c : corretoras) {
			comboBox.addItem(c.getNomeCorretora());
		}

		// Botão para retornar à Tela Inicial
		JButton btnTelaInicial = new JButton("Tela inicial");
		btnTelaInicial.setBounds(620, 500, 139, 40); // Posição e tamanho
		frame.getContentPane().add(btnTelaInicial);

		// Evento para voltar à tela inicial
		btnTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a tela inicial
			frame.dispose(); // Fecha a tela atual
		});
		
		
	}

}