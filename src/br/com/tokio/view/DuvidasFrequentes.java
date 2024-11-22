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
        // Perguntas sobre o seguro residencial
        {"O que é seguro residencial?", "O seguro residencial é um tipo de seguro que cobre danos à sua residência, como incêndios, furtos e desastres naturais."},
        {"Quais são as vantagens de contratar um seguro residencial?", "O seguro residencial oferece proteção contra danos, roubos e ajuda a garantir a segurança do seu lar."},
        {"O que o seguro residencial cobre?", "O seguro cobre danos causados por incêndios, desastres naturais, furtos e outros imprevistos."},
        {"Como posso contratar um seguro residencial?", "Você pode contratar um seguro residencial diretamente pelo nosso site ou com um corretor."},
        {"O que não é coberto por um seguro residencial?", "O seguro não cobre danos causados por negligência, guerra ou danos em áreas externas não relacionadas à residência."},
        {"Como posso fazer uma cotação de seguro residencial?", "Você pode solicitar uma cotação através do nosso site, preenchendo um formulário simples."},
        {"Como funciona o processo de sinistro no seguro residencial?", "Em caso de sinistro, basta entrar em contato conosco para abrir uma solicitação de reparo ou compensação."},
        {"Qual é o valor do prêmio do seguro residencial?", "O valor do prêmio varia conforme o tipo de cobertura e o valor da residência."},
        {"Posso incluir itens de valor no meu seguro residencial?", "Sim, você pode incluir itens de valor, como joias, eletrônicos e outros bens valiosos."},
        {"O que fazer em caso de um sinistro no meu imóvel?", "Em caso de sinistro, entre em contato conosco imediatamente para registrar o ocorrido."},

        // Perguntas sobre o sistema
        {"Como faço o login no sistema como cliente?", "Para acessar como cliente, use o seu e-mail e senha cadastrados."},
        {"Como faço o login no sistema como funcionário?", "Para acessar como funcionário, use o login fornecido pela empresa e sua senha."},
        {"Como posso recuperar minha senha?", "Se você esqueceu sua senha, clique em 'Esqueci minha senha' na página de login."},
        {"Qual é a diferença entre o login de cliente e o login de funcionário?", "O login de cliente acessa informações relacionadas ao seu seguro, enquanto o login de funcionário tem acesso ao sistema administrativo."},
        {"Como posso alterar meus dados no sistema?", "Você pode alterar seus dados acessando a seção 'Meus Dados' após o login."},
        {"O que fazer se eu encontrar um erro no sistema?", "Se encontrar um erro, entre em contato com nossa equipe de suporte para reportar o problema."},
        {"Onde posso acessar o histórico de sinistros no sistema?", "Você pode acessar o histórico de sinistros na seção 'Meus Sinistros' após o login."},
        {"Como consultar as apólices de seguro no sistema?", "As apólices de seguro estão disponíveis na seção 'Meus Seguros'."},

        // Opções de direcionamento
        {"Deseja fazer login como cliente ou funcionário?", "Por favor, escolha entre 'Cliente' ou 'Funcionário' para prosseguir."},
        {"Deseja contratar um seguro residencial?", "Sim! Clique aqui para começar a contratar seu seguro residencial."},
        {"Caso precise de assistência com o seu seguro ou tenha dúvidas, posso te direcionar para a área de atendimento ao cliente.", "Por favor, aguarde enquanto direciono você para a área de atendimento."},
        {"Se você já tem um login no sistema, posso te ajudar a acessar sua conta.", "Por favor, forneça seu login e senha para prosseguir."},
        {"Caso precise de ajuda para realizar um sinistro, posso te direcionar à área de sinistros do sistema.", "Por favor, aguarde enquanto direciono você para a área de sinistros."}
    };

    // Arrays para categorizar as perguntas
    private String[] categorias = {"Seguro Residencial", "Sistema", "Opções de Direcionamento"};
    private String[][] perguntasCategorias = {
        {"O que é seguro residencial?", "Quais são as vantagens de contratar um seguro residencial?", "O que o seguro residencial cobre?", "Como posso contratar um seguro residencial?", "O que não é coberto por um seguro residencial?", "Como posso fazer uma cotação de seguro residencial?", "Como funciona o processo de sinistro no seguro residencial?", "Qual é o valor do prêmio do seguro residencial?", "Posso incluir itens de valor no meu seguro residencial?", "O que fazer em caso de um sinistro no meu imóvel?"},
        {"Como faço o login no sistema como cliente?", "Como faço o login no sistema como funcionário?", "Como posso recuperar minha senha?", "Qual é a diferença entre o login de cliente e o login de funcionário?", "Como posso alterar meus dados no sistema?", "O que fazer se eu encontrar um erro no sistema?", "Onde posso acessar o histórico de sinistros no sistema?", "Como consultar as apólices de seguro no sistema?"},
        {"Deseja fazer login como cliente ou funcionário?", "Deseja contratar um seguro residencial?", "Caso precise de assistência com o seu seguro ou tenha dúvidas, posso te direcionar para a área de atendimento ao cliente.", "Se você já tem um login no sistema, posso te ajudar a acessar sua conta.", "Caso precise de ajuda para realizar um sinistro, posso te direcionar à área de sinistros do sistema."}
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
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 200, 800, 600);
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

        JPanel panel = new JPanel();
        panel.setBounds(107, 100, 569, 461);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panelPerguntas = new JPanel();
        panelPerguntas.setBounds(0, 302, 569, 159);
        panel.add(panelPerguntas);

        // ScrollPane para respostas
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 569, 291);
        panel.add(scrollPane);
        
        JPanel panelRespostas = new JPanel();
        scrollPane.setViewportView(panelRespostas);
        panelRespostas.setLayout(new BoxLayout(panelRespostas, BoxLayout.Y_AXIS)); // Organiza as respostas verticalmente
        panelRespostas.setPreferredSize(new java.awt.Dimension(550, 200));
        panelPerguntas.setLayout(null);


        // ComboBoxes para as categorias
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);
        comboCategorias.setBounds(78, 11, 194, 23);
        comboCategorias.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelPerguntas.add(comboCategorias);

        JComboBox<String> comboPerguntas = new JComboBox<>();
        comboPerguntas.setBounds(20, 69, 539, 25);
        comboPerguntas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panelPerguntas.add(comboPerguntas);

        // Botão para mostrar a resposta
        JButton btnMostrarResposta = new JButton("Mostrar Resposta");
        btnMostrarResposta.setBounds(350, 10, 139, 25);
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

        // Ação ao selecionar uma pergunta
        comboPerguntas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Apenas seleciona a pergunta sem mostrar a resposta
            }
        });

        // Evento para mostrar a resposta
        btnMostrarResposta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String perguntaSelecionada = (String) comboPerguntas.getSelectedItem();
                String resposta = getResposta(perguntaSelecionada);
                JLabel lblResposta = new JLabel("<html><p style='width: 410px;padding:5px;'>" + resposta + "</p></html>");
                lblResposta.setOpaque(true);
                lblResposta.setBackground(new Color(240, 240, 240));
                lblResposta.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true));
                lblResposta.setBounds(10, 10 + panelRespostas.getComponentCount() * 60, 550, 60);
                lblResposta.setBackground(new Color(230, 230, 250));
                lblResposta.setFont(new Font("Tahoma", Font.PLAIN, 14));
                panelRespostas.add(lblResposta);
            
				panelRespostas.setPreferredSize(
						new java.awt.Dimension(panelRespostas.getWidth(), panelRespostas.getComponentCount() * 70
				// Ajuste o valor 70 conforme necessário para espaçamento
				));

				
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				
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

    // Método que retorna a resposta da pergunta selecionada
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
