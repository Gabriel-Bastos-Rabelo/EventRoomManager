package models;

public class Fixa extends Solicitacao{
    private String disciplina;
    private int vagas;
    private String horario;


    public Fixa(int ano, int semestre, String curso, String disciplina, int vagas, String horario){
        super(ano, semestre, curso);
        this.disciplina = disciplina;
        this.vagas = vagas;
        this.horario = horario;
    }

    public String getDisciplina(){
        return this.disciplina;
    }

    public String getHorario(){
        return this.horario;
    }

    public int getVagas(){
        return this.vagas;
    }

    @Override
    public String toString() {
        return "Fixa{" +
               "ano=" + getAno() +
               ", semestre=" + getSemestre() +
               ", curso='" + getCurso() + '\'' +
               ", disciplina='" + disciplina + '\'' +
               ", vagas=" + vagas +
               ", horario='" + horario + '\'' +
               '}';
    }
    
               
               
}
