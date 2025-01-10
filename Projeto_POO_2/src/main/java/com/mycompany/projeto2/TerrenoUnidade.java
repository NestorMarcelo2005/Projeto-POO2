/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.projeto2;

public class TerrenoUnidade extends Terrenos {
    private Unidade unidade;
    private int x;
    private int y;

    public TerrenoUnidade(Unidade unidade, int x, int y) {
        super(0);
        this.unidade = unidade;
        this.x = x;
        this.y = y;
    }
    /**
     * Método para obter a unidade
     */
    public Unidade getUnidade() {
        return unidade;
    }
    /**
     * Método para definir a unidade
     * @param unidade A unidade a ser definida
     */
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    /**
     * Método para exibir a unidade
     */
    @Override
    public String toString() {
        if (unidade instanceof Militares) {
            return "M";
        } else if (unidade instanceof Construtores) {
            return "B";
        } else if (unidade instanceof Colonos) {
            return "H";
        } else {
            return "?";
        }
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