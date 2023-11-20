package models;

public class Eventual extends Solicitacao{
    private String finalidade;
    private int vagas;
    private String horario;


    public Eventual(int ano, int semestre, String curso, String finalidade, int vagas, String horario){
        super(ano, semestre, curso);
        this.finalidade = finalidade;
        this.horario = horario;
    }
}
