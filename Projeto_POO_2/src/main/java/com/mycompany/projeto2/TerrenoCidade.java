/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 package com.mycompany.projeto2;

/**
 *
 * @author nelso
 */


public class TerrenoCidade extends Terrenos {
    private Cidades cidade;
    private int x;
    private int y;

    public TerrenoCidade(Cidades cidade, int x, int y) {
        super(1); 
        this.cidade = cidade;
        this.x = x;
        this.y = y;
    }

    /*
     * Método para exibir a cidade
     */
    @Override
    public String toString() {
        return "C"; 
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
    /**
     * Método para obter a cidade
     * @return A cidade
     */
    public Cidades getCidade() {
        return cidade;
    }
}