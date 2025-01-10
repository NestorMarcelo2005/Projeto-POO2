/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 *
 * @author nelso
 */
public class Comida{
    private int comidaConsumidaPorPopulacao;
    private int reservaDeComida;
    private int limiteParaCrescimento;
    private int populacao;

    /**
     * Construtor padrão que inicializa os valores com configurações padrão.
     * A comida produzida e a reserva inicial são 20, e a população começa com 1.
     */
    public Comida() {
        this.comidaConsumidaPorPopulacao = 1;
        this.reservaDeComida = 20;
        this.limiteParaCrescimento = 20;
        this.populacao = 1;
    }

    /**
     * Construtor que inicializa a comida produzida com um valor inicial especificado.
     *
     * @param comidaInicial a quantidade inicial de comida produzida por turno.
     */
    public Comida(int comidaInicial) {
        this.reservaDeComida = comidaInicial;
    }

    /**
     * Produz comida e adiciona à reserva de comida armazenada.
     */
    public void produzirComida(int quantidade) {
        this.reservaDeComida += quantidade;
    }

    /**
     * Consome comida com base na população atual.
     * Caso a reserva de comida seja insuficiente, a população é reduzida em 1
     * e a reserva é zerada.
     */
    public void consumirComida() {
        int comidaNecessaria = populacao * comidaConsumidaPorPopulacao;
        this.reservaDeComida -= comidaNecessaria;

        if (this.reservaDeComida < 0) {
            populacao -= 1; // Reduz a população em caso de fome.
            this.reservaDeComida = 0;
        }
    }

    /**
     * Verifica se a população pode crescer com base na reserva de comida.
     * Se houver comida suficiente para o crescimento, a população aumenta
     * e a comida necessária para o crescimento é removida da reserva.
     */
    public void verificarCrescimento() {
        if (this.reservaDeComida > limiteParaCrescimento) {
            populacao += 1; // Incrementa a população.
            this.reservaDeComida -= limiteParaCrescimento; // Consome comida para o crescimento.
        }
    }

    /**
     * Obtém a quantidade de comida armazenada na reserva.
     *
     * @return a quantidade de comida armazenada.
     */
    public int getReservaDeComida() {
        return reservaDeComida;
    }

    /**
     * Avança para o próximo turno, realizando as ações de produzir comida,
     * consumir comida e verificar crescimento populacional.
     */
    public void proximoTurno() {
        consumirComida();
        verificarCrescimento();
    }

    /**
     * Retorna uma representação textual do estado atual da comida e da população.
     *
     * @return uma string no formato "População: X, Reserva de Comida: Y".
     */
    @Override
    public String toString() {
        return "População: " + populacao + ", Reserva de Comida: " + reservaDeComida;
    }

    

    /**
     * Define a quantidade de comida consumida por cada unidade de população por turno.
     *
     * @param comidaConsumidaPorPopulacao a nova quantidade de comida consumida por unidade de população.
     */
    public void setComidaConsumidaPorPopulacao(int comidaConsumidaPorPopulacao) {
        this.comidaConsumidaPorPopulacao = comidaConsumidaPorPopulacao;
    }

    /**
     * Define o limite de comida necessário para que a população cresça.
     *
     * @param limiteParaCrescimento a nova quantidade de comida necessária para crescimento populacional.
     */
    public void setLimiteParaCrescimento(int limiteParaCrescimento) {
        this.limiteParaCrescimento = limiteParaCrescimento;
    }
}
