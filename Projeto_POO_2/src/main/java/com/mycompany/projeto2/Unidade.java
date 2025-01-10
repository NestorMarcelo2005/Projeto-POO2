/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 *
 * @author mgng2
 */
public abstract class Unidade {
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private int pontosMovimentos; 
    private int maxpontosmovimento; 
    private int custo; 
    private int vida;
    private int ataque;

    public Unidade(String nome, int maxpontosmovimento) {
        this.nome = nome;
        this.maxpontosmovimento = maxpontosmovimento;
        this.pontosMovimentos = maxpontosmovimento;

    }
    /**
     * Método para obter o custo de produção
     * @return  O custo de produção
     */


    /**
     * Método para criar uma unidade
     * @param tipo O tipo de unidade
     * @return A unidade
     */
    public Unidade criarUnidade(String tipo) {
        switch (tipo.toLowerCase()) {
            case "militar":
                return new Militares("Militar");
            case "colono":
                return new Colonos("Colono");
            case "construtor":
                return new Construtores("Construtor");
            default:
                System.out.println("Tipo de unidade desconhecido.");
                return null;
        }
    }
    /**
     * Método para verificar se a unidade pode ser movida, devido aos pontos de movimento
     * @param novaPosicaoX A nova posição x
     * @param novaPosicaoY A nova posição y
     * @param terrenoDestino O terreno de destino
     * @return Verdadeiro se a unidade foi movida, falso caso contrário
     */
    public boolean mover(int novaPosicaoX, int novaPosicaoY, Terrenos terrenoDestino) {
        int custoMovimento = terrenoDestino.getCustoMovimento();
        
        if (pontosMovimentos >= custoMovimento) {
            pontosMovimentos -= custoMovimento;
            return true;
        }
        return false;
    }

    /**
     * Método para obter a vida da unidade
     * @param vida A vida da unidade
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Método para reestar o custo de produção
     */
    public void resetMovementPoints() {
        this.pontosMovimentos = maxpontosmovimento;
    }
    
    /**
     * Método para mover a unidade
     * @param novaPosicaoX A nova posição x
     * @param novaPosicaoY A nova posição y
     */
    public void move(int novaPosicaoX, int novaPosicaoY) {
        this.posicaoX = novaPosicaoX;
        this.posicaoY = novaPosicaoY;
        System.out.println(nome + " foi movida para a posiçao (" + novaPosicaoX + ", " + novaPosicaoY + ").");
    }

    /**
     * Método para obter o custo de produção
     * @param custoterra O custo do terreno
     */
    public void move(int custoterra) {
        if (custoterra <= pontosMovimentos) {
            pontosMovimentos -= custoterra;
            System.out.println(nome + " moveu-se. Pontos de movimento restantes: " + pontosMovimentos);
        } else {
            System.out.println(nome + " nao pode mover-se, custo de terreno muito alto.");
        }
    }

    /**
     * Método para mover a unidade
     * @param novaPosicaoX A nova posição x
     * @param novaPosicaoY A nova posição y
     * @param custoMovimento O custo de movimento
     * @return Verdadeiro se a unidade foi movida, falso caso contrário
     */
    public boolean movePara(int novaPosicaoX, int novaPosicaoY, int custoMovimento) {
        if (custoMovimento <= pontosMovimentos) {
            this.posicaoX = novaPosicaoX;
            this.posicaoY = novaPosicaoY;
            pontosMovimentos -= custoMovimento;
            System.out.println(nome + " moveu para (" + novaPosicaoX + ", " + novaPosicaoY + "). Pontos restantes: " + pontosMovimentos);
            return true;
        } else {
            System.out.println(nome + " nao tem pontos de movimento suficientes para se mover para (" + novaPosicaoX + ", " + novaPosicaoY + ").");
            return false;
        }
    }
    // Getters e Setters
    public String getNome() { return nome; }
    public int getPosicaoX() { return posicaoX; }
    public int getPosicaoY() { return posicaoY; }
    public int getCusto() { return custo; }
    public int getPontosMovimentos() { return pontosMovimentos; }

    public void setPosicao(int x, int y) {
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public abstract void performAction(); 
    /**
     * Método para atacar uma unidade
     * @param inimigo A unidade a ser atacada.
     */
    public void attack(Unidade inimigo) {
        if (inimigo != null) {
            System.out.println(this.nome + " atacou " + inimigo.getNome());
            int dano = this.ataque - (inimigo.ataque / 2);
            inimigo.takeDamage(dano);
        } else {
            System.out.println("O alvo não é uma unidade válida.");
        }
    }
    /**
     * Método para atacar uma cidade
     * @param cidade A cidade a ser atacada.
     */
    public void attackCity(Cidades cidade) {
        if (cidade != null) {
            System.out.println(this.nome + " está atacando a cidade " + cidade.getNome());
            int dano = 20;
            cidade.takeDamage(dano);
        } else {
            System.out.println("A cidade não é válida.");
        }
    }
    /**
     * Método para reduzir a vida da unidade quando ela recebe dano.
     * @param dano O valor de dano que a unidade sofre.
     */
    public void takeDamage(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            System.out.println(this.nome + " foi destruído.");
        } else {
            System.out.println(this.nome + " sofreu dano. Vida restante: " + this.vida);
        }
    }
    /**
     * Método para receber dano de outra unidade.
     */
    @Override
    public String toString() {
        return nome + " (Movimento: " + pontosMovimentos + ", Posicao: " + posicaoX + ", " + posicaoY + ")";
    }
    /**
     * Método para reduzir os pontos de movimento
     * @param custo O custo de movimento
     */
    public void reduzirPontosMovimento(int custo) {
        this.pontosMovimentos = Math.max(0, this.pontosMovimentos - custo);
    }
    /**
     * Método para resetar os pontos de movimento
     */
    public void resetarPontosMovimento() {
        this.pontosMovimentos = this.maxpontosmovimento;
    }
}