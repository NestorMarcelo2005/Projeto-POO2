/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 * Representa um terreno do tipo Floresta no jogo.
 * A floresta pode fornecer recursos como comida e produção por turno.
 */
public class Floresta extends Terrenos implements Recursos {
    private int comida;
    private int producaoPorTurno = 10;
    private int x;
    private int y;

    /**
     * Construtor que inicializa a Floresta com coordenadas específicas.
     *
     * @param x a coordenada X da floresta.
     * @param y a coordenada Y da floresta.
     */
    public Floresta(int x, int y) {
        super(4); // Define um custo fixo para movimentação neste tipo de terreno.
        this.x = x;
        this.y = y;
    }

    /**
     * Obtém a quantidade de comida coletada da floresta.
     *
     * @return a quantidade de comida disponível.
     */
    @Override
    public int getComida() {
        return comida;
    }

    /**
     * Obtém a quantidade de produção gerada pela floresta.
     * Neste caso, a floresta não fornece produção acumulada diretamente.
     *
     * @return 0, pois a floresta não acumula produção diretamente.
     */
    @Override
    public int getProducao() {
        return 0;
    }

    /**
     * Obtém a quantidade de ouro gerada pela floresta.
     * Florestas não produzem ouro.
     *
     * @return 0, pois a floresta não gera ouro.
     */
    @Override
    public int getOuro() {
        return 0;
    }

    /**
     * Obtém a quantidade de produção gerada pela floresta por turno.
     *
     * @return a quantidade de produção por turno, que é 10.
     */
    @Override
    public int getProducaoPorTurno() {
        return producaoPorTurno;
    }

    /**
     * Retorna uma representação textual do terreno.
     * Neste caso, a letra "F" representa a floresta no mapa.
     *
     * @return uma string contendo "F".
     */
    @Override
    public String toString() {
        return "F";
    }

    /**
     * Coleta comida gerada pela floresta, adicionando a produção por turno
     * à quantidade acumulada de comida.
     */
    public void coletarComida() {
        comida += producaoPorTurno;
    }

    /**
     * Obtém a coordenada X da floresta.
     *
     * @return a coordenada X.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Obtém a coordenada Y da floresta.
     *
     * @return a coordenada Y.
     */
    @Override
    public int getY() {
        return this.y;
    }
}