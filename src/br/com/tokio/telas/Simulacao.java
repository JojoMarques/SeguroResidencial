package br.com.tokio.telas;

import java.awt.EventQueue;
import javax.swing.*;

public class Simulacao {

    private JFrame frame;
    private JTextField txtCPF;
    private JTextField txtCEP;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtCobertura;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Simulacao window = new Simulacao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
//
    /**
     * Create the application.
     */
    public Simulacao() {
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

        // Label título
        JLabel lblCPF_1 = new JLabel("Dados para simulação:");
        lblCPF_1.setBounds(184, 14, 149, 25);
        frame.getContentPane().add(lblCPF_1);

        // Label e TextField para CPF
        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(50, 50, 100, 25);
        frame.getContentPane().add(lblCPF);

        txtCPF = new JTextField();
        txtCPF.setBounds(150, 50, 200, 25);
        frame.getContentPane().add(txtCPF);

        // Label e TextField para CEP
        JLabel lblCEP = new JLabel("CEP:");
        lblCEP.setBounds(50, 100, 100, 25);
        frame.getContentPane().add(lblCEP);

        txtCEP = new JTextField();
        txtCEP.setBounds(150, 100, 200, 25);
        frame.getContentPane().add(txtCEP);

        // Label e TextField para Telefone
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(50, 150, 100, 25);
        frame.getContentPane().add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(150, 150, 200, 25);
        frame.getContentPane().add(txtTelefone);

        // Label e TextField para E-mail
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(50, 200, 100, 25);
        frame.getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 200, 200, 25);
        frame.getContentPane().add(txtEmail);

        // Label e TextField para Cobertura
        JLabel lblCobertura = new JLabel("Valor cobertura:");
        lblCobertura.setBounds(50, 250, 100, 25);
        frame.getContentPane().add(lblCobertura);

        txtCobertura = new JTextField();
        txtCobertura.setBounds(150, 250, 200, 25);
        frame.getContentPane().add(txtCobertura);

        // Botão Enviar
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(199, 300, 100, 30);
        frame.getContentPane().add(btnEnviar);

        // Evento para o botão Enviar
        btnEnviar.addActionListener(e -> {
            String cpf = txtCPF.getText();
            String cep = txtCEP.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();
            String cobertura = txtCobertura.getText();

            JOptionPane.showMessageDialog(frame, "Dados enviados com sucesso!\n"
                    + "CPF: " + cpf + "\n"
                    + "CEP: " + cep + "\n"
                    + "Telefone: " + telefone + "\n"
                    + "E-mail: " + email + "\n"
                    + "Cobertura: " + cobertura);
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
    }

    public void show() {
        frame.setVisible(true);
    }
}
