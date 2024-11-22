package br.com.tokio.view.funcionario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import br.com.tokio.view.TelaInicial;

public class AreaFuncionario {

	private JFrame frame;
	private int idRecebido;

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
	
	public AreaFuncionario(int idFuncionario) {
		this.idRecebido = idFuncionario;
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
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(51, 153, 102));
		panelHeader.setBounds(0, 0, 784, 100);
		frame.getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
		Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);

		JButton btnLogoTelaInicial = new JButton(icon);
		btnLogoTelaInicial.setBorder(BorderFactory.createEmptyBorder());
		btnLogoTelaInicial.setFocusPainted(false);
		btnLogoTelaInicial.setBackground(new Color(0, 153, 102));
		btnLogoTelaInicial.setBounds(5, 15, 243, 69);
		panelHeader.add(btnLogoTelaInicial);

		JLabel lblAreaFuncionario = new JLabel("Área do Funcionário");
		lblAreaFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaFuncionario.setForeground(Color.WHITE);
		lblAreaFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblAreaFuncionario.setBackground(Color.WHITE);
		lblAreaFuncionario.setBounds(248, 18, 287, 63);
		panelHeader.add(lblAreaFuncionario);

		JPanel panel = new JPanel();
		panel.setBounds(0, 98, 784, 463);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Botão para gerar Relatório
		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.setBackground(new Color(225, 186, 83));
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGerarRelatorio.setBounds(553, 395, 200, 40);
		panel.add(btnGerarRelatorio);
		

		JLabel lblRelatorio = new JLabel("Gerar relatório:");
		lblRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatorio.setBounds(49, 11, 200, 25);
		panel.add(lblRelatorio);

		DefaultListModel<String> listModelTabelas = new DefaultListModel<>();

		JPanel panelSelecionarTabelas = new JPanel();
		panelSelecionarTabelas.setBounds(49, 60, 285, 126);
		panel.add(panelSelecionarTabelas);
		panelSelecionarTabelas.setLayout(null);

		JLabel lblTabelas = new JLabel("Tabelas - pode-se selecionar múltiplas:");
		lblTabelas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTabelas.setBounds(14, 0, 294, 14);
		panelSelecionarTabelas.add(lblTabelas);
		// aqui colocamos as tabelas respectivas

		listModelTabelas.addElement("Campo 1");
		listModelTabelas.addElement("Campo 2");
		listModelTabelas.addElement("Campo 3");
		listModelTabelas.addElement("Campo 4");
		listModelTabelas.addElement("Campo 5");

		JList<String> listTabelas = new JList<>(listModelTabelas);
		listTabelas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listTabelas.setBounds(238, 85, 200, 100);

		JScrollPane scrollPaneTabelas = new JScrollPane(listTabelas);
		scrollPaneTabelas.setBounds(14, 26, 200, 100);
		panelSelecionarTabelas.add(scrollPaneTabelas);

		// -----------------------------------------------

		DefaultListModel<String> listModelCampos = new DefaultListModel<>();

		JPanel panelSelecionarCampos = new JPanel();
		panelSelecionarCampos.setBounds(49, 254, 285, 126);
		panel.add(panelSelecionarCampos);
		panelSelecionarCampos.setLayout(null);

		JLabel lblCampos = new JLabel("Campos - pode-se selecionar múltiplos:");
		lblCampos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCampos.setBounds(14, 0, 294, 14);
		panelSelecionarCampos.add(lblCampos);
		// aqui colocamos os campos das tabelas respectivas

		listModelCampos.addElement("Campo 1");
		listModelCampos.addElement("Campo 2");
		listModelCampos.addElement("Campo 3");
		listModelCampos.addElement("Campo 4");
		listModelCampos.addElement("Campo 5");

		JList<String> listCampos = new JList<>(listModelCampos);
		listCampos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCampos.setBounds(238, 85, 200, 100);

		JScrollPane scrollPaneCampos = new JScrollPane(listCampos);
		scrollPaneCampos.setBounds(14, 26, 200, 100);
		panelSelecionarCampos.add(scrollPaneCampos);
		// --------------------------------------------------------
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrdenarPor.setBounds(400, 60, 200, 14);
		panel.add(lblOrdenarPor);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(400, 85, 200, 22);
		panel.add(comboBox_1);

		//--------------------------------------------------

		btnGerarRelatorio.addActionListener(e -> {
			List<String> selectedTabelas = listTabelas.getSelectedValuesList();
			List<String> selectedCampos = listCampos.getSelectedValuesList();
			
			System.out.println("Tabelas Selecionadas: " + selectedTabelas);
			System.out.println("Campos Selecionados: " + selectedCampos);

			Relatorio relatorio = new Relatorio(idRecebido);
			relatorio.show(); // Mostra a nova tela
			frame.dispose(); // Fecha a tela atual
		});

		// Ação para voltar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a nova tela
			frame.dispose(); // Fecha a tela atual
		});
	}

	// Exibe a tela (chamado no clique da tela inicial)
	public void show() {
		frame.setVisible(true);
	}
}
