package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import models.Auditorio;
import models.Espaco;
import models.Eventual;
import models.Fixa;
import models.Sala;
import models.Solicitacao;

public class Funcionalidades {

    private Hashtable<Espaco, Hashtable<String, Solicitacao>> dados;
    private Hashtable<String, Espaco> espacos;

    public Funcionalidades() {
        this.dados = new Hashtable<>();
        this.espacos = new Hashtable<>();
        this.carregar();
        this.desmarcarHorariosExpirados();
    }

    public void carregar(){
        this.carregarEspacos();
        this.carregarSolicitacoes();
    }

    public void carregarEspacos(){
        File arquivo = new File("./espacos.txt");

        try{

            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String linha = br.readLine();
                String array[] = new String[3];
                array = linha.split(";");
                if(array[0].equals("sala")){
                    Sala newSala = new Sala(Integer.parseInt(array[1]), array[2], array[3]);
                    dados.put(newSala, new Hashtable<String, Solicitacao>());
                    espacos.put(array[2], newSala);
                }
                else{
                    Auditorio newAuditorio = new Auditorio(Integer.parseInt(array[1]), array[2], array[3]);
                    dados.put(newAuditorio, new Hashtable<String, Solicitacao>());
                    espacos.put(array[2], newAuditorio);
                }
                
            }

            br.close();
            fr.close();

        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }


    }

    public void carregarSolicitacoes(){
        
        File arquivo = new File("./solicitacoes.txt");

        try{
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            while(br.ready()){
                String linha = br.readLine();
                String array[] = new String[8];
                array = linha.split(";");
                
                Espaco espaco = espacos.get(array[7]);
                
                if(array[0].equals("fixa")){
                    dados.get(espaco).put(array[6], new Fixa(Integer.parseInt(array[1]), Integer.parseInt(array[2]), array[3], array[4], Integer.parseInt(array[5]), array[6]));
                }
                else{

                    dados.get(espaco).put(array[6], new Eventual(Integer.parseInt(array[1]), Integer.parseInt(array[2]), array[3], array[4], Integer.parseInt(array[5]), array[6]));
                }
                
            }

            br.close();
            fr.close();

        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }

    }

    public boolean solicitacaoFixa(int ano, int semestre, String curso, String disciplina, int vagas, String horario) {
        ArrayList<String> horarios = this.gerarCombinacoesHorarios(horario);
        ArrayList<Espaco> espacosDisponiveis = new ArrayList<>();
        Enumeration<Espaco> e = dados.keys();

        while(e.hasMoreElements()){
            Espaco espaco = e.nextElement();
            if(this.verificarDisponibilidadeSala(espaco.getMatriz(), horarios) == true){
                espacosDisponiveis.add(espaco);
            }
        }

        if(espacosDisponiveis.size() != 0){
            Espaco melhorOpcao = this.melhorOpcao(espacosDisponiveis, vagas);
            if(melhorOpcao != null){
                this.marcarHorarios(melhorOpcao, horarios);
                dados.get(melhorOpcao).put(horario, new Fixa(ano, semestre, curso, disciplina, vagas, horario));
                File arquivo = new File("./solicitacoes.txt");
                try{
                    FileWriter fw = new FileWriter(arquivo, true);
                    BufferedWriter bw = new BufferedWriter(fw);


                   
                    bw.write("fixa" + ";" + ano + ";" + semestre + ";" + curso + ";" + disciplina + ";" + vagas + ";" + horario + ";" +  melhorOpcao.getNome() + "\n");

                    bw.close();
                    fw.close();



                }catch(IOException exception){
                    System.out.println(exception.getMessage());
                }
                return true;
            }
        }

        return false;

    }

    public boolean solicitacaoEventual(int ano, int semestre, String curso, String finalidade, int vagas,
            String horario) {
        ArrayList<String> horarios = this.gerarCombinacoesHorarios(horario);
        ArrayList<Espaco> espacosDisponiveis = new ArrayList<>();
        Enumeration<Espaco> e = dados.keys();

        while(e.hasMoreElements()){
            Espaco espaco = e.nextElement();
            if(espaco instanceof Auditorio){
                if(this.verificarDisponibilidadeSala(espaco.getMatriz(), horarios) == true){
                espacosDisponiveis.add(espaco);
            }
            }
            
        }

        if(espacosDisponiveis.size() != 0){
            Espaco melhorOpcao = this.melhorOpcao(espacosDisponiveis, vagas);
            if(melhorOpcao != null){
                this.marcarHorarios(melhorOpcao, horarios);
                dados.get(melhorOpcao).put(horario, new Eventual(ano, semestre, curso, finalidade, vagas, horario));
                File arquivo = new File("./solicitacoes.txt");
                try{
                    FileWriter fw = new FileWriter(arquivo, true);
                    BufferedWriter bw = new BufferedWriter(fw);


                   
                    bw.write("eventual" + ";" + ano + ";" + semestre + ";" + curso + ";" + finalidade + ";" + vagas + ";" + horario +  ";" + melhorOpcao.getNome() + "\n");

                    bw.close();
                    fw.close();



                }catch(IOException exception){
                    System.out.println(exception.getMessage());
                }
                return true;
            }
        }

        return false;
    }

    public void cadastroSala(int capacidade, String nome, String localizacao) {
        Sala newSala = new Sala(capacidade, nome, localizacao);
        dados.put(newSala, new Hashtable<String, Solicitacao>());
        espacos.put(nome, newSala);
        File arquivo = new File("./espacos.txt");
        try{
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("sala" + ";" + capacidade + ";" + nome + ";" + localizacao + "\n");

            bw.close();
            fw.close();
        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }


    }

    public void cadastroAuditorio(int capacidade, String nome, String localizacao) {
        Auditorio newAuditorio = new Auditorio(capacidade, nome, localizacao);
        dados.put(newAuditorio, new Hashtable<String, Solicitacao>());
        espacos.put(nome, newAuditorio);
        File arquivo = new File("./espacos.txt");
        try{
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("auditório" + ";" + capacidade + ";" + nome + ";" + localizacao + "\n");

            bw.close();
            fw.close();
        }catch(IOException exception){
            System.out.println(exception.getMessage());
        }

    }


    public ArrayList<String> gerarCombinacoesHorarios(String horario) {
        ArrayList<String> combinacoes = new ArrayList<>();
        String turno = "";
        String horariosSeparados[]  = new String[3];
        horariosSeparados = horario.split(" ");
        
        for(int i = 0; i < horariosSeparados.length; i++){
            horario = horariosSeparados[i];
            for (int j = 0; j < horario.length(); j++) {
                if (horario.charAt(j) == 'N') {
                    turno = "N";
                    break;
                } else if (horario.charAt(j) == 'M') {
                    turno = "M";
                    break;
                } else if (horario.charAt(j) == 'T') {
                    turno = "T";
                    break;
                }
            }

            int indexofTurno = horario.indexOf(turno);

            String parte1 = horario.substring(0, indexofTurno);
        
            String parte2 = horario.substring(indexofTurno + 1);
       

            for (int k = 0; k < parte1.length(); k++) {
                for (int l = 0; l < parte2.length(); l++) {
                    String aux = parte1.charAt(k) + turno + parte2.charAt(l);
                    combinacoes.add(aux);
                }
            }


        }

        
        return combinacoes;
    }


    public boolean verificarDisponibilidadeSala(int [][]matriz, ArrayList<String> horarios){
        int posicao;
        for(String horario : horarios){
            if(horario.charAt(1) == 'M'){

                posicao = (6*0) + Integer.parseInt(horario.substring(2));
                
            }
            else if(horario.charAt(1) == 'T'){
                posicao = (6*1) + Integer.parseInt(horario.substring(2));
               
            }
            else{
                posicao = (6*2) + Integer.parseInt(horario.substring(2));
                
            }

           
            if(matriz[Integer.parseInt(horario.substring(0,1))][posicao] == 1){
                return false;
            }
        }

        return true;
    }

    public Espaco melhorOpcao(ArrayList<Espaco> opcoes, int vagas){
        double diferenca = Double.POSITIVE_INFINITY;
        Espaco melhorOpcao = null;
        for(Espaco opcao : opcoes){
            if(opcao.getCapacidade() >= vagas){
                if((opcao.getCapacidade() - vagas) < diferenca){
                    diferenca = opcao.getCapacidade() - vagas;
                    melhorOpcao = opcao;
                }
            }
        }

        return melhorOpcao;
    }


    public void marcarHorarios(Espaco espaco, ArrayList<String> horarios){
        int posicao;
        int [][]matriz = espaco.getMatriz();
        for(String horario : horarios){
            if(horario.charAt(1) == 'M'){

                posicao = (6*0) + Integer.parseInt(horario.substring(2));
                
            }
            else if(horario.charAt(1) == 'T'){
                posicao = (6*1) + Integer.parseInt(horario.substring(2));
               
            }
            else{
                posicao = (6*2) + Integer.parseInt(horario.substring(2));
                
            }

           
            matriz[Integer.parseInt(horario.substring(0,1))][posicao] = 1;
               
        }

        espaco.setMatriz(matriz);

    }

    public Hashtable<String, Solicitacao> gerarRelatorioPorEspaco(String nome){
        Espaco espaco = espacos.get(nome);
        
        if(espaco != null){
            
            Hashtable<String, Solicitacao> solicitacoes = dados.get(espaco);
            

            return solicitacoes;
        }

        return null;
    }


    public ArrayList<Solicitacao> gerarRelatorioPorCurso(String curso){
        

        Enumeration<Espaco> e = dados.keys();

        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        while(e.hasMoreElements()){

            Espaco key = e.nextElement();

            Hashtable<String, Solicitacao> solicitacoesEspaco = dados.get(key);
            
            Enumeration<String> i = solicitacoesEspaco.keys();

            while(i.hasMoreElements()){

                String keySala = i.nextElement();

                Solicitacao solicitacao = solicitacoesEspaco.get(keySala);

                if(solicitacao.getCurso().equals(curso)){
                    solicitacoes.add(solicitacao);
                }
            }


        }

        return solicitacoes;
    }

    public void desmarcarHorariosExpirados(){
        Enumeration<Espaco> espacos = this.dados.keys();

        while(espacos.hasMoreElements()){
            Espaco espaco = espacos.nextElement();

            Enumeration<String> horarios = this.dados.get(espaco).keys();

            while(horarios.hasMoreElements()){
                String horario = horarios.nextElement();

                Solicitacao solicitacao = this.dados.get(espaco).get(horario);

                if(solicitacao instanceof Eventual){
                    String horarioSolicitacao = ((Eventual) solicitacao).getHorario();

                    LocalDate localdate = LocalDate.now();
                    DayOfWeek dayOfWeek = localdate.getDayOfWeek();
                    int dayOfWeekNumber = dayOfWeek.getValue();
                    
                
                    String turno = "";
                    for(int i = 0; i < horarioSolicitacao.length(); i++){
                        if(horarioSolicitacao.charAt(i) == 'T'){
                            turno = "T";
                            break;
                        }
                        else if(horarioSolicitacao.charAt(i) == 'M'){
                            turno = "M";
                            break;
                        }
                        else if(horarioSolicitacao.charAt(i) == 'N'){
                            turno = "N";
                            break;
                        }
                    }

                    String dias = horarioSolicitacao.substring(0, horarioSolicitacao.indexOf(turno));
                    int ultimoDia = Integer.parseInt(dias.substring(dias.length()-1, dias.length()));
                    if(ultimoDia < (dayOfWeekNumber + 1)%8){
                        this.desmarcarHorariosEventuais(espaco, solicitacao);
                    }

                }
            }
        }
    }


    public void desmarcarHorariosEventuais(Espaco espaco, Solicitacao solicitacao){
        int matriz[][] = espaco.getMatriz();

        String h = ((Eventual) solicitacao).getHorario();

        ArrayList<String> horarios = this.gerarCombinacoesHorarios(h);


        int posicao;
        for(String horario : horarios){
            if(horario.charAt(1) == 'M'){

                posicao = (6*0) + Integer.parseInt(horario.substring(2));
                
            }
            else if(horario.charAt(1) == 'T'){
                posicao = (6*1) + Integer.parseInt(horario.substring(2));
               
            }
            else{
                posicao = (6*2) + Integer.parseInt(horario.substring(2));
                
            }

           
            matriz[Integer.parseInt(horario.substring(0,1))][posicao] = 0;
               
        }

        espaco.setMatriz(matriz);

    }
}
