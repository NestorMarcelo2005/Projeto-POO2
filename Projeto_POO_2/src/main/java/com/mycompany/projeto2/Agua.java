/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 * Representa um terreno do tipo Água no jogo.
 * A água é um tipo especial de terreno que não pode ser explorado ou trabalhado,
 * mas é representado no mapa.
 */
public class Agua extends Terrenos {
    private int x;
    private int y; 

    /**
     * Construtor para inicializar um terreno do tipo Água com coordenadas específicas.
     *
     * @param x a coordenada X do terreno.
     * @param y a coordenada Y do terreno.
     */
    public Agua(int x, int y) {
        super(9999); // Define um custo arbitrário muito alto para representar que este terreno é inacessível.
        this.x = x;
        this.y = y;
    }

    /**
     * Obtém a coordenada X deste terreno.
     *
     * @return a coordenada X.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Obtém a coordenada Y deste terreno.
     *
     * @return a coordenada Y.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Retorna uma representação textual do terreno.
     * Neste caso, a letra "A" representa a água no mapa.
     *
     * @return uma string contendo "A".
     */
    @Override
    public String toString() {
        return "A";
    }
}
