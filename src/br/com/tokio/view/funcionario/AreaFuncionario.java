package br.com.tokio.view.funcionario;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

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
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.CorretoraDAO;
import br.com.tokio.dao.PacoteAssistenciaDAO;
import br.com.tokio.dao.PacoteCoberturaDAO;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.model.Impressora;
import br.com.tokio.model.Seguro;
import br.com.tokio.view.TelaInicial;

public class AreaFuncionario {

	private JFrame frame;
	private int idRecebido;
	private JTextField textClienteCPF;
	private JTextField textDataInicial;
	private JTextField textFinal;
	private JTextField textCorretora;
	
	public List<Seguro> seguros = null;

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
	 * Inicializa a tela 'area do funcionario' onde ele pode solicitar relatórios e também imprimir na aba em que este for gerado
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Connection connection = new ConnectionFactory().conectar();
		
        SeguroDAO dao = new SeguroDAO(connection);
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		PacoteAssistenciaDAO assistenciaDAO = new PacoteAssistenciaDAO(connection);
		PacoteCoberturaDAO coberturaDAO = new PacoteCoberturaDAO(connection);

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
		// ------------------------------------------------
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(49, 60, 295, 246);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Selecione o relatório a ser gerado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(6, 0, 283, 14);
		panel_1.add(lblNewLabel);

		JRadioButton rdbtnAtivos = new JRadioButton("Relatório de Seguros Ativos");
		rdbtnAtivos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnAtivos.setBounds(6, 33, 283, 23);
		panel_1.add(rdbtnAtivos);

		JRadioButton rdbtnCliente = new JRadioButton("Relatório de Seguros por Cliente");
		rdbtnCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCliente.setBounds(6, 59, 283, 23);
		panel_1.add(rdbtnCliente);

		JRadioButton rdbtnCorretora = new JRadioButton("Relatório de Seguros por Corretora");
		rdbtnCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCorretora.setBounds(6, 85, 283, 23);
		panel_1.add(rdbtnCorretora);

		JRadioButton rdbtnVencidos = new JRadioButton("Relatório de Seguros Vencidos");
		rdbtnVencidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnVencidos.setBounds(6, 111, 283, 23);
		panel_1.add(rdbtnVencidos);

		JRadioButton rdbtnCobertura = new JRadioButton("Relatório de Seguros por Cobertura");
		rdbtnCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnCobertura.setBounds(6, 137, 283, 23);
		panel_1.add(rdbtnCobertura);

		JRadioButton rdbtnPeriodo = new JRadioButton("Relatório de Seguros por Período");
		rdbtnPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnPeriodo.setBounds(6, 163, 283, 23);
		panel_1.add(rdbtnPeriodo);

		JRadioButton rdbtnAssistencia = new JRadioButton("Relatório de Seguros por Assistência");
		rdbtnAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnAssistencia.setBounds(6, 189, 283, 23);
		panel_1.add(rdbtnAssistencia);

	

		// Criando o ButtonGroup para agrupar os radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAtivos);
		group.add(rdbtnCliente);
		group.add(rdbtnCorretora);
		group.add(rdbtnVencidos);
		group.add(rdbtnCobertura);
		group.add(rdbtnPeriodo);
		group.add(rdbtnAssistencia);

		// ------------------------------------------------------
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(400, 129, 200, 177);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panelCobertura = new JPanel();
		panelCobertura.setBounds(0, 0, 200, 177);
		panel_2.add(panelCobertura);
		panelCobertura.setLayout(null);
		
		JLabel lblCobertura = new JLabel("Selecione o tipo de cobertura:");
		lblCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCobertura.setBounds(0, 0, 200, 14);
		panelCobertura.add(lblCobertura);
		
		JComboBox comboBoxCobertura = new JComboBox();
		comboBoxCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxCobertura.setBounds(0, 25, 200, 22);
		panelCobertura.add(comboBoxCobertura);
		
