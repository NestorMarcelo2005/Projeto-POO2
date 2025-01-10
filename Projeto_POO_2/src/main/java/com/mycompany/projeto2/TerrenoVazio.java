/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 *
 * @author nelso
 */
public class TerrenoVazio extends Terrenos {
    private int x;
    private int y;

    public TerrenoVazio(int x, int y) {
        super(7);
        this.x = x;
        this.y = y;
    }
    /**
     * Método para obter a coordenada x
     */
    @Override
    public int getX() {
        return x;
    }
    /**
     * Método para obter a coordenada y
     */
    @Override
    public int getY() {
        return y;
    }
    
    /**
     * Método para exibir o terreno vazio
     */
    @Override
    public String toString() {
        return "X";
    }
}