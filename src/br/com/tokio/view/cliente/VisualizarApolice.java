package br.com.tokio.view.cliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.ImovelDAO;
import br.com.tokio.dao.PacoteCoberturaDAO;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Corretora;
import br.com.tokio.model.Imovel;
import br.com.tokio.model.Impressora;
import br.com.tokio.model.PacoteAssistencia;
import br.com.tokio.model.PacoteCobertura;
import br.com.tokio.model.Seguro;
import br.com.tokio.view.TelaInicial;




public class VisualizarApolice {

	private JFrame frame;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarApolice window = new VisualizarApolice();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela visualizar apólice, onde o cliente pode ver e também solicitar a impressão ou o download do pdf do documento
	 */
	public VisualizarApolice() {
		initialize();
	}
	
	
	/**
	 * Cria a tela visualizar apólice, onde o cliente pode ver e também solicitar a impressão ou o download do pdf do documento
	 * agora passando os dados do cliente logado
	 * @param int - id do cliente
	 */
	public VisualizarApolice(int idCliente) {
		this.idRecebido = idCliente;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        Connection connection = new ConnectionFactory().conectar();
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		ImovelDAO imovelDAO = new ImovelDAO(connection);
		SeguroDAO seguroDAO = new SeguroDAO(connection);
		PacoteCoberturaDAO pacoteCoberturaDAO = new PacoteCoberturaDAO(connection);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(51, 153, 102));
		panelHeader.setBounds(0, 0, 784, 100);
		frame.getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/logo-tokio-marine.png"));
		Image img = icon.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		panelHeader.setLayout(null);

		JButton btnLogo = new JButton(icon);
		btnLogo.setBorder(BorderFactory.createEmptyBorder());
		btnLogo.setFocusPainted(false);
		btnLogo.setBounds(5, 15, 243, 69);
		btnLogo.setBackground(new Color(0, 153, 102));
		panelHeader.add(btnLogo);

		JLabel lblVerApolice = new JLabel("Visualizar apólice");
		lblVerApolice.setForeground(new Color(255, 255, 255));
		lblVerApolice.setBackground(new Color(255, 255, 255));
		lblVerApolice.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerApolice.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblVerApolice.setBounds(279, 15, 226, 69);
		panelHeader.add(lblVerApolice);

