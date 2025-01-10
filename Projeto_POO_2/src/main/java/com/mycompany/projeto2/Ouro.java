/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 * Classe que representa o ouro.
 */
public class Ouro{
    private int quantidade;
    private int producaoPorTurno;  

    public Ouro(int quantidadeInicial, int producaoPorTurno) {
        this.quantidade = quantidadeInicial;
        this.producaoPorTurno = producaoPorTurno;
    }

    
    /**
     * Método para adicionar ouro
     * @param quantidade A quantidade de ouro a ser adicionada ao tesouro.
     */
    public void adicionarOuro(int quantidade) {
        if (quantidade > 0) {
            this.quantidade += quantidade;
        }
    }

    /**
     * Método para remover ouro do tesouro
     * @param quantidade A quantidade de ouro a ser removida do tesouro.
     * @throws IllegalArgumentException Se a quantidade de ouro a ser removida for maior que a quantidade de ouro no tesouro.
     */
    public void removerOuro(int quantidade) throws IllegalArgumentException {
        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Não há ouro suficiente no tesouro.");
        }
        this.quantidade -= quantidade;
    }

    /**
     * Método para obter a quantidade atual de ouro
     * @return A quantidade de ouro no tesouro.
     */
    public int obterQuantidade() {
        return this.quantidade;
    }
    /**
     * Método para produzir ouro
     */
    public void produzirOuro() {
        this.quantidade += this.producaoPorTurno;
    }
    /**
     * Método para exibir a quantidade de ouro
     */
    @Override
    public String toString() {
        return "Quantidade de ouro no tesouro: " + this.quantidade;
    }
    /**
     * Método para obter a produção de ouro por turno
     * @param incremento A quantidade de ouro a ser adicionada à produção por turno.
     */
    public void aumentarProducao(int incremento) {
        if (incremento > 0) {
            this.producaoPorTurno += incremento;
        } else {
            System.out.println("Valor de incremento inválido. O aumento deve ser maior que 0.");
        }
    }

}
