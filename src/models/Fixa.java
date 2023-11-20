package models;

public class Fixa extends Solicitacao{
    private String disciplina;
    private int vagas;
    private String horario;


    public Fixa(int ano, int semestre, String curso, String disciplina, int vagas, String horario){
        super(ano, semestre, curso);
        this.disciplina = disciplina;
        this.horario = horario;
    }
}
