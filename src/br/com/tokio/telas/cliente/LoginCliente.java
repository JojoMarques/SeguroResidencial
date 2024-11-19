package br.com.tokio.telas.cliente;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.tokio.dao.ClienteDAO;
import br.com.tokio.telas.TelaInicial;

public class LoginCliente {

    private JFrame frame;
    private JTextField txtCpf;
    private JPasswordField txtSenha;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginCliente window = new LoginCliente();
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
    public LoginCliente() {
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

        // Label para CPF
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(200, 200, 80, 25); // Posição e tamanho (x, y, largura, altura)
        frame.getContentPane().add(lblCpf);

        // Campo de texto para CPF
        txtCpf = new JTextField();
        txtCpf.setBounds(300, 200, 200, 25); // Posição e tamanho (x, y, largura, altura)
        frame.getContentPane().add(txtCpf);
        txtCpf.setColumns(10);

        // Label para Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(200, 250, 80, 25);
        frame.getContentPane().add(lblSenha);

        // Campo de texto para Senha
        txtSenha = new JPasswordField();
        txtSenha.setBounds(300, 250, 200, 25);
        frame.getContentPane().add(txtSenha);

        // Botão de Login
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(300, 300, 200, 30);
        frame.getContentPane().add(btnLogin);

        // Evento de clique no botão de login
        btnLogin.addActionListener(e -> {
            String cpf = txtCpf.getText();
            String senha = new String(txtSenha.getPassword());
        });

        // Botão para retornar à tela inicial
        JButton btnTelaInicial = new JButton("Tela inicial");
        btnTelaInicial.setBounds(620, 500, 139, 40);
        frame.getContentPane().add(btnTelaInicial);

        // Evento para voltar à tela inicial
        btnTelaInicial.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.show(); // Mostra a nova tela
            frame.dispose(); // Fecha a tela atual
        });
        
     // Evento de clique no botão de login
        btnLogin.addActionListener(e -> {
            String cpf = txtCpf.getText();
            String senha = new String(txtSenha.getPassword());

            // Instanciar o ClienteDAO e verificar a autenticação
            ClienteDAO clienteDAO = new ClienteDAO();
            int resultadoAutenticacao = clienteDAO.autenticacao(cpf, senha); 

            if (resultadoAutenticacao == 1) {
                // Se CPF e Senha estiverem corretos, direciona para a próxima tela
                AreaCliente areaCliente = new AreaCliente();
                areaCliente.show(); // Mostra a nova tela
                frame.dispose(); // Fecha a tela atual
            } else {
                // Exibe mensagem de erro caso o CPF ou senha estejam incorretos
                JOptionPane.showMessageDialog(frame, "CPF ou Senha incorretos!", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

   
    public void show() {
        frame.setVisible(true);
    }
}
