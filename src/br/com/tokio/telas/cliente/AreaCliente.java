package br.com.tokio.telas.cliente;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.tokio.telas.TelaInicial;

public class AreaCliente {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AreaCliente window = new AreaCliente();
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
    public AreaCliente() {
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

        // Painel para as informações do cliente
        JPanel panelInformacoes = new JPanel();
        panelInformacoes.setBounds(150, 100, 500, 300); // Posição e tamanho do painel
        panelInformacoes.setLayout(null); // Layout absoluto dentro do painel
        frame.getContentPane().add(panelInformacoes);

        // Título do painel
        JLabel lblTitulo = new JLabel("Informações do Cliente");
        lblTitulo.setBounds(180, 10, 200, 30); // Posição no painel
        panelInformacoes.add(lblTitulo);

        // Campo de exibição de nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 60, 80, 25); // Posição no painel
        panelInformacoes.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(150, 60, 300, 25); // Posição no painel
        txtNome.setEditable(false); // Campo apenas para exibição
        txtNome.setText("Nome do Cliente"); // Valor de exemplo
        panelInformacoes.add(txtNome);

        // Campo de exibição de CPF
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(50, 100, 80, 25); // Posição no painel
        panelInformacoes.add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(150, 100, 300, 25); // Posição no painel
        txtCpf.setEditable(false); // Campo apenas para exibição
        txtCpf.setText("123.456.789-00"); // Valor de exemplo
        panelInformacoes.add(txtCpf);

        // Botão de editar informações
        JButton btnEditarInformacoes = new JButton("Editar Informações");
        btnEditarInformacoes.setBounds(150, 200, 200, 30); // Posição no painel
        panelInformacoes.add(btnEditarInformacoes);

        // Evento para abrir a tela EditarCliente
        btnEditarInformacoes.addActionListener(e -> {
            EditarCliente editarCliente = new EditarCliente();
            editarCliente.show(); // Mostra a tela de edição
            frame.dispose(); // Fecha a tela atual
        });

        // Botão para voltar à tela inicial
        JButton btnTelaInicial = new JButton("Tela inicial");
        btnTelaInicial.setBounds(620, 500, 139, 40); // Posição na janela
        frame.getContentPane().add(btnTelaInicial);

        // Evento para retornar à tela inicial
        btnTelaInicial.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.show();
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
