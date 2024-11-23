package br.com.tokio.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DuvidasFrequentes {

    private JFrame frame;

    // Perguntas e respostas
    private String[][] perguntasRespostas = {
        {"O que é seguro residencial?", "O seguro residencial é um tipo de seguro que cobre danos à sua residência, como incêndios , desastres naturais e imprevistos domésticos."},
        {"Quais são as vantagens de contratar um seguro residencial?", "O seguro residencial oferece uma ampla gama de benefícios que ajudam a proteger sua casa, seus bens e proporcionar tranquilidade para você e sua família. Ele é uma forma de proteção contra imprevistos, cobrindo danos causados por eventos como incêndios, explosões, alagamentos, quedas de raios e vendavais. Dessa forma, você garante que sua casa esteja protegida contra adversidades que podem ocorrer a qualquer momento. \nAlém disso, nossos seguros incluem serviços de assistência emergencial, como chaveiro, encanador, eletricista e até reparos em eletrodomésticos. Esses serviços ajudam a resolver problemas do dia a dia com rapidez e eficiência, sem que você precise buscar por especialistas por conta própria."},
        {"O que o seguro residencial cobre?", "O seguro cobre danos causados por incêndios, desastres naturais e outros imprevistos."},
        {"Como faço o login no sistema como cliente?", "Para acessar como cliente, use o seu cpf e senha cadastrados."},
        {"Como faço o login no sistema como funcionário?", "Para acessar como funcionário, use o login fornecido pela empresa e sua senha."},
        {"Deseja fazer login como cliente ou funcionário?", "Por favor, retorne a tela inicial e escolha se logar na área do cliente ou na área do funcionário."},
        {"Deseja contratar um seguro residencial?", "Retorne a tela inicial e clique no botão \"Contrate Aqui\"."},
    };

    private String[] categorias = {"Seguro Residencial", "Sistema", "Opções de Direcionamento"};
    private String[][] perguntasCategorias = {
        {"O que é seguro residencial?", "Quais são as vantagens de contratar um seguro residencial?", "O que o seguro residencial cobre?"},
        {"Como faço o login no sistema como cliente?", "Como faço o login no sistema como funcionário?"},
        {"Deseja fazer login como cliente ou funcionário?", "Deseja contratar um seguro residencial?"}
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DuvidasFrequentes window = new DuvidasFrequentes();
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
    public DuvidasFrequentes() {
        initialize();
    }

    /**
     * Inicializa a tela 'duvidas frequentes' onde o usuário pode selecionar entre 3 categorias de perguntas relacionadas ao seguro e ao sistema
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

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

        JLabel lblAreaDoCliente = new JLabel("Dúvidas frequentes");
        lblAreaDoCliente.setForeground(new Color(255, 255, 255));
        lblAreaDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblAreaDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 27));
        lblAreaDoCliente.setBounds(249, 15, 286, 69);
        panelHeader.add(lblAreaDoCliente);

        // -------------------------------------------
        
        JPanel panel = new JPanel();
        panel.setBounds(107, 100, 569, 461);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // ScrollPane para respostas
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 569, 291);
        panel.add(scrollPane);

        // JLabel para exibir as respostas
        JLabel lblResposta = new JLabel();
        lblResposta.setVerticalAlignment(SwingConstants.TOP);
        lblResposta.setOpaque(true);
        lblResposta.setBackground(new Color(240, 240, 240));
        lblResposta.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true));
        lblResposta.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(lblResposta);

        JPanel panelPerguntas = new JPanel();
        panelPerguntas.setBounds(0, 302, 569, 159);
        panel.add(panelPerguntas);
        panelPerguntas.setLayout(null);

        JComboBox<String> comboCategorias = new JComboBox<>(categorias);
        comboCategorias.setBounds(60, 11, 194, 23);
        comboCategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelPerguntas.add(comboCategorias);

        JComboBox<String> comboPerguntas = new JComboBox<>();
        comboPerguntas.setBounds(20, 69, 539, 25);
        comboPerguntas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelPerguntas.add(comboPerguntas);

        JButton btnMostrarResposta = new JButton("Mostrar Resposta");
        btnMostrarResposta.setBounds(314, 10, 194, 25);
        btnMostrarResposta.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelPerguntas.add(btnMostrarResposta);

        // Ação ao selecionar uma categoria
        comboCategorias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedCategoryIndex = comboCategorias.getSelectedIndex();
                comboPerguntas.removeAllItems();
                for (String pergunta : perguntasCategorias[selectedCategoryIndex]) {
                    comboPerguntas.addItem(pergunta);
                }
            }
        });

        // Evento para mostrar a resposta
        btnMostrarResposta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String perguntaSelecionada = (String) comboPerguntas.getSelectedItem();
                String resposta = getResposta(perguntaSelecionada);
                lblResposta.setText("<html><p style='width: 410px; padding: 5px;'>" + resposta + "</p></html>");
                SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
            }
        });

        // Inicializar as perguntas automaticamente
        comboCategorias.setSelectedIndex(0);
        comboCategorias.getActionListeners()[0].actionPerformed(null);

        // Evento para retornar à tela inicial
        btnLogo.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.show();
            frame.dispose();
        });
    }

    /** retorna a resposta para a pergunta
     * @return String - resposta
	 * */
    private String getResposta(String pergunta) {
        for (String[] pr : perguntasRespostas) {
            if (pr[0].equals(pergunta)) {
                return pr[1];
            }
        }
        return "Resposta não encontrada.";
    }

    public void show() {
        frame.setVisible(true);
    }
}
