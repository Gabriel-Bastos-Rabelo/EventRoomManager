package models;

public abstract class Espaco {
    protected int capacidade;
    protected String nome;
    protected String localizacao;


    public Espaco(int capacidade, String nome, String localizacao){
        this.capacidade = capacidade;
        this.nome = nome;
        this.localizacao = localizacao;
    }   
}