		// Evento para retornar à tela inicial
		btnLogo.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show();
			frame.dispose();
		});

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

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(560, 509, 200, 30);
		frame.getContentPane().add(btnImprimir);
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnImprimir.setBackground(new Color(225, 193, 85));

		// ------------------------------------------------------
		JPanel panel = new JPanel();
		panel.setBounds(0, 100, 564, 461);

		JScrollPane scrollPanelInformacoes = new JScrollPane(panel);
		scrollPanelInformacoes.setBounds(28, 152, 512, 387);
		scrollPanelInformacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelInformacoes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		frame.getContentPane().add(scrollPanelInformacoes);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblTitulo = new JLabel("Informações da Apólice");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(124, 111, 300, 30);
		frame.getContentPane().add(lblTitulo);
		// --------------------------------------------------------------
		
		Cliente clienteLogado = clienteDAO.selectById(idRecebido); //id que passa de página a página

		JLabel lblCdCliente = new JLabel("Código do Cliente:");
		lblCdCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCdCliente.setBounds(38, 60, 150, 25);
		panel.add(lblCdCliente);
		lblCdCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCdCliente = new JTextField();
		txtCdCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCdCliente.setBounds(182, 60, 200, 25);
		txtCdCliente.setEditable(false);
		String idCliente = String.valueOf(clienteLogado.getIdCliente());
		txtCdCliente.setText(idCliente);
		panel.add(txtCdCliente);

		JLabel lblNmCliente = new JLabel("Nome:");
		lblNmCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNmCliente.setBounds(38, 95, 150, 25);
		panel.add(lblNmCliente);
		lblNmCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtNmCliente = new JTextField();
		txtNmCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNmCliente.setBounds(182, 95, 200, 25);
		txtNmCliente.setEditable(false);
		txtNmCliente.setText(clienteLogado.getNome());
		panel.add(txtNmCliente);

		JLabel lblCpfCliente = new JLabel("CPF:");
		lblCpfCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpfCliente.setBounds(38, 130, 150, 25);
		panel.add(lblCpfCliente);
		lblCpfCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCpfCliente = new JTextField();
		txtCpfCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCpfCliente.setBounds(182, 130, 200, 25);
		txtCpfCliente.setEditable(false);
		txtCpfCliente.setText(clienteLogado.getCpf());//
		panel.add(txtCpfCliente);

		JLabel lblTelefoneCliente = new JLabel("Telefone:");
		lblTelefoneCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefoneCliente.setBounds(38, 165, 150, 25);
		panel.add(lblTelefoneCliente);
		lblTelefoneCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtTelefoneCliente = new JTextField();
		txtTelefoneCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefoneCliente.setBounds(182, 165, 200, 25);
		txtTelefoneCliente.setEditable(false);
		txtTelefoneCliente.setText(clienteLogado.getTelefone());//
		panel.add(txtTelefoneCliente);

		JLabel lblEmailCliente = new JLabel("Email:");
		lblEmailCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailCliente.setBounds(38, 200, 150, 25);
		panel.add(lblEmailCliente);
		lblEmailCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtEmailCliente = new JTextField();
		txtEmailCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmailCliente.setBounds(182, 200, 200, 25);
		txtEmailCliente.setEditable(false);
		txtEmailCliente.setText(clienteLogado.getEmail());//
		panel.add(txtEmailCliente);

		// Imóvel
		Imovel imovel = imovelDAO.selectByClienteId(idRecebido);
		JLabel lblCdImovel = new JLabel("Código do Imóvel:");
		lblCdImovel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCdImovel.setBounds(38, 250, 150, 25);
		panel.add(lblCdImovel);
		lblCdImovel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCdImovel = new JTextField();
		txtCdImovel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCdImovel.setBounds(182, 250, 200, 25);
		txtCdImovel.setEditable(false);
		String idImovel = String.valueOf(imovel.getIdImovel());
		txtCdImovel.setText(idImovel); // Valor de exemplo
		panel.add(txtCdImovel);

		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogradouro.setBounds(38, 285, 150, 25);
		panel.add(lblLogradouro);
		lblLogradouro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtLogradouro = new JTextField();
		txtLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtLogradouro.setBounds(182, 285, 200, 25);
		txtLogradouro.setEditable(false);
		txtLogradouro.setText(imovel.getLogradouro());
		panel.add(txtLogradouro);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBairro.setBounds(38, 320, 150, 25);
		panel.add(lblBairro);
		lblBairro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtBairro.setBounds(182, 320, 200, 25);
		txtBairro.setEditable(false);
		txtBairro.setText(imovel.getBairro());
		panel.add(txtBairro);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCidade.setBounds(38, 355, 150, 25);
		panel.add(lblCidade);
		lblCidade.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCidade = new JTextField();
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCidade.setBounds(182, 355, 200, 25);
		txtCidade.setEditable(false);
		txtCidade.setText(imovel.getCidade());
		panel.add(txtCidade);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEstado.setBounds(38, 390, 150, 25);
		panel.add(lblEstado);
		lblEstado.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtEstado = new JTextField();
		txtEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEstado.setBounds(182, 390, 200, 25);
		txtEstado.setEditable(false);
		txtEstado.setText(imovel.getEstado());
		panel.add(txtEstado);

		JLabel lblVlImovel = new JLabel("Valor do Imóvel:");
		lblVlImovel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVlImovel.setBounds(38, 425, 150, 25);
		panel.add(lblVlImovel);
		lblVlImovel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtVlImovel = new JTextField();
		txtVlImovel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtVlImovel.setBounds(182, 425, 200, 25);
		txtVlImovel.setEditable(false);
		String valorImovel = "R$"+ String.valueOf(imovel.getValorImovel());
		txtVlImovel.setText(valorImovel);
		panel.add(txtVlImovel);

		// Seguro
		Seguro seguro = seguroDAO.selectByCliente(idRecebido);

		JLabel lblCdSeguro = new JLabel("Código do Seguro:");
		lblCdSeguro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCdSeguro.setBounds(450, 60, 150, 25);
		panel.add(lblCdSeguro);
		lblCdSeguro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCdSeguro = new JTextField();
		txtCdSeguro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCdSeguro.setBounds(610, 60, 200, 25);
		txtCdSeguro.setEditable(false);
		String idSeguro = String.valueOf(seguro.getIdSeguro());

		txtCdSeguro.setText(idSeguro); // Valor de exemplo
		panel.add(txtCdSeguro);

		JLabel lblVlPremio = new JLabel("Valor do Prêmio:");
		lblVlPremio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVlPremio.setBounds(450, 95, 150, 25);
		panel.add(lblVlPremio);
		lblVlPremio.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtVlPremio = new JTextField();
		txtVlPremio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtVlPremio.setBounds(610, 95, 200, 25);
		txtVlPremio.setEditable(false);
		txtVlPremio.setText("R$ 1.200,00");
		panel.add(txtVlPremio);

		JLabel lblDtInicio = new JLabel("Data de Início:");
		lblDtInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDtInicio.setBounds(450, 130, 150, 25);
		panel.add(lblDtInicio);
		lblDtInicio.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtDtInicio = new JTextField();
		txtDtInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDtInicio.setBounds(610, 130, 200, 25);
		txtDtInicio.setEditable(false);
		txtDtInicio.setText(String.valueOf(seguro.getDataInicio()));
		panel.add(txtDtInicio);

		JLabel lblDtFim = new JLabel("Data de Término:");
		lblDtFim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDtFim.setBounds(450, 165, 150, 25);
		panel.add(lblDtFim);
		lblDtFim.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtDtFim = new JTextField();
		txtDtFim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDtFim.setBounds(610, 165, 200, 25);
		txtDtFim.setEditable(false);
		txtDtFim.setText(String.valueOf(seguro.getDataFim()));
		panel.add(txtDtFim);
		
		PacoteCobertura pacoteCobertura = seguroDAO.selectCoberturaPorCliente(idRecebido);
		PacoteAssistencia pacoteAsssistencia = seguroDAO.selectPacotePorCliente(idRecebido);
		Corretora corretora = seguroDAO.selectCorretoraByIdSeguro(seguro.getIdSeguro()) ;
		
		JLabel lblCdCobertura = new JLabel("Código da Cobertura:");
		lblCdCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCdCobertura.setBounds(450, 200, 150, 25);
		panel.add(lblCdCobertura);
		lblCdCobertura.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCdCobertura = new JTextField();
		txtCdCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCdCobertura.setBounds(610, 200, 200, 25);
		txtCdCobertura.setEditable(false);
		String idCobertura = String.valueOf(pacoteCobertura.getIdCobertura());
		txtCdCobertura.setText(idCobertura);
		panel.add(txtCdCobertura);

		JLabel lblCdAssistencia = new JLabel("Código da Assistência:");
		lblCdAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCdAssistencia.setBounds(450, 235, 150, 25);
		panel.add(lblCdAssistencia);
		lblCdAssistencia.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtCdAssistencia = new JTextField();
		txtCdAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCdAssistencia.setBounds(610, 235, 200, 25);
		txtCdAssistencia.setEditable(false);
		String idAssistencia = String.valueOf(pacoteAsssistencia.getIdAssistencia());
		
		txtCdAssistencia.setText(idAssistencia);
		panel.add(txtCdAssistencia);

		// Cobertura
		JLabel lblTpCobertura = new JLabel("Tipo de Cobertura:");
		lblTpCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTpCobertura.setBounds(450, 285, 150, 25);
		panel.add(lblTpCobertura);
		lblTpCobertura.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtTpCobertura = new JTextField();
		txtTpCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTpCobertura.setBounds(610, 285, 200, 25);
		txtTpCobertura.setEditable(false);
		txtTpCobertura.setText(pacoteCobertura.getTipo());
		panel.add(txtTpCobertura);

		JLabel lblDsCobertura = new JLabel("Descrição da Cobertura:");
		lblDsCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDsCobertura.setBounds(450, 320, 150, 25);
		panel.add(lblDsCobertura);
		lblDsCobertura.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtDsCobertura = new JTextField();
		txtDsCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDsCobertura.setBounds(610, 320, 200, 25);
		txtDsCobertura.setEditable(false);
		txtDsCobertura.setText(pacoteCobertura.getDescricao());
		panel.add(txtDsCobertura);

		JLabel lblVlPctCobertura = new JLabel("Valor da Cobertura:");
		lblVlPctCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVlPctCobertura.setBounds(450, 355, 150, 25);
		panel.add(lblVlPctCobertura);
		lblVlPctCobertura.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtVlPctCobertura = new JTextField();
		txtVlPctCobertura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtVlPctCobertura.setBounds(610, 355, 200, 25);
		txtVlPctCobertura.setEditable(false);
		txtVlPctCobertura.setText("R$" + pacoteCobertura.getPreco());
		panel.add(txtVlPctCobertura);

		// Assistência
		JLabel lblTpAssistencia = new JLabel("Tipo de Assistência:");
		lblTpAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTpAssistencia.setBounds(450, 405, 150, 25);
		panel.add(lblTpAssistencia);
		lblTpAssistencia.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtTpAssistencia = new JTextField();
		txtTpAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTpAssistencia.setBounds(610, 405, 200, 25);
		txtTpAssistencia.setEditable(false);
		txtTpAssistencia.setText(pacoteAsssistencia.getTipo());
		panel.add(txtTpAssistencia);

		JLabel lblDsAssistencia = new JLabel("Descrição da Assistência:");
		lblDsAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDsAssistencia.setBounds(450, 440, 150, 25);
		panel.add(lblDsAssistencia);
		lblDsAssistencia.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtDsAssistencia = new JTextField();
		txtDsAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDsAssistencia.setBounds(610, 440, 200, 25);
		txtDsAssistencia.setEditable(false);
		txtDsAssistencia.setText(pacoteAsssistencia.getDescricao());
		panel.add(txtDsAssistencia);

		JLabel lblVlPctAssistencia = new JLabel("Valor da Assistência:");
		lblVlPctAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVlPctAssistencia.setBounds(450, 475, 150, 25);
		panel.add(lblVlPctAssistencia);
		lblVlPctAssistencia.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtVlPctAssistencia = new JTextField();
		txtVlPctAssistencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtVlPctAssistencia.setBounds(610, 475, 200, 25);
		txtVlPctAssistencia.setEditable(false);
		txtVlPctAssistencia.setText("R$" + pacoteAsssistencia.getPreco());
		panel.add(txtVlPctAssistencia);

		// Corretora
		JLabel lblNmCorretora = new JLabel("Nome da Corretora:");
		lblNmCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNmCorretora.setBounds(450, 525, 150, 25);
		panel.add(lblNmCorretora);
		lblNmCorretora.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtNmCorretora = new JTextField();
		txtNmCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNmCorretora.setBounds(610, 525, 200, 25);
		txtNmCorretora.setEditable(false);
		txtNmCorretora.setText(corretora.getNomeCorretora());
		panel.add(txtNmCorretora);

		JLabel lblEnderecoCorretora = new JLabel("Endereço da Corretora:");
		lblEnderecoCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnderecoCorretora.setBounds(450, 560, 150, 25);
		panel.add(lblEnderecoCorretora);
		lblEnderecoCorretora.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

		JTextField txtEnderecoCorretora = new JTextField();
		txtEnderecoCorretora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEnderecoCorretora.setBounds(610, 560, 200, 25);
		txtEnderecoCorretora.setEditable(false);
		txtEnderecoCorretora.setText(corretora.getEndereco());
		panel.add(txtEnderecoCorretora);



		// ------------------------------------------

		// Botão de fechar
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFechar.setBackground(new Color(225, 193, 85));
		btnFechar.setBounds(250, 500, 200, 30);
		scrollPanelInformacoes.add(btnFechar);
		
		
		// Evento para abrir a tela EditarCliente
		btnImprimir.addActionListener(e -> {
			
			Impressora impressora = new Impressora();
	        impressora.iniciarImpressao(panel);
			
		});

		btnVoltar.addActionListener(e -> {
			AreaCliente areaCliente = new AreaCliente(idRecebido);
			areaCliente.show();
			frame.dispose();
		});

		
	}





	public void show() {
		frame.setVisible(true);
	}
}
