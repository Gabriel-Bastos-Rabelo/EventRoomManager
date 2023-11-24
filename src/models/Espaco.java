package models;

public abstract class Espaco {
    protected int capacidade;
    protected String nome;
    protected String localizacao;
    protected int[][] matriz;


    public Espaco(int capacidade, String nome, String localizacao){
        this.capacidade = capacidade;
        this.nome = nome;
        this.localizacao = localizacao;
        this.matriz = new int[7][19];
      

    }  


    public int[][] getMatriz(){
        return this.matriz;
    }

    public String getNome(){
        return this.nome;
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public void setMatriz(int [][]matriz){
        this.matriz = matriz;
    }
}
