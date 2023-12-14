package view;
import javax.swing.*;
import controller.Funcionalidades;
import models.Solicitacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class MenuInterface extends JFrame {
    private Funcionalidades funcionalidades;

    public MenuInterface() {
        funcionalidades = new Funcionalidades();
        criarInterface();
    }

    private void criarInterface() {
        setTitle("Sistema de Gestão de Salas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        add(criarPainelBotoes(), BorderLayout.CENTER);
        
        
        setLocationRelativeTo(null); 
    }



    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        
        Dimension buttonDimension = new Dimension(200, 40); 
    
        
        JButton btnCadastrarSala = criarBotao("Cadastrar Sala", e -> mostrarFormularioCadastroSala());
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(btnCadastrarSala);
    
        JButton btnCadastrarAuditorio = criarBotao("Cadastrar Auditório", e -> mostrarFormularioCadastroAuditorio());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnCadastrarAuditorio);
    
        JButton btnEfetuarSolicitacaoFixa = criarBotao("Efetuar solicitação fixa", e -> mostrarFormularioSolicitacaoFixa());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnEfetuarSolicitacaoFixa);
    
        JButton btnEfetuarSolicitacaoEventual = criarBotao("Efetuar solicitação Eventual", e -> mostrarFormularioSolicitacaoEventual());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnEfetuarSolicitacaoEventual);
    
        JButton btnGerarRelatorioPorSala = criarBotao("Gerar relatório por sala", e -> mostrarFormularioRelatorioPorEspaco());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnGerarRelatorioPorSala);
    
        JButton btnGerarRelatorioPorCurso = criarBotao("Gerar relatório por curso", e -> mostrarFormularioRelatorioPorCurso());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnGerarRelatorioPorCurso);
    
        return panel;
    }
    
    private JButton criarBotao(String texto, ActionListener actionListener) {

        Dimension buttonDimension = new Dimension(200, 40);
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT); 
        botao.setPreferredSize(buttonDimension);
        botao.setMaximumSize(buttonDimension);
        botao.addActionListener(actionListener);
        return botao;
    }



    private void mostrarFormularioCadastroSala() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        inputPanel.add(new JLabel("Nome da Sala:"));
        JTextField nomeSala = new JTextField();
        inputPanel.add(nomeSala);
    
        inputPanel.add(new JLabel("Capacidade da Sala:"));
        JTextField capacidadeSala = new JTextField();
        inputPanel.add(capacidadeSala);
    
        inputPanel.add(new JLabel("Localização da Sala:"));
        JTextField localizacaoSala = new JTextField();
        inputPanel.add(localizacaoSala);
    
        panel.add(inputPanel);
    

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
        
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nome = nomeSala.getText();
                String capacidade = capacidadeSala.getText();
                String localizacao = localizacaoSala.getText();
    
                
                try {
                    int capacidadeNumerica = Integer.parseInt(capacidade);
                    funcionalidades.cadastroSala(capacidadeNumerica, nome, localizacao);
                    JOptionPane.showMessageDialog(panel, "Cadastro da sala realizado com sucesso!");

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(panel, "Por favor, insira um número válido para a capacidade.");
                }
            }
        });
        panel.add(submitButton);
    

        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        panel.add(btnVoltar);
    
        
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }
    

    private void mostrarFormularioCadastroAuditorio() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        inputPanel.add(new JLabel("Nome do Auditório:"));
        JTextField nomeAuditorio = new JTextField();
        inputPanel.add(nomeAuditorio);
    
        inputPanel.add(new JLabel("Capacidade do Auditório:"));
        JTextField capacidadeAuditorio = new JTextField();
        inputPanel.add(capacidadeAuditorio);
    
        inputPanel.add(new JLabel("Localização do Auditório:"));
        JTextField localizacaoAuditorio = new JTextField();
        inputPanel.add(localizacaoAuditorio);
    
        panel.add(inputPanel);
    

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
        
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nome = nomeAuditorio.getText();
                String capacidade = capacidadeAuditorio.getText();
                String localizacao = localizacaoAuditorio.getText();
    
                
                try {
                    int capacidadeNumerica = Integer.parseInt(capacidade);
                    funcionalidades.cadastroAuditorio(capacidadeNumerica, nome, localizacao);
                    JOptionPane.showMessageDialog(panel, "Cadastro do auditório realizado com sucesso!");

                } catch (NumberFormatException ex) {
                    
                    JOptionPane.showMessageDialog(panel, "Por favor, insira um número válido para a capacidade.");
                }
            }
        });
        panel.add(submitButton);
    

        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        panel.add(btnVoltar);
    
        
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }
    


    private void mostrarFormularioSolicitacaoFixa() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        inputPanel.add(new JLabel("Ano:"));
        JTextField anoSolicitacao = new JTextField();
        inputPanel.add(anoSolicitacao);
    
        inputPanel.add(new JLabel("Semestre:"));
        JTextField semestreSolicitacao = new JTextField();
        inputPanel.add(semestreSolicitacao);
    
        inputPanel.add(new JLabel("Curso:"));
        JTextField cursoSolicitacao = new JTextField();
        inputPanel.add(cursoSolicitacao);
    
        inputPanel.add(new JLabel("Disciplina:"));
        JTextField disciplinaSolicitacao = new JTextField();
        inputPanel.add(disciplinaSolicitacao);
    
        inputPanel.add(new JLabel("Número de Vagas:"));
        JTextField vagasSolicitacao = new JTextField();
        inputPanel.add(vagasSolicitacao);
    
        inputPanel.add(new JLabel("Horário:"));
        JTextField horarioSolicitacao = new JTextField();
        inputPanel.add(horarioSolicitacao);
    
        panel.add(inputPanel);
    
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
        
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ano = anoSolicitacao.getText();
                String semestre = semestreSolicitacao.getText();
                String curso = cursoSolicitacao.getText();
                String disciplina = disciplinaSolicitacao.getText();
                String vagas = vagasSolicitacao.getText();
                String horario = horarioSolicitacao.getText();
    
                
                try {
                    int vagasNumerica = Integer.parseInt(vagas);
                    int anoNumerico = Integer.parseInt(ano);
                    int semestreNumerico = Integer.parseInt(semestre);
                    boolean res = funcionalidades.solicitacaoFixa(anoNumerico, semestreNumerico, curso, disciplina, vagasNumerica, horario);

                    if(!res){
                        JOptionPane.showMessageDialog(panel, "Não tem salas disponíveis para a sua solicitação!");
                    }
                    else{
                        JOptionPane.showMessageDialog(panel, "Cadastro da solicitação fixa realizado com sucesso!");
                    }
                    

                } catch (NumberFormatException ex) {
                    
                    JOptionPane.showMessageDialog(panel, "Por favor, insira um número válido.");
                }
            }
        });
        panel.add(submitButton);
    

        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    

        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        panel.add(btnVoltar);
    
        
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }
    


    private void mostrarFormularioSolicitacaoEventual() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        inputPanel.add(new JLabel("Ano:"));
        JTextField anoSolicitacao = new JTextField();
        inputPanel.add(anoSolicitacao);
    
        inputPanel.add(new JLabel("Semestre:"));
        JTextField semestreSolicitacao = new JTextField();
        inputPanel.add(semestreSolicitacao);
    
        inputPanel.add(new JLabel("Curso:"));
        JTextField cursoSolicitacao = new JTextField();
        inputPanel.add(cursoSolicitacao);
    
        inputPanel.add(new JLabel("Finalidade:"));
        JTextField finalidadeSolicitacao = new JTextField();
        inputPanel.add(finalidadeSolicitacao);
    
        inputPanel.add(new JLabel("Número de Vagas:"));
        JTextField vagasSolicitacao = new JTextField();
        inputPanel.add(vagasSolicitacao);
    
        inputPanel.add(new JLabel("Horário:"));
        JTextField horarioSolicitacao = new JTextField();
        inputPanel.add(horarioSolicitacao);
    
        panel.add(inputPanel);
    
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
        
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ano = anoSolicitacao.getText();
                String semestre = semestreSolicitacao.getText();
                String curso = cursoSolicitacao.getText();
                String finalidade = finalidadeSolicitacao.getText();
                String vagas = vagasSolicitacao.getText();
                String horario = horarioSolicitacao.getText();
    
                
                try {
                    int anoNumerico = Integer.parseInt(ano);
                    int semestreNumerico = Integer.parseInt(semestre);
                    int vagasNumerica = Integer.parseInt(vagas);
                    boolean res = funcionalidades.solicitacaoEventual(anoNumerico, semestreNumerico, curso, finalidade, vagasNumerica, horario);

                    if(!res){
                        JOptionPane.showMessageDialog(panel, "Não tem salas disponíveis para a sua solicitação!");
                    }
                    else{
                        JOptionPane.showMessageDialog(panel, "Cadastro da solicitação fixa realizado com sucesso!");
                    }
    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Por favor, insira números válidos nos campos apropriados.");
                }
            }
        });
        panel.add(submitButton);
    

        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        panel.add(btnVoltar);
    

        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }


    private void mostrarFormularioRelatorioPorEspaco() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        inputPanel.add(new JLabel("Nome da Sala:"));
        JTextField nomeSala = new JTextField();
        inputPanel.add(nomeSala);
    
        panel.add(inputPanel);
    

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
        
        JButton submitButton = new JButton("Gerar Relatório");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nome = nomeSala.getText();
                System.out.println(nome);
    
                
                Hashtable<String, Solicitacao> relatorio = funcionalidades.gerarRelatorioPorEspaco(nome);
    
                StringBuilder relatorioStr = new StringBuilder();
                Enumeration<String> keys = relatorio.keys();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    Solicitacao solicitacao = relatorio.get(key);
                    relatorioStr.append("Chave: ").append(key).append("\n");
                    relatorioStr.append("Detalhes da Solicitação: ").append(solicitacao.toString()).append("\n\n"); 
                }

                
                JTextArea textArea = new JTextArea(10, 40);
                textArea.setText(relatorioStr.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(panel, scrollPane, "Relatório da Sala", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel.add(submitButton);
    
        
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        panel.add(btnVoltar);
    
        
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }
    

    private void mostrarFormularioRelatorioPorCurso() {
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    
        
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); 
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        inputPanel.add(new JLabel("Nome do Curso:"));
        JTextField nomeCurso = new JTextField();
        inputPanel.add(nomeCurso);
    
        panel.add(inputPanel);
    

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    
        
        JButton submitButton = new JButton("Gerar Relatório");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String nome = nomeCurso.getText();
    
                
                ArrayList<Solicitacao> relatorio = funcionalidades.gerarRelatorioPorCurso(nome);
    
                
                if (relatorio.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Não há solicitações para o curso especificado.", "Relatório do Curso", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    StringBuilder relatorioStr = new StringBuilder();
                    for (Solicitacao solicitacao : relatorio) {
                        relatorioStr.append(solicitacao.toString()).append("\n\n");
                    }
    

                    JTextArea textArea = new JTextArea(10, 40);
                    textArea.setText(relatorioStr.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(panel, scrollPane, "Relatório do Curso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(submitButton);
    
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.addActionListener(e -> mostrarMenuPrincipal());
        panel.add(btnVoltar);
    
        
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        validate();
        repaint();
    }
    

    private void mostrarMenuPrincipal() {
        getContentPane().removeAll();
        add(criarPainelBotoes(), BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    

}