		comboBoxCobertura.addItem("Básico");
		comboBoxCobertura.addItem("Intermediário");
		comboBoxCobertura.addItem("Completo");
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBounds(0, 0, 200, 177);
		panel_2.add(panelCliente);
		panelCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("CPF do cliente a ser consultado:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCliente.setBounds(0, 11, 200, 14);
		panelCliente.add(lblCliente);
		
		textClienteCPF = new JTextField();
		textClienteCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textClienteCPF.setBounds(0, 30, 200, 20);
		panelCliente.add(textClienteCPF);
		textClienteCPF.setColumns(10);
		
		JPanel panelPeriodo = new JPanel();
		panelPeriodo.setBounds(0, 0, 200, 177);
		panel_2.add(panelPeriodo);
		panelPeriodo.setLayout(null);
		
		JLabel lblInicial = new JLabel("Data inicial:");
		lblInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInicial.setBounds(0, 0, 200, 14);
		panelPeriodo.add(lblInicial);
		
		textDataInicial = new JTextField();
		textDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textDataInicial.setBounds(0, 25, 200, 20);
		panelPeriodo.add(textDataInicial);
		textDataInicial.setColumns(10);
		
		textFinal = new JTextField();
		textFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFinal.setColumns(10);
		textFinal.setBounds(0, 103, 200, 20);
		panelPeriodo.add(textFinal);
		
		JLabel lblFinal = new JLabel("Data final:");
		lblFinal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFinal.setBounds(0, 78, 200, 14);
		panelPeriodo.add(lblFinal);
		
		JPanel panelCorretora = new JPanel();
		panelCorretora.setBounds(0, 0, 200, 177);
		panel_2.add(panelCorretora);
		panelCorretora.setLayout(null);
		
		textCorretora = new JTextField();
		textCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCorretora.setBounds(0, 25, 200, 20);
		textCorretora.setColumns(10);
		panelCorretora.add(textCorretora);
		
		JLabel lblCorretora = new JLabel("ID da corretora:");
		lblCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCorretora.setBounds(0, 0, 200, 14);
		panelCorretora.add(lblCorretora);
		
		JPanel panelAssistencia = new JPanel();
		panelAssistencia.setBounds(0, 0, 200, 177);
		panel_2.add(panelAssistencia);
		panelAssistencia.setLayout(null);
		
		JComboBox comboBoxAssistencia = new JComboBox();
		comboBoxAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxAssistencia.setBounds(0, 25, 200, 22);
		panelAssistencia.add(comboBoxAssistencia);
		
		comboBoxAssistencia.addItem("Básico");
		comboBoxAssistencia.addItem("Essencial");
		comboBoxAssistencia.addItem("VIP");
		
		JLabel lblAssistencia = new JLabel("Selecione o tipo de assistencia");
		lblAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssistencia.setBounds(0, 0, 200, 14);
		panelAssistencia.add(lblAssistencia);
		
		JButton btnSair = new JButton("Voltar");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSair.setBackground(new Color(225, 186, 83));
		btnSair.setBounds(49, 360, 132, 40);
		panel.add(btnSair);
		
		// ---------------------------------------
		// painel que é uma aba
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 100, 564, 461);
		panel2.setLayout(null);
		
		
		// painel dentro do scrollpane
		JPanel panelRelatorio = new JPanel();
		panelRelatorio.setBounds(0, 100,260, 461);

		JScrollPane scrollPanelInformacoes = new JScrollPane(panelRelatorio);
		scrollPanelInformacoes.setBounds(16, 36, 747, 314);
		scrollPanelInformacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel2.add(scrollPanelInformacoes);

		JLabel lblTitulo = new JLabel("Informações da Apólice");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(306, 5, 167, 20);
		panel2.add(lblTitulo);
		
		tabbedPaneOpcoes.add(panel2);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnImprimir.setBackground(new Color(225, 186, 83));
		btnImprimir.setBounds(563, 368, 200, 40);
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

	   
	    
	    JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnVoltar.setBackground(new Color(225, 186, 83));
        btnVoltar.setBounds(415, 368, 132, 40);
        panel2.add(btnVoltar);

		//--------------------------------------------------

