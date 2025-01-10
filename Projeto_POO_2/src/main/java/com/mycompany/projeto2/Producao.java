/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 *
 * @author nelso
 */
public class Producao {
    private static final int PRODUCAO_FIXA = 10; 
    private int producaoPorTurno; 
    private int producaoUtilizada;

    /**
     * Construtor para inicializar a produção por turno.
     *
     * @param producaoPorTurno a quantidade de produção gerada a cada turno.
     */
    public Producao(int producaoPorTurno) {
        this.producaoPorTurno = producaoPorTurno;
        this.producaoUtilizada=0;
    }

    /**
     * Reinicia a produção no início de um novo turno.
     */
    public void reiniciarProducao() {
        this.producaoUtilizada = 0;
    }

    /**
     * Tenta utilizar uma quantidade de produção disponível.
     *
     * @param quantidade a quantidade de produção que se deseja consumir.
     * @return true se a produção foi consumida com sucesso; false caso contrário.
     */
    public boolean usarProducao(int quantidade) {
        if (quantidade <= getProducaoRestante()) {
            producaoUtilizada += quantidade;
            System.out.printf("Produção de %d utilizada. Restante: %d.\n", quantidade, getProducaoRestante());
            return true;
        } else {
            System.out.println("Produção insuficiente para atender à demanda.");
            return false;
        }
    }

    /**
     * Obtém a quantidade restante de produção neste turno.
     *
     * @return a produção restante.
     */
    public int getProducaoRestante() {
        return PRODUCAO_FIXA - producaoUtilizada;
    }

    /**
     * Atualiza a quantidade de produção gerada por turno.
     *
     * @param producaoPorTurno nova quantidade de produção por turno.
     */
    public void setProducaoPorTurno(int producaoPorTurno) {
        this.producaoPorTurno = producaoPorTurno;
    }

    /**
     * Retorna uma representação textual da produção disponível.
     *
     * @return texto com a produção disponível.
     */
    @Override
    public String toString() {
        return "Produção disponível: " + getProducaoRestante();
    }
}