package br.com.tokio.view.cliente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.tokio.connection.ConnectionFactory;
import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.model.Cliente;
import br.com.tokio.view.TelaInicial;

import javax.swing.JPasswordField;

public class EditarCliente {

    private JFrame frame;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JTextField txtTelefone;
    private int idRecebido;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditarCliente window = new EditarCliente();
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
    public EditarCliente() {
        initialize();
    }
    
    public EditarCliente(int idCliente) {
    	this.idRecebido = idCliente;
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

		JLabel lblRelatorio = new JLabel("Editar");
		lblRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorio.setForeground(Color.WHITE);
		lblRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblRelatorio.setBackground(Color.WHITE);
		lblRelatorio.setBounds(266, 18, 251, 63);
		panelHeader.add(lblRelatorio);

		// --------------------------------------------
		
		JPanel panel = new JPanel();
        panel.setBounds(130, 111, 523, 418);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Editar Informações:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitulo.setBounds(161, 36, 200, 30); // Posição na janela
        panel.add(lblTitulo);
        
    	Connection connection = new ConnectionFactory().conectar();
		ClienteDAO clienteDAO = new ClienteDAO(connection);
    	Cliente cliente = new Cliente();
		cliente = clienteDAO.selectById(idRecebido);
        
        // Campo para editar Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNome.setBounds(82, 106, 80, 25); // Posição na janela
        panel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNome.setBounds(161, 106, 200, 25); // Posição na janela
        txtNome.setText(cliente.getNome());
        panel.add(txtNome);

        // Campo para editar E-mail
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEmail.setBounds(82, 156, 80, 25); // Posição na janela
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtEmail.setBounds(161, 156, 200, 25); // Posição na janela
        txtEmail.setText(cliente.getEmail());
        panel.add(txtEmail);

        // Campo para editar Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblSenha.setBounds(82, 206, 80, 25); // Posição na janela
        panel.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtSenha.setBounds(161, 206, 200, 25); // Posição na janela
        txtSenha.setText(cliente.getSenhaCliente());
        panel.add(txtSenha);

        // Campo para editar Telefone
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTelefone.setBounds(82, 256, 80, 25); // Posição na janela
        panel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTelefone.setBounds(161, 256, 200, 25); // Posição na janela
        txtTelefone.setText(cliente.getTelefone());
        panel.add(txtTelefone);

        // Botão para salvar alterações
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(225, 193, 85));
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalvar.setBounds(161, 327, 200, 30); // Posição na janela
        panel.add(btnSalvar);
        
        
     // Evento para voltar à tela inicial
		btnLogoTelaInicial.addActionListener(e -> {
			TelaInicial telaInicial = new TelaInicial();
			telaInicial.show(); // Mostra a nova tela
			frame.dispose(); // Fecha a tela atual
		});

        // Evento do botão "Salvar"
        btnSalvar.addActionListener(e -> {
        	Cliente c = clienteDAO.selectById(idRecebido);
            // Simulação de atualização dos dados
        	c.setNome(txtNome.getText());
            c.setEmail(txtEmail.getText());
            c.setSenhaCliente(new String(txtSenha.getPassword()));
            c.setTelefone(txtTelefone.getText());
            clienteDAO.update(c);
            AreaCliente areaCliente = new AreaCliente(idRecebido);
            areaCliente.show();
            frame.dispose();
        });
    }

    /**
     * Método para exibir a tela.
     */
    public void show() {
        frame.setVisible(true);
    }
}
