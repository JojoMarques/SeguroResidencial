package br.com.tokio.telas;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.tokio.telas.cliente.LoginCliente;
import br.com.tokio.telas.funcionario.LoginFuncionario;

public class TelaInicial {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaInicial window = new TelaInicial();
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
    public TelaInicial() {
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

        // Botão "Área do Cliente"
        JButton btnCliente = new JButton("Área do Cliente");
        btnCliente.setBounds(437, 43, 139, 40); // Posição e tamanho (x, y, largura, altura)
        frame.getContentPane().add(btnCliente);

        // Botão "Área do Funcionário"
        JButton btnFuncionario = new JButton("Área do Funcionário");
        btnFuncionario.setBounds(609, 43, 139, 40); // Posição e tamanho (x, y, largura, altura)
        frame.getContentPane().add(btnFuncionario);

        // Botão "Contrate Aqui"
        JButton btnContrate = new JButton("Contrate Aqui");
        btnContrate.setBounds(301, 267, 178, 50); // Posição e tamanho (x, y, largura, altura)
        frame.getContentPane().add(btnContrate);
        
        // Evento que direciona para o login do cliente
        btnCliente.addActionListener(e -> {
            // Abre a tela AreaCliente
            LoginCliente loginCliente = new LoginCliente();
            loginCliente.show(); // Mostra a nova tela
            // Fecha a tela atual (opcional)
            frame.dispose();
        });
             
        // Evento que direciona para o login do funcionário
        btnFuncionario.addActionListener(e -> {
            // Abre a tela AreaCliente
            LoginFuncionario areaFuncionario = new LoginFuncionario();
            areaFuncionario.show(); // Mostra a nova tela
            // Fecha a tela atual (opcional)
            frame.dispose();
        });
        
        // Evento que direciona para o contrate aqui
        btnContrate.addActionListener(e -> {
            // Abre a tela AreaCliente
        	ContrateAqui areaContrateAqui = new ContrateAqui();
            areaContrateAqui.show(); // Mostra a nova tela
            // Fecha a tela atual (opcional)
            frame.dispose();
        });
    }
    
    // vai exibir a tela (é chamado no click da tela inicial) 
		public void show() {
			frame.setVisible(true);
		}
}
