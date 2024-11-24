package br.com.tokio.view.cliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.dao.ImovelDAO;
import br.com.tokio.dao.PacoteCoberturaDAO;
import br.com.tokio.dao.SeguroDAO;
import br.com.tokio.dao.SinistroDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.model.Impressora;
import br.com.tokio.model.Sinistro;
import br.com.tokio.view.TelaInicial;

public class VisualizarSinistros {

	private JFrame frame;
	private int idRecebido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarSinistros window = new VisualizarSinistros();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela 'visualizar sinistros', onde ele pode ver seus sinistros
	 */
	public VisualizarSinistros() {
		initialize();
	}

	
	/**
	 * Cria a tela 'visualizar sinistros', onde ele pode ver seus sinistros, recebendo o id do cliente logado
	 * @param int - id do cliente
	 */
	public VisualizarSinistros(int idCliente) {
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
		SinistroDAO sinistroDAO = new SinistroDAO(connection);
		
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

		JLabel lblVerSinistros = new JLabel("Visualizar sinistros");
		lblVerSinistros.setForeground(new Color(255, 255, 255));
		lblVerSinistros.setBackground(new Color(255, 255, 255));
		lblVerSinistros.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerSinistros.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblVerSinistros.setBounds(279, 15, 226, 69);
		panelHeader.add(lblVerSinistros);

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

		// ------------------------------------------------------
		JPanel panel = new JPanel();
		panel.setBounds(0, 100, 564, 461);

		JScrollPane scrollPanelInformacoes = new JScrollPane(panel);
		panel.setLayout(null);
		scrollPanelInformacoes.setBounds(136, 152, 512, 387);

		frame.getContentPane().add(scrollPanelInformacoes);

		JLabel lblTitulo = new JLabel("Meus sinistros:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(242, 111, 300, 30);
		frame.getContentPane().add(lblTitulo);
		// --------------------------------------------------------------
		
		Cliente clienteLogado = clienteDAO.selectById(idRecebido); //id que passa de página a página
		
		List<Sinistro> sinistros = sinistroDAO.selectByCliente(idRecebido);

		
		    for (Sinistro sinistro : sinistros) {
		        panel.setLayout(new GridLayout(0, 1, 0, 0));
		        JLabel lblSinistro = new JLabel(formatarSinistro(sinistro));
		        lblSinistro.setHorizontalAlignment(SwingConstants.CENTER);
		        lblSinistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		        lblSinistro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		        lblSinistro.setAlignmentX(Component.LEFT_ALIGNMENT);
		        panel.add(lblSinistro);
		    }
		
	
		btnVoltar.addActionListener(e -> {
			AreaCliente areaCliente = new AreaCliente(idRecebido);
			areaCliente.show();
			frame.dispose();
		});

		
	}

	/**formata uma linha com dados relacionados ao sinistro para exibição
	 * @param Sinistro - sinistro
	 * @return String - tupla do sinistro
	 * */
	private String formatarSinistro(Sinistro sinistro) {
	    Connection connection = new ConnectionFactory().conectar();

	    SeguroDAO seguroDAO = new SeguroDAO(connection);
	    ClienteDAO clienteDAO = new ClienteDAO(connection);

	    return String.format(
	        "ID Sinistro: %d | Tipo: %s | Data: %s | Descrição: %s | Status: %s | "
	        + "ID Seguro: %d | Prêmio Seguro: R$ %.2f | Data Início Seguro: %s | "
	        + "ID Cliente: %d | Nome Cliente: %s | CPF Cliente: %s",
	        sinistro.getIdSinistro(),
	        sinistro.getTipoSinistro(),
	        sinistro.getDataSinistro(),
	        sinistro.getDescricao(),
	        sinistro.isStatus() ? "resolvido" : "não resolvido",
	        sinistro.getIdSeguro(),
	        seguroDAO.selectById(sinistro.getIdSeguro()).getValorPremio(),
	        seguroDAO.selectById(sinistro.getIdSeguro()).getDataInicio(),
	        sinistro.getIdCliente(),
	        clienteDAO.selectById(sinistro.getIdCliente()).getNome(),
	        clienteDAO.selectById(sinistro.getIdCliente()).getCpf()
	    );
	}



	public void show() {
		frame.setVisible(true);
	}
	

}
