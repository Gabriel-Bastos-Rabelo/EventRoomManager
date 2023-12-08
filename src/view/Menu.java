package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.Funcionalidades;
import models.Fixa;
import models.Eventual;
import models.Solicitacao;

public class Menu { 
    private Funcionalidades funcionalidades;
    public Menu(){
        funcionalidades = new Funcionalidades();
    }
    
    public void mostrarMenu(){
        while(true){
            System.out.println("1- Cadastrar sala");
            System.out.println("2- Cadastrar Auditório");
            System.out.println("3- Efetuar solicitação fixa");
            System.out.println("4- Efetuar solicitação eventual");
            System.out.println("5- Gerar relatório por sala");
            System.out.println("6- Gerar relatório por curso");
            System.out.println("7- Sair");

            Scanner entrada = new Scanner(System.in);

            try{

                int opcao = entrada.nextInt();
                entrada.nextLine();
                if(opcao == 7){
                    break;
                }
                switch(opcao){
                    case 1:
                        System.out.println("Informe o nome da sala");
                        String nomeSala = entrada.nextLine();
                        System.out.println("Informe a capacidade da sala");
                        int capacidadeSala = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe a localização da sala");
                        String localizacaoSala = entrada.nextLine();

                        funcionalidades.cadastroSala(capacidadeSala, nomeSala, localizacaoSala);
                        System.out.println("Cadastro de sala efetuado com sucesso!");
                        break;
                    case 2:
                        System.out.println("Informe o nome do auditório");
                        String nomeAuditorio = entrada.nextLine();
                        System.out.println("Informe a capacidade do auditório");
                        int capacidadeAuditorio = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe a localização do auditório");
                        String localizacaoAuditorio = entrada.nextLine();

                        funcionalidades.cadastroAuditorio(capacidadeAuditorio, nomeAuditorio, localizacaoAuditorio);
                        System.out.println("Cadastro do auditório efetuado com sucesso!");
                        break;

                    case 3:
                        //int ano, int semestre, String curso, String disciplina, int vagas, String horario
                        System.out.println("Informe o ano");
                        int anoSolicitacaoFixa = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe o semestre");
                        int semestreSolicitacaoFixa = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe o curso");
                        String cursoSolicitacaoFixa = entrada.nextLine();
                        System.out.println("Informe a disciplina");
                        String disciplinaSolicitacaoFixa = entrada.nextLine();
                        System.out.println("Informe o número de vagas");
                        int vagasSolicitacaoFixa = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe o horário");
                        String horarioSolicitacaoFixa = entrada.nextLine();

                        if(funcionalidades.solicitacaoFixa(anoSolicitacaoFixa, semestreSolicitacaoFixa, cursoSolicitacaoFixa, disciplinaSolicitacaoFixa, vagasSolicitacaoFixa, horarioSolicitacaoFixa)){
                            System.out.println("Solicitação atendida com sucesso");
                        }
                        else{
                            System.out.println("Não foi possível atender a solicitação");
                        }
                        break;
                    case 4:
                        //int ano, int semestre, String curso, String finalidade, int vagas, String horario
                        System.out.println("Informe o ano");
                        int anoSolicitacaoEventual = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe o semestre");
                        int semestreSolicitacaoEventual = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe o curso");
                        String cursoSolicitacaoEventual = entrada.nextLine();
                        System.out.println("Informe a finalidade");
                        String finalidadeSolicitacaoEventual = entrada.nextLine();
                        System.out.println("Informe o número de vagas");
                        int vagasSolicitacaoEventual = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Informe o horário");
                        String horarioSolicitacaoEventual = entrada.nextLine();

                        if(funcionalidades.solicitacaoEventual(anoSolicitacaoEventual, semestreSolicitacaoEventual, cursoSolicitacaoEventual, finalidadeSolicitacaoEventual, vagasSolicitacaoEventual, horarioSolicitacaoEventual)){
                            System.out.println("Solicitação atendida com sucesso");
                        }
                        else{
                            System.out.println("Não foi possível atender a solicitação");
                        }
                        break;

                    case 5:
                        System.out.println("Informe o nome do espaço");
                        String nomeEspacoRelatorio = entrada.nextLine();

                        Hashtable<String, Solicitacao> solicitacoes = funcionalidades.gerarRelatorioPorEspaco(nomeEspacoRelatorio);

                        if(solicitacoes != null){
                            Enumeration<String> chaves = solicitacoes.keys();
                            if(solicitacoes.size() > 0){
                                while(chaves.hasMoreElements()){
                                    String chave = chaves.nextElement();
                                    Solicitacao solicitacao = solicitacoes.get(chave);

                                    if(solicitacao instanceof Fixa){

                                        System.out.println("Ano: " + solicitacao.getAno() + ", Semestre: " + solicitacao.getSemestre() + ", Curso: " + solicitacao.getCurso() + ", disciplina: " + (((Fixa) solicitacao).getDisciplina()) + ", Vagas: " + (((Fixa) solicitacao).getVagas()) + ", Horários: " + (((Fixa) solicitacao).getHorario()));
                                    }

                                    else{

                                        System.out.println("Ano: " + solicitacao.getAno() + ", Semestre: " + solicitacao.getSemestre() + ", Curso: " + solicitacao.getCurso() + ", finalidade: " + (((Eventual) solicitacao).getFinalidade()) + ", Vagas: " + (((Eventual) solicitacao).getVagas()) + ", Horários: " + (((Eventual) solicitacao).getHorario()));
                                    }
                                }
                
                            }
                            else{
                                System.out.println("Não houve nenhuma solicitação para esse espaço");
                            }
                        }
                        else{
                            System.out.println("Nenhum espaço com esse nome foi encontrado!");
                        }

                        break;

                    case 6:
                        System.out.println("Digite o nome do curso:");
                        String nomeCursoRelatorio = entrada.nextLine();

                        ArrayList<Solicitacao> solicitacoesPorCurso = funcionalidades.gerarRelatorioPorCurso(nomeCursoRelatorio);

                        for(Solicitacao s : solicitacoesPorCurso){
                            if(s instanceof Fixa){

                                System.out.println("Ano: " + s.getAno() + ", Semestre: " + s.getSemestre() + ", Curso: " + s.getCurso() + ", disciplina: " + (((Fixa) s).getDisciplina()) + ", Vagas: " + (((Fixa) s).getVagas()) + ", Horários: " + (((Fixa) s).getHorario()));
                            }

                            else{

                                System.out.println("Ano: " + s.getAno() + ", Semestre: " + s.getSemestre() + ", Curso: " + s.getCurso() + ", finalidade: " + (((Eventual) s).getFinalidade()) + ", Vagas: " + (((Eventual) s).getVagas()) + ", Horários: " + (((Eventual) s).getHorario()));
                            }
                            
                        }
                        
                        


                }



            }catch(InputMismatchException exception){
                System.out.println("Digite o tipo pedido!");
            }
            
        }
    }
}
