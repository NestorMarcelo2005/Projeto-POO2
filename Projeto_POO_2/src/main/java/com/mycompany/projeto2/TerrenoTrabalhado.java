package com.mycompany.projeto2;

public class TerrenoTrabalhado extends Terrenos {
    private Terrenos terrenoOriginal; 
    private int quantidadeTrabalhadores;
    private String cidadeResponsavel;
    private int x;
    private int y;

    public TerrenoTrabalhado(Terrenos terrenoOriginal, String cidadeResponsavel, int quantidadeTrabalhadores, int x, int y) {
        super(1);
        this.terrenoOriginal = terrenoOriginal; 
        this.cidadeResponsavel = cidadeResponsavel;
        this.quantidadeTrabalhadores = quantidadeTrabalhadores;
        this.x = x;
        this.y = y;
    }
    /**
     * Método para obter o terreno original
     * @return O terreno original
     */
    public Terrenos getTerrenoOriginal() {
        return terrenoOriginal;
    }

    /**
     * Método para obter a cidade responsável
     * @return A cidade responsável
     */
    public String getCidadeResponsavel() {
        return cidadeResponsavel;
    }

    /**
     * Método para obter a quantidade de trabalhadores
     * @return A quantidade de trabalhadores
     */
    public int getQuantidadeTrabalhadores() {
        return quantidadeTrabalhadores;
    }

    /**
     * Método para exibir o terreno trabalhado
     */
    @Override
    public String toString() {
        return terrenoOriginal.toString() + " - Trabalhadores: " + quantidadeTrabalhadores + " (" + cidadeResponsavel + ")";
    }

    /** 
     * Método para incrementar a quantidade de trabalhadores
     */
    public void incrementarTrabalhador() {
        this.quantidadeTrabalhadores++;
    }

    /**
     * Método para obter a coordenada x
     */
    @Override
    public int getX() {
        return this.x;
    }
    /**
     * Método para obter a coordenada y
     */
    @Override
    public int getY() {
        return this.y;
    }

}

