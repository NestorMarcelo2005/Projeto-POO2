/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 *
 * @author nelso
 */
public abstract class Terrenos {

    private int custoMovimento;
    private Unidade unidade;
    public Terrenos(int custoMovimento) {
        this.custoMovimento = custoMovimento;
    }
    /**
     * Método para obter a coordenada x
     * @return A coordenada x
     */
    public abstract int getX();
    /**
     * Método para obter a coordenada y
     * @return A coordenada y
     */
    public abstract int getY();
    /**
     * Método para obter o custo de movimento
     * @return O custo de movimento
     */
    public int getCustoMovimento() {
        return custoMovimento;
    }
    
    /**
     * Método para exibir o terreno
     * @return O terreno
     */
    @Override
    public abstract String toString();
    /**
     * Método para obter a unidade
     * @return A unidade
     */
    public Unidade getUnidade() {
        return unidade;
    }
    /**
     * Método para definir a unidade
     * @return A unidade
     */
    public boolean temUnidade() {
        return this instanceof TerrenoUnidade;
    }

}