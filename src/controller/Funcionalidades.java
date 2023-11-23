package controller;

import java.util.Enumeration;
import java.util.Hashtable;

import models.Espaco;
import models.Solicitacao;

public class Funcionalidades {
    

    private Hashtable<Espaco, Hashtable<String, Solicitacao>> dados;


    
    public Funcionalidades(){
        this.dados = new Hashtable<>();
    }

    public boolean solicitacaoFixa(int ano, int semestre, String curso, String disciplina, int vagas, String horario){
        return true;
        
    }

    public boolean solicitacaoEventual(int ano, int semestre, String curso, String finalidade, int vagas, String horario){
        return true;
    }

    public boolean cadastroSala(int capacidade, String nome, String localizacao){
        return true;
    }

    public boolean cadastroAuditorio(int capacidade, String nome, String localizacao){
        return true;
    }

    public boolean conflitoHorarios(String horario1, String horario2){
        return true;

    }
}
