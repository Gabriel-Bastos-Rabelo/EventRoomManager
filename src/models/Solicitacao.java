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

    public int getAno(){
        return this.ano;
    }

    public int getSemestre(){
        return this.semestre;
    }

    public String getCurso(){
        return this.curso;
    }
}