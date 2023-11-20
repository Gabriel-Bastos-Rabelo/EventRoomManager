package models;

public abstract class Solicitacao{
    protected int ano;
    protected int semestre;
    protected String curso;

    public Solicitacao(int ano, int semestre, String curso){
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
    }
}