		btnGerarRelatorio.addActionListener(e -> {
			
			
			
			String selectedOption = "";
			
			String coberturaSelecionada = (String) comboBoxCobertura.getSelectedItem();
			String clienteCPF = textClienteCPF.getText();
			String dataInicial = textDataInicial.getText();
			String dataFinal = textFinal.getText();
			String corretoraID = textCorretora.getText();
			String assistenciaSelecionada = (String) comboBoxAssistencia.getSelectedItem();
			
			// Criando um array de botões de rádio e seus nomes
            JRadioButton[] buttons = {rdbtnAtivos, rdbtnCliente, rdbtnCorretora, rdbtnVencidos, 
                                       rdbtnCobertura, rdbtnPeriodo, rdbtnAssistencia};
            String[] labels = {"Ativos", "Cliente", "Corretora", "Vencidos", "Cobertura", 
                               "Período", "Assistência", "Prêmios"};

            // Iterando sobre os botões para encontrar o selecionado
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].isSelected()) {
                	selectedOption = labels[i];
                }
            }
            
            panelRelatorio.removeAll(); // Limpa os itens anteriores
            
            List<Seguro> seguros;
            
            switch (selectedOption) {
            case "Ativos":
            	seguros = dao.buscarAtivos();  // Preenchendo a lista com os resultados da consulta
            	break;
            case "Cliente":
            	int idCliente = clienteDAO.selectIdByCpf(textClienteCPF.getText());  // Pegando o CPF do cliente do campo de texto
            	
            	if (idCliente != 0) {
            		seguros = dao.buscarPorCliente(idCliente);  // Preenchendo a lista com os resultados da consulta
            	}else {
            		seguros = List.of();
            	}
            	break;
            case "Corretora":
            	int idCorretora = Integer.parseInt(textCorretora.getText());  // Pegando o ID da corretora do campo de texto
            	
            	if (idCorretora != 0) {
            		seguros = dao.buscarPorCorretora(idCorretora);  // Preenchendo a lista com os resultados da consulta
            	}else {
            		seguros = List.of();
            	}
            	break;
            case "Vencidos":
            	seguros = dao.buscarVencidos();  // Preenchendo a lista com os resultados da consulta
            	break;
            case "Cobertura":
            	int idCobertura = coberturaDAO.selectIdByNome(comboBoxCobertura.getSelectedItem().toString().trim()); 
            	
            	if (idCobertura != 0) {
            		seguros = dao.buscarCobertura(idCobertura);  // Preenchendo a lista com os resultados da consulta
            	}else {
            		seguros = List.of();
            	}
            	break;
            case "Período":
            	if (!textDataInicial.getText().isBlank() && !textFinal.getText().isBlank()) {
            		Date dataInicio = Date.valueOf(textDataInicial.getText().trim());  // Pegando a data inicial do campo de texto
            		Date dataFim = Date.valueOf(textFinal.getText().trim());  // Pegando a data final do campo de texto
            		seguros = dao.buscarPorPeriodo(dataInicio, dataFim);  // Preenchendo a lista com os resultados da consulta
            	} else {
            		seguros = List.of();
            	}
            	break;
            case "Assistência":
            	int idAssistencia = assistenciaDAO.selectIdByNome(comboBoxAssistencia.getSelectedItem().toString().trim());  // Pegando o ID da assistência do combo box
            	
            	if (idAssistencia != 0) {
            		seguros = dao.buscarPorAssistencia(idAssistencia);  // Preenchendo a lista com os resultados da consulta
            	}else {
            		seguros = List.of();
            	}
            	break;
            default:
            	seguros = List.of();
            }
            
            // Adicionar os itens ao painel
            if (seguros.isEmpty()) {
            	JOptionPane.showMessageDialog(frame, "nada foi encontrado", "Erro de autenticação",
						JOptionPane.ERROR_MESSAGE);
            } else {
            	for (Seguro seguro : seguros) {
            		panelRelatorio.setLayout(new GridLayout(0, 1, 0, 0));
            		JLabel lblSeguro = new JLabel(formatarSeguro(seguro));
            		lblSeguro.setHorizontalAlignment(SwingConstants.CENTER);
            		lblSeguro.setFont(new Font("Tahoma", Font.PLAIN, 14));
            		lblSeguro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            		lblSeguro.setAlignmentX(Component.LEFT_ALIGNMENT);
            		panelRelatorio.add(lblSeguro);
            		tabbedPaneOpcoes.setSelectedIndex(1);
            	}
            }
            
            panelRelatorio.revalidate();
            panelRelatorio.repaint();

			
		});
		

		btnSair.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); 
			frame.dispose(); 
		});
		
		// Ação para voltar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); 
			frame.dispose();
		});
		
		btnImprimir.addActionListener(e -> {
			
			Impressora impressora = new Impressora();
	        impressora.iniciarImpressao(panelRelatorio);
	        
			frame.dispose(); // Fecha a tela atual
		});
		
		btnVoltar.addActionListener(e -> {
			tabbedPaneOpcoes.setSelectedIndex(0);
		});
	}


	/**formata uma linha com dados relacionados ao seguro para colocar no relatorio
	 * @param Seguro - seguro
	 * @return String - tupla do seguro
	 * */
    private String formatarSeguro(Seguro seguro) {
    	Connection connection = new ConnectionFactory().conectar();
		
        SeguroDAO dao = new SeguroDAO(connection);
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		PacoteAssistenciaDAO assistenciaDAO = new PacoteAssistenciaDAO(connection);
		PacoteCoberturaDAO coberturaDAO = new PacoteCoberturaDAO(connection);
		CorretoraDAO corretoraDAO = new CorretoraDAO(connection);
		
        return String.format(
            "ID: %d | Prêmio: R$ %.2f | Início: %s | Fim: %s | Id do cliente: %d | Nome do Cliente: %s | CPF do Cliente: %s |"
            + " id da cobertura: %d | Tipo de Cobertura: %s | id da assistência: %d | Tipo de Assistência: %s | id da corretora: %d | Nome da Corretora: %s |",
            seguro.getIdSeguro(),
            seguro.getValorPremio(),
            seguro.getDataInicio(),
            seguro.getDataFim(),
            seguro.getIdCliente(),
            clienteDAO.selectById(seguro.getIdCliente()).getNome(),
            clienteDAO.selectById(seguro.getIdCliente()).getCpf(),
            seguro.getIdCobertura(),
            coberturaDAO.selectById(seguro.getIdCobertura()).getTipo(),
            seguro.getIdAssistencia(),
            assistenciaDAO.selectById(seguro.getIdAssistencia()).getTipo(),
            seguro.getIdCorretora(),
            corretoraDAO.selectById(seguro.getIdCorretora()).getNomeCorretora()
        );
    }

	public void show() {
		frame.setVisible(true);
	}
}