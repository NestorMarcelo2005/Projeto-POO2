/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;
/**
 * Classe que representa os militares.
 */
public class Militares extends Unidade{
    private int ataque;
    private int vida;
    private int customanutencao;
    private int danocombate = 10; 

    public Militares(String nome) {
        super(nome,15);
        this.ataque = 10;
        this.vida = 5;
    }

    // Getters
    public int getAtaque() {
        return ataque;
    }
    // Getters
    public int getVida() {
        return vida;
    }
    /**
     * Método para atacar uma cidade
     * @param cidade A cidade a ser atacada.
     */
    @Override
    public void attackCity(Cidades cidade) {
        System.out.println(getNome() + " está a atacar a cidade " + cidade.getNome());
        int dano = danocombate;
        cidade.takeDamage(dano);
    }
    /**
     * Método para reduzir a vida da unidade quando ela recebe dano.
     * @param dano O valor de dano que a unidade sofre.
     */
    @Override
    public void takeDamage(int dano) {
        vida -= dano;
        if (vida <= 0) {
            System.out.println(getNome() + " foi destruída.");
        } else {
            System.out.println(getNome() + " sofreu dano. Vida restante: " + vida);
        }
    }

    /**
     * Método para atacar outra unidade.
     * @param inimigo A unidade a ser atacada.
     */
    @Override
    public void attack(Unidade inimigo) {
        if (inimigo instanceof Militares) {
            Militares target = (Militares) inimigo;
            System.out.println(getNome() + " atacou " + target.getNome());
            int damage = this.ataque - (target.ataque / 2); 
            target.takeDamage(damage);
        } else {
            System.out.println("Alvo inválido para ataque.");
        }
    }

    /**
     * Método para receber dano de outra unidade.
     * @param dano O valor de dano que a unidade sofre.
     */
    public void receberDano(int dano) {
        vida -= dano;
        if (vida <= 0) {
            System.out.println(getNome() + " foi destruído.");
        }
    }

    /**
     * Método para executar a ação da unidade.
     */
    @Override
    public void performAction() {
        System.out.println(getNome() + " está em posição defensiva.");
    }

} 
