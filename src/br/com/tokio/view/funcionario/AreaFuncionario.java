package br.com.tokio.view.funcionario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.model.Impressora;
import br.com.tokio.view.TelaInicial;

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

		JTabbedPane tabbedPaneOpcoes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneOpcoes.setBounds(0, 98, 784, 463);
		frame.getContentPane().add(tabbedPaneOpcoes);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 98, 784, 463);
		tabbedPaneOpcoes.add(panel);
		tabbedPaneOpcoes.setTitleAt(0, "Opções");
		panel.setLayout(null);

		// Botão para gerar Relatório
		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.setBackground(new Color(225, 186, 83));
		btnGerarRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGerarRelatorio.setBounds(552, 360, 200, 40);
		panel.add(btnGerarRelatorio);
		

		JLabel lblRelatorio = new JLabel("Gerar relatório:");
		lblRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatorio.setBounds(49, 11, 200, 25);
		panel.add(lblRelatorio);

	
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

		JRadioButton rdbtnAtivos = new JRadioButton("Relatório de Seguros Ativos");
		rdbtnAtivos.setBounds(6, 33, 283, 23);
		panel_1.add(rdbtnAtivos);

		JRadioButton rdbtnCliente = new JRadioButton("Relatório de Seguros por Cliente");
		rdbtnCliente.setBounds(6, 59, 283, 23);
		panel_1.add(rdbtnCliente);

		JRadioButton rdbtnCorretora = new JRadioButton("Relatório de Seguros por Corretora");
		rdbtnCorretora.setBounds(6, 85, 283, 23);
		panel_1.add(rdbtnCorretora);

		JRadioButton rdbtnVencidos = new JRadioButton("Relatório de Seguros Vencidos");
		rdbtnVencidos.setBounds(6, 111, 283, 23);
		panel_1.add(rdbtnVencidos);

		JRadioButton rdbtnCobertura = new JRadioButton("Relatório de Seguros por Cobertura");
		rdbtnCobertura.setBounds(6, 137, 283, 23);
		panel_1.add(rdbtnCobertura);

		JRadioButton rdbtnPeriodo = new JRadioButton("Relatório de Seguros por Período");
		rdbtnPeriodo.setBounds(6, 163, 283, 23);
		panel_1.add(rdbtnPeriodo);

		JRadioButton rdbtnAssistencia = new JRadioButton("Relatório de Seguros com Assistência Específica");
		rdbtnAssistencia.setBounds(6, 189, 283, 23);
		panel_1.add(rdbtnAssistencia);

		JRadioButton rdbtnPremios = new JRadioButton("Relatório de Total de Prêmios");
		rdbtnPremios.setBounds(6, 215, 283, 23);
		panel_1.add(rdbtnPremios);

		// Criando o ButtonGroup para agrupar os radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAtivos);
		group.add(rdbtnCliente);
		group.add(rdbtnCorretora);
		group.add(rdbtnVencidos);
		group.add(rdbtnCobertura);
		group.add(rdbtnPeriodo);
		group.add(rdbtnAssistencia);
		group.add(rdbtnPremios);

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
		
		// ---------------------------------------
		// painel que é uma aba
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 100, 564, 461);
		panel2.setLayout(null);
		
		JPanel panelRelatorio = new JPanel();
		panelRelatorio.setBounds(0, 100,260, 461);

		JScrollPane scrollPanelInformacoes = new JScrollPane(panelRelatorio);
		scrollPanelInformacoes.setBounds(184, 36, 431, 314);
		scrollPanelInformacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelInformacoes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel2.add(scrollPanelInformacoes);
		
		panelRelatorio.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblTitulo = new JLabel("Informações da Apólice");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(318, 5, 167, 20);
		panel2.add(lblTitulo);
		
		tabbedPaneOpcoes.add(panel2);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnImprimir.setBackground(new Color(225, 186, 83));
		btnImprimir.setBounds(557, 368, 200, 40);
		panel2.add(btnImprimir);
		tabbedPaneOpcoes.setTitleAt(1, "Relatório");
		
		// --------------------------------------------
		
		
		

	    // Inicialmente, ocultar todos os painéis
	    panelCobertura.setVisible(false);
	    panelCliente.setVisible(false);
	    panelPeriodo.setVisible(false);
	    panelCorretora.setVisible(false);
	    panelAssistencia.setVisible(false);


	    // ActionListener para os radio buttons
	    rdbtnAtivos.addActionListener(e -> {
	        // Esconder todos os painéis
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	        panelPeriodo.setVisible(false); // Exemplo: Relatório de Seguros por Período
	    });

	    rdbtnCliente.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(true); // Exemplo: Relatório de Seguros por Cliente
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnCorretora.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(true); // Exemplo: Relatório de Seguros por Corretora
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnVencidos.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false); // Exemplo: Relatório de Seguros com Assistência Específica
	    });

	    rdbtnCobertura.addActionListener(e -> {
	        panelCobertura.setVisible(true); // Exemplo: Relatório de Seguros por Cobertura
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnPeriodo.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(true); // Exemplo: Relatório de Seguros por Período
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });

	    rdbtnAssistencia.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(true); // Exemplo: Relatório de Seguros com Assistência Específica
	    });

	    rdbtnPremios.addActionListener(e -> {
	        panelCobertura.setVisible(false);
	        panelCliente.setVisible(false);
	        panelPeriodo.setVisible(false);
	        panelCorretora.setVisible(false);
	        panelAssistencia.setVisible(false);
	    });


		//--------------------------------------------------

		btnGerarRelatorio.addActionListener(e -> {
			Connection connection = new ConnectionFactory().conectar();
			SeguroDAO dao = new SeguroDAO(connection);
			String selectedOption = "";
			
			// Criando um array de botões de rádio e seus nomes
            JRadioButton[] buttons = {rdbtnAtivos, rdbtnCliente, rdbtnCorretora, rdbtnVencidos, 
                                       rdbtnCobertura, rdbtnPeriodo, rdbtnAssistencia, rdbtnPremios};
            String[] labels = {"Ativos", "Cliente", "Corretora", "Vencidos", "Cobertura", 
                               "Período", "Assistência", "Prêmios"};

            // Iterando sobre os botões para encontrar o selecionado
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].isSelected()) {
                	selectedOption = labels[i];
                }
            }

			// Usando switch para verificar qual opção foi selecionada
			switch (selectedOption) {
			case "Ativos":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarAtivos();
				break;
			case "Cliente":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarPorCliente();
				break;
			case "Corretora":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarPorCorretora();
				break;
			case "Vencidos":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarVencidos();
				break;
			case "Cobertura":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarCobertura();
				break;
			case "Período":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarPorPeriodo();
				break;
			case "Assistência":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarPorAssistencia();
				break;
			case "Prêmios":
				tabbedPaneOpcoes.setSelectedIndex(1);
				//dao.buscarPremios();
				break;
			default:
				JOptionPane.showMessageDialog(frame, "Nenhuma opção selecionada.");
			}
			
		});

		// Ação para voltar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a nova tela
			frame.dispose(); // Fecha a tela atual
		});
		
		btnImprimir.addActionListener(e -> {
			
			Impressora impressora = new Impressora();
	        impressora.iniciarImpressao(panelRelatorio);
			
			frame.dispose(); // Fecha a tela atual
		});
	}


	// Exibe a tela (chamado no clique da tela inicial)
	public void show() {
		frame.setVisible(true);
	}
}