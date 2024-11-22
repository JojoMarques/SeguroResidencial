package br.com.tokio.view.funcionario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.tokio.view.TelaInicial;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AreaFuncionario {

	private JFrame frame;
	private int idRecebido;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		// aqui colocamos as tabelas respectivas

		listModelTabelas.addElement("Pacote Cobertura");
		listModelTabelas.addElement("Pacote Assistência");
		listModelTabelas.addElement("Clientes"); //listando todos os clientes
		listModelTabelas.addElement("Campo 4");
		listModelTabelas.addElement("Campo 5");

		// -----------------------------------------------

		DefaultListModel<String> listModelCampos = new DefaultListModel<>();
		// aqui colocamos os campos das tabelas respectivas

		listModelCampos.addElement("Campo 1");
		listModelCampos.addElement("Campo 2");
		listModelCampos.addElement("Campo 3");
		listModelCampos.addElement("Campo 4");
		listModelCampos.addElement("Campo 5");
		// --------------------------------------------------------
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOrdenarPor.setBounds(400, 60, 200, 14);
		panel.add(lblOrdenarPor);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(400, 85, 200, 22);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(49, 60, 295, 246);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Selecione o relatório a ser gerado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(6, 0, 283, 14);
		panel_1.add(lblNewLabel);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Relatório de Seguros Ativos");
		rdbtnNewRadioButton.setBounds(6, 33, 283, 23);
		panel_1.add(rdbtnNewRadioButton);

		JRadioButton rdbtnRelatrioDeSeguros = new JRadioButton("Relatório de Seguros por Cliente");
		rdbtnRelatrioDeSeguros.setBounds(6, 59, 283, 23);
		panel_1.add(rdbtnRelatrioDeSeguros);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Relatório de Seguros por Corretora");
		rdbtnNewRadioButton_1.setBounds(6, 85, 283, 23);
		panel_1.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Relatório de Seguros Vencidos");
		rdbtnNewRadioButton_2.setBounds(6, 111, 283, 23);
		panel_1.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Relatório de Seguros por Cobertura");
		rdbtnNewRadioButton_3.setBounds(6, 137, 283, 23);
		panel_1.add(rdbtnNewRadioButton_3);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Relatório de Seguros por Período");
		rdbtnNewRadioButton_4.setBounds(6, 163, 283, 23);
		panel_1.add(rdbtnNewRadioButton_4);

		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Relatório de Seguros com Assistência Específica");
		rdbtnNewRadioButton_5.setBounds(6, 189, 283, 23);
		panel_1.add(rdbtnNewRadioButton_5);

		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("Relatório de Total de Prêmios");
		rdbtnNewRadioButton_6.setBounds(6, 215, 283, 23);
		panel_1.add(rdbtnNewRadioButton_6);

		// Criando o ButtonGroup para agrupar os radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnRelatrioDeSeguros);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);
		group.add(rdbtnNewRadioButton_4);
		group.add(rdbtnNewRadioButton_5);
		group.add(rdbtnNewRadioButton_6);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(400, 129, 200, 177);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panelCobertura = new JPanel();
		panelCobertura.setBounds(0, 0, 200, 177);
		panel_2.add(panelCobertura);
		panelCobertura.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Selecione o tipo de cobertura:");
		lblNewLabel_3.setBounds(0, 0, 200, 14);
		panelCobertura.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 25, 200, 22);
		panelCobertura.add(comboBox);
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBounds(0, 0, 200, 177);
		panel_2.add(panelCliente);
		panelCliente.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CPF do cliente a ser consultado:");
		lblNewLabel_1.setBounds(0, 11, 200, 14);
		panelCliente.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(0, 30, 200, 20);
		panelCliente.add(textField);
		textField.setColumns(10);
		
		JPanel panelPeriodo = new JPanel();
		panelPeriodo.setBounds(0, 0, 200, 177);
		panel_2.add(panelPeriodo);
		panelPeriodo.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Data inicial:");
		lblNewLabel_2.setBounds(0, 0, 200, 14);
		panelPeriodo.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 25, 200, 20);
		panelPeriodo.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(0, 103, 200, 20);
		panelPeriodo.add(textField_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Data final:");
		lblNewLabel_2_1.setBounds(0, 78, 200, 14);
		panelPeriodo.add(lblNewLabel_2_1);
		
		JPanel panelCorretora = new JPanel();
		panelCorretora.setBounds(0, 0, 200, 177);
		panel_2.add(panelCorretora);
		panelCorretora.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(0, 25, 200, 20);
		textField_3.setColumns(10);
		panelCorretora.add(textField_3);
		
		JLabel lblNewLabel_2_2 = new JLabel("ID da corretora:");
		lblNewLabel_2_2.setBounds(0, 0, 200, 14);
		panelCorretora.add(lblNewLabel_2_2);
		
		JPanel panelAssistencia = new JPanel();
		panelAssistencia.setBounds(0, 0, 200, 177);
		panel_2.add(panelAssistencia);
		panelAssistencia.setLayout(null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(0, 25, 200, 22);
		panelAssistencia.add(comboBox_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Selecione o tipo de assistencia");
		lblNewLabel_3_1.setBounds(0, 0, 200, 14);
		panelAssistencia.add(lblNewLabel_3_1);
		
		
		// Adicionar componentes ao panelAssistencia...

	    // Inicialmente, ocultar todos os painéis
	    panelCobertura.setVisible(false);
	    panelCliente.setVisible(false);
	    panelPeriodo.setVisible(false);
	    panelCorretora.setVisible(false);
	    panelAssistencia.setVisible(false);


	    // ActionListener para os radio buttons
	    rdbtnNewRadioButton.addActionListener(e -> {
	        // Esconder todos os painéis
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	        
	        // Mostrar o painel específico
	        panelPeriodo.setVisible(true); // Exemplo: Relatório de Seguros por Período
	    });

	    rdbtnRelatrioDeSeguros.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(true); // Exemplo: Relatório de Seguros por Cliente
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnNewRadioButton_1.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(true); // Exemplo: Relatório de Seguros por Corretora
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnNewRadioButton_2.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(true); // Exemplo: Relatório de Seguros com Assistência Específica
	    });

	    rdbtnNewRadioButton_3.addActionListener(e -> {
	        panelCobertura.setVisible(true); // Exemplo: Relatório de Seguros por Cobertura
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnNewRadioButton_4.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(true); // Exemplo: Relatório de Seguros por Período
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnNewRadioButton_5.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(true); // Exemplo: Relatório de Seguros com Assistência Específica
	    });

	    rdbtnNewRadioButton_6.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });


		//--------------------------------------------------

		btnGerarRelatorio.addActionListener(e -> {

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
