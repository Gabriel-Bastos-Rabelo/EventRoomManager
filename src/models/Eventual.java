package models;

import java.time.LocalDate;

public class Eventual extends Solicitacao{
    private String finalidade;
    private int vagas;
    private String horario;
    private LocalDate date;


    public Eventual(int ano, int semestre, String curso, String finalidade, int vagas, String horario){
        super(ano, semestre, curso);
        this.finalidade = finalidade;
        this.horario = horario;
        this.vagas = vagas;
    }


    public String getHorario(){
        return this.horario;
    }

    public String getFinalidade(){
        return this.finalidade;
    }

    public int getVagas(){
        return this.vagas;
    }
}
