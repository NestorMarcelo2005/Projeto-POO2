/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.projeto2;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o jogador.
 */
public class Player {
    private String nome;
    private List<Cidades> cidades;
    private List<Unidade> unidades;
    private int tesouro;

    public Player(String nome) {
        this.nome = nome;
        this.cidades = new ArrayList<>();
        this.unidades = new ArrayList<>();
        this.tesouro = 0;
    }
    /**
     * Adiciona uma cidade ao jogador.
     * @param cidade A cidade a ser adicionada.
     */
    public void adicionarCidade(Cidades cidade) {
        cidades.add(cidade);
        System.out.println("Cidade " + cidade.getNome() + " adicionada ao Player " + nome);
    }

    /**
     * Adiciona uma unidade ao jogador.
     * @param unidade A unidade a ser adicionada.
     */
    public void adicionarUnidade(Unidade unidade) {
        unidades.add(unidade);
        System.out.println("Unidade " + unidade.getNome() + " adicionada ao Player " + nome);
    }

    /**
     * Calcula o tesouro total do jogador.
     */
    public void calcularTesouro() {
        for (Cidades cidade : cidades) {
            tesouro += cidade.getOuro();
        }
        System.out.println("Tesouro total do Player " + nome + ": " + tesouro);
    }

    /**
     * Método para pagar a manutenção das unidades.
     */
    public void pagarManutencao() {
        int custoTotal = 0;
        for (Unidade unidade : unidades) {
            custoTotal += unidade.getCusto();
        }

        if (tesouro >= custoTotal) {
            tesouro -= custoTotal;
            System.out.println("Manutenção paga. Tesouro atual: " + tesouro);
        } else {
            System.out.println("Tesouro insuficiente! Unidades podem ser desmontadas ou penalidades aplicadas.");
        }
    }

    /**
     * Lista as unidades do jogador.
     */
    public void listarUnidades() {
        if (unidades.isEmpty()) {
            System.out.println("Nenhuma unidade disponível.");
            return;
        }
        System.out.println("===== Unidades =====");
        for (Unidade unidade : unidades) {
            System.out.println(unidade.getNome() + " - Posição (" + unidade.getPosicaoX() + ", " + unidade.getPosicaoY() + ")");
        }
    }

    /**
     * Lista as cidades do jogador.
     */
    public void listarCidades() {
        if (cidades.isEmpty()) {
            System.out.println("Nenhuma cidade disponível.");
            return;
        }
        System.out.println("===== Cidades =====");
        for (Cidades cidade : cidades) {
            System.out.println(cidade.getNome() + " - População: " + cidade.getPopulacao());
        }
    }

    /**
     * O método tem como propósito permitir que uma unidade ataque outra
     * @param indiceUnidade O índice da unidade a ser movida.
     * @param unidadeAlvo A unidade alvo para a movimentação.
     */
    public void atacarComUnidade(int indiceUnidade, Unidade unidadeAlvo) {
        if (indiceUnidade >= 0 && indiceUnidade < unidades.size()) {
            Unidade unidade = unidades.get(indiceUnidade);
            unidade.attack(unidade);
        } else {
            System.out.println("Unidade não encontrada.");
        }
    }

    /**
     * O método retorna uma lista de objetos do tipo Unidade.
     * @return A lista de cidades do jogador.
     */
    public List<Unidade> getUnidades() {
        return unidades;
    }

    /**
     * O método retorna uma lista de objetos do tipo Cidades.
     * @return A lista de cidades do jogador.
     */
    public List<Cidades> getCidades() {
        return cidades;
    }

    /**
     * O método retorna o nome do jogador.
     * @return O nome do jogador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * O método adiciona uma unidade à lista de unidades do jogador.
     * @param unidade A unidade a ser adicionada.
     */
    public void addUnidade(Unidade unidade) {
        unidades.add(unidade);
    }

    /**
     * O método remove uma unidade da lista de unidades do jogador.
     * @param unidade A unidade a ser removida.
     * @return true se a unidade foi removida com sucesso, false caso contrário.
     */
    public boolean removerUnidade(Unidade unidade) {
        if (unidade == null) {
            System.out.println("Unidade inválida.");
            return false;
        }
    
        if (unidades.remove(unidade)) {
            System.out.println("Unidade " + unidade.getNome() + " removida com sucesso.");
            return true;
        } else {
            System.out.println("Unidade não encontrada na cidade.");
            return false;
        }
    }
    public int calcularOuroTotal() {
        int ouroTotal = 0;
        for (Cidades cidade : cidades) {
            ouroTotal += cidade.getOuro();
        }
        return ouroTotal;
    }
    
    public boolean verificarVitoria() {
        int ouroTotal = calcularOuroTotal();
        if (ouroTotal >= 320) {
            System.out.println("VITÓRIA! " + nome + " alcançou 320 de ouro!");
            return true;
        }
        return false;
    }
}