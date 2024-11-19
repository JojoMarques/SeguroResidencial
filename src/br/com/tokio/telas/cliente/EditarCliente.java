package br.com.tokio.telas.cliente;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class EditarCliente {

    private JFrame frame;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JTextField txtTelefone;

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

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Título da tela
        JLabel lblTitulo = new JLabel("Editar Informações do Cliente");
        lblTitulo.setBounds(300, 50, 200, 30); // Posição na janela
        frame.getContentPane().add(lblTitulo);

        // Campo para editar Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(200, 120, 80, 25); // Posição na janela
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(300, 120, 200, 25); // Posição na janela
        frame.getContentPane().add(txtNome);

        // Campo para editar E-mail
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(200, 170, 80, 25); // Posição na janela
        frame.getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(300, 170, 200, 25); // Posição na janela
        frame.getContentPane().add(txtEmail);

        // Campo para editar Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(200, 220, 80, 25); // Posição na janela
        frame.getContentPane().add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(300, 220, 200, 25); // Posição na janela
        frame.getContentPane().add(txtSenha);

        // Campo para editar Telefone
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(200, 270, 80, 25); // Posição na janela
        frame.getContentPane().add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(300, 270, 200, 25); // Posição na janela
        frame.getContentPane().add(txtTelefone);

        // Botão para salvar alterações
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(300, 350, 100, 30); // Posição na janela
        frame.getContentPane().add(btnSalvar);

        // Evento do botão "Salvar"
        btnSalvar.addActionListener(e -> {
            // Simulação de atualização dos dados
            String nome = txtNome.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());
            String telefone = txtTelefone.getText();
            
            // dar um update no banco.

        });

        // Botão para retornar à Área do Cliente
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(620, 500, 139, 40); // Posição na janela
        frame.getContentPane().add(btnVoltar);

        // Evento para voltar à Área do Cliente
        btnVoltar.addActionListener(e -> {
            AreaCliente areaCliente = new AreaCliente();
            areaCliente.show(); // Mostra a tela da Área do Cliente
            frame.dispose(); // Fecha a tela atual
        });
    }

    /**
     * Método para exibir a tela.
     */
    public void show() {
        frame.setVisible(true);
    }
}
