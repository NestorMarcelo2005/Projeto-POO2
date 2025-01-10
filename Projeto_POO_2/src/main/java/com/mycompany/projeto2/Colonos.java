/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

import java.util.List;

/**
 *
 * @author mgng2
 */
public class Colonos extends Unidade{
    private int POPULACAO_INICIAL;
    private int OURO_INICIAL;
    private int PRODUCAO_INICIAL;
    private List<Cidades> listcidades;


    /**
     * Construtor da classe Colonos.
     * @param nome O nome da unidade.
     */
    public Colonos(String nome) {
        super(nome,  10); 
        this.listcidades=listcidades;
    }

    public boolean fundarCidade(Player player, String nomeCidade, Mapa mapa, int x, int y) {
        // Verifica se a posição é válida
        if (!mapa.posicaoLivre(x, y)) {
            return false;
        }
    
        // Verifica se já existe uma cidade próxima
        if (mapa.existeCidadeProxima(x, y)) {
            System.out.println("Já existe uma cidade próxima.");
            return false;
        }
    
        // Remove colono da lista de unidades do jogador
        player.removerUnidade(this);
    
        // Cria a cidade
        if (mapa.criarCidade2(x, y, player)) {
            System.out.println(getNome() + " fundou uma nova cidade!");
            return true;
        }
    
        return false;
    }
    /*
     * Método que executa a ação da unidade
     */
    @Override
    public void performAction() {
        System.out.println(getNome() + " está a fundar uma nova cidade.");

    }
} 

