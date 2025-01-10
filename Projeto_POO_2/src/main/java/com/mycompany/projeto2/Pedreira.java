/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;


/**
 * Classe que representa uma pedreira.
 */
public class Pedreira extends Terrenos implements Recursos {
    private int ouro = 0;
    private int producaoPorTurno = 20; 
    private int x;
    private int y;

    public Pedreira(int x, int y) {
        super(4);
        this.x = x;
        this.y = y;
    }
    /**
     * Obtém a quantidade de comida coletada da pedreira.
     */
    @Override
    public int getComida() {
        return 0; 
    }
    /**
     * Obtém a quantidade de produção gerada pela pedreira.
     */
    @Override
    public int getProducao() {
        return 0; 
    }
    /**
     * Obtém a quantidade de ouro gerada pela pedreira.
     */
    @Override
    public int getOuro() {
        return ouro;
    }
    /**
     * Obtém a quantidade de produção gerada pela pedreira por turno.
     */
    @Override
    public int getProducaoPorTurno() {
        return producaoPorTurno; 
    }
    /**
     * Método para exibir a quantidade de ouro
     */
    @Override
    public String toString() {
        return "P"; 
    }
    /**
     * Método para coletar ouro
     */
    public void coletarOuro() {
        ouro += producaoPorTurno; 
    }
    /**
     * Método para obter a coordenada x da pedreira
     */
    @Override
    public int getX() {
        return this.x;
    }
    /**
     * Método para obter a coordenada Y da pedreira
     */
    @Override
    public int getY() {
        return this.y;
    }

}