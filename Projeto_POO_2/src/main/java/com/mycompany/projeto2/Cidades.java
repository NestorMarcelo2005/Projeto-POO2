/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mgng2
 */  
public class Cidades {
    private final int distanciaMin = 2; 
    private ArrayList<int[]> posicoesCidades;
    private String nome;
    private int populacao;
    private int producao; 
    private Producao gestaoProducao;
    private List<Unidade> unidades;
    private int vida;
    private int comida;
    private int posX;
    private int posY;
    private Comida gestaoComida;
    private Ouro gestaoOuro; 
    private List<Terrenos> terrenosTrabalhados;
    private int comidainicial;
    private int totalComida = 10;
    private int totalProducao = 10;
    private int totalOuro = 10;
    private Player player;
    private int raioInfluencia = 3;
    private boolean raioAumentado = false;
    private static int contadorCidades = 1;
    private static final int POPULACAO_INICIAL = 5;
    private static final int PRODUCAO_INICIAL = 10;
    private static final int COMIDA_INICIAL = 50;
    private static final int VIDA = 100;
    private int contadorMilitares = 0;
    private int contadorColonos = 0;
    private int contadorConstrutores = 0;

    public Cidades(int populacao, int ouroInicial, int producaoPorTurno, int posX, int posY, Mapa mapaa, Player player) {
        this.gestaoOuro = new Ouro(ouroInicial,0);
        this.gestaoProducao = new Producao(producaoPorTurno);
        this.posX = posX; 
        this.posY = posY; 
        this.comida = comidainicial;
        this.populacao = populacao;
        this.player = null;
        this.nome = "cidade" + contadorCidades++;
        this.populacao = POPULACAO_INICIAL;
        this.unidades = new ArrayList<>();
        this.terrenosTrabalhados = new ArrayList<>();
        this.gestaoProducao = new Producao(PRODUCAO_INICIAL);
        this.gestaoComida = new Comida(COMIDA_INICIAL);
        this.gestaoOuro = new Ouro(0, 0);
        this.vida = VIDA;
        this.comida = COMIDA_INICIAL;
        
    }
    
    /**
     * Método para obter a posição x
     * @param player O jogador
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Método para adicionar uma cidade
     * @param x coordenada de x a adicionar cidade
     * @param y coordenada de y a adicionar cidade
     * @param nomeCidade nome da cidade
     * @return true se a cidade foi adicionada, false caso contrário
     */
    public boolean adicionarCidade(int x, int y, String nomeCidade) {
        if (posicaoLivre(x, y)) {
            posicoesCidades.add(new int[]{x, y});
            System.out.println("Cidade " + nomeCidade + " foi criada na posição (" + x + ", " + y + ")");
            return true;
        } else {
            System.out.println("Posição ocupada ou muito próxima a outra cidade.");
            return false;
        }
    }

    /**
     * Método para adicionar uma cidade
     * @param cidades lista de cidades   
     * @param cidade cidade a ser adicionada
     */
    public void adicionarCidade(List<Cidades> cidades, Cidades cidade) {
        cidades.add(cidade); 
    }

    /**
     * Método para verificar se a posição está livre
     * @param x coordenada de x
     * @param y coordenada de y
     * @return true se a posição está livre, false caso contrário
     */
    private boolean posicaoLivre(int x, int y) {
        for (int[] cidade : posicoesCidades) {
            int cidadeX = cidade[0];
            int cidadeY = cidade[1];
            int MinX = cidadeX - distanciaMin;
            int MaxX = cidadeX + distanciaMin;
            int MinY = cidadeY - distanciaMin;
            int MaxY = cidadeY + distanciaMin;

            if (x >= MinX && x <= MaxX && y >= MinY && y <= MaxY) {
                return false;
            }
        }
        return true;
    }

    // Getters
    public ArrayList<int[]> getPosicoesCidades(){return posicoesCidades;}
    public String getNome() {return nome;}
    public int getPopulacao() {return populacao;}
    public int getProducao() {return producao;}
    public List<Unidade> getUnidades() {return unidades;}
    public int getVida() {return vida;}
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setNome(String nome) {this.nome = nome;}
    /**
     * Método para adicionar um terreno trabalhado
     * @param terreno O terreno a ser adicionado
     */
    public void adicionarTerrenoTrabalhado(TerrenoTrabalhado terreno) {
        if (terreno != null) {
            terrenosTrabalhados.add(terreno);  
        }
    }

    
    /**
     * Método para aumentar a população
     * @param quantidade A quantidade a ser aumentada
     */
    public void aumentarPopulacao(int quantidade) {
        this.populacao += quantidade;
    }

    /**
     * Método para reduzir a população
     * @param quantidade A quantidade a ser reduzida
     */
    public void reduzirPopulacao(int quantidade) {
        this.populacao -= quantidade;
        if (this.populacao < 0) {
            this.populacao = 0;
        }
    }
    /**
     * Método para obter a quantidade de ouro
     * @return A quantidade de ouro
     */
    public int getOuro() {
        return gestaoOuro.obterQuantidade();
    }


    /**
     * Método para adicionar ouro
     * @param quantidade A quantidade de ouro a ser adicionada
     */
    public void adicionarOuro(int quantidade) {
        gestaoOuro.adicionarOuro(quantidade);
    }

    /**
     * Método para remover ouro
     * @param quantidade A quantidade de ouro a ser removida
     */
    public void removerOuro(int quantidade) {
        gestaoOuro.removerOuro(quantidade);
    }

    /**
     * Método para produzir ouro
     * @param terreno O terreno a ser trabalhado
     * @param mapa O mapa
     */
    public void alocarCidadao(Terrenos terreno, Mapa mapa) {
        if (terrenosTrabalhados.size() >= populacao) {
            System.out.println("Todos os cidadãos estão alocados.");
            return;
        }
        if (!(terreno instanceof Floresta || terreno instanceof Pedreira)) {
            System.out.println("Este terreno não pode ser trabalhado.");
            return;
        }
        int distanciaX = Math.abs(terreno.getX() - this.getPosX());
        int distanciaY = Math.abs(terreno.getY() - this.getPosY());
        if (distanciaX > raioInfluencia || distanciaY > raioInfluencia) {
            System.out.println("O terreno está muito distante para ser alocado.");
            return;
        }
        if (terrenosTrabalhados.contains(terreno)) {
            System.out.println("Este terreno já foi alocado.");
            return;
        }
        Terrenos terrenoMapa = mapa.getMapa()[terreno.getX()][terreno.getY()];
        if (terrenoMapa instanceof Floresta || terrenoMapa instanceof Pedreira) {
            mapa.getMapa()[terreno.getX()][terreno.getY()] = new TerrenoTrabalhado(terreno, this.getNome(), 1, terreno.getX(), terreno.getY());
            terrenosTrabalhados.add(terreno); 
            mapa.adicionarTerrenoTrabalhado(terreno);
            System.out.println("Cidadão alocado ao terreno " + terreno);
        } else {
            System.out.println("O terreno não pode ser trabalhado porque não é Floresta ou Pedreira.");
        }
    }

    /**
     * Método para desalocar um cidadão
     * @param terreno O terreno a ser desalocado
     */
    public void desalocarCidadão(Terrenos terreno) {
        if (terrenosTrabalhados.remove(terreno)) {
            System.out.println("Cidadão desalocado do terreno: " + terreno.toString());
        } else {
            System.out.println("Esse terreno não está sendo trabalhado.");
        }
    }
    /**
     * Método para produzir recursos
     */
    public void produzirRecursos() {  
        totalComida = 0;
        totalProducao = 0;
        totalOuro = 0;
        
        for (Terrenos terreno : terrenosTrabalhados) {
            if (terreno instanceof Floresta) {
                totalComida += ((Floresta) terreno).getProducaoPorTurno();
            } else if (terreno instanceof Pedreira) {
                totalOuro += ((Pedreira) terreno).getProducaoPorTurno();
            }
        }
        gestaoComida.produzirComida(totalComida);
        gestaoProducao.setProducaoPorTurno(totalProducao);
        gestaoOuro.adicionarOuro(totalOuro);
    
        System.out.printf("Cidade %s produziu %d comida, %d produção e %d ouro.\n", nome, totalComida, totalProducao, totalOuro);
    }

    /**
     * Método para obter a quantidade de comida
     * @return A quantidade de comida
     */
    public void proximoTurno() {
        produzirRecursos();
        consumirComidaPorPopulacao();
        crescerPopulacao();
        gestaoComida.proximoTurno();
        gestaoProducao.reiniciarProducao();
    }
    /**
     * Método para criar uma unidade
     * @param player O jogador
     * @param tipo O tipo de unidade
     * @param nomeCidade O nome da cidade
     * @param mapa O mapa
     */
    public void criarUnidade(Player player, String tipo, String nomeCidade, Mapa mapa) {
        Unidade novaUnidade = null;
        int custoproducao; 
        switch (tipo.toLowerCase()) {
            case "militar":
                novaUnidade = new Militares("Militar");
                custoproducao = 5; 
                break;
            case "colono":
                novaUnidade = new Colonos("Colono");
                custoproducao = 3; 
                break;
            case "construtor":
                novaUnidade = new Construtores("Construtor");
                custoproducao = 3;
                break;
            default:
                System.out.println("Tipo de unidade desconhecido.");
                return; 
        }
        if (gestaoProducao.usarProducao(custoproducao)) {
            if (mapa.adicionarUnidade(player, novaUnidade, nomeCidade)) {
                player.addUnidade(novaUnidade);
                incrementarContadorUnidade(novaUnidade);
                System.out.println(tipo + " criado próximo à cidade " + nomeCidade + " usando " + custoproducao + " de produção.");

        } else {
                System.out.println("Não foi possível adicionar unidade ao mapa.");
            }
        } else {
            System.out.println("Produção insuficiente para criar " + tipo + " perto da cidade " + nomeCidade);
        }
    }
    
    /**
     * Método para atacar uma cidade
     * @param dano O dano a ser causado
     */
    public void takeDamage(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            System.out.println("A cidade " + nome + " foi destruída.");
        } else {
            System.out.println("A cidade " + nome + " sofreu dano. Vida restante: " + this.vida);
        }
    }
    // Getters
    public Player getPlayer() {
        return player;
    }
    /**
     * Método para adicionar uma unidade
     * @param unidade A unidade a ser adicionada
     */
    public void adicionarUnidade(Unidade unidade) {
        unidades.add(unidade);
    }
    /**
     * Método para retornar a cidade
     */
    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Cidade: ").append(nome).append("\n");
        info.append("População: ").append(populacao).append("\n");
        info.append("Produção: ").append(gestaoProducao).append("\n");
        info.append("Comida: ").append(gestaoComida.getReservaDeComida()).append("\n");
        info.append("Vida: ").append(vida).append("\n");
        info.append("Ouro: ").append(getOuro()).append("\n");
        info.append("Unidades:\n");
        info.append("- Colonos: ").append(contadorColonos).append("\n");
        info.append("- Militares: ").append(contadorMilitares).append("\n");
        info.append("- Construtores: ").append(contadorConstrutores).append("\n");
        return info.toString();
    }
    /**
     * Método para crescer a população da cidade
     */
    public void crescerPopulacao() {
        if (gestaoComida.getReservaDeComida() >= 100) { 
            gestaoComida.produzirComida(-20);
            populacao++;
            System.out.printf("Cidade %s cresceu para a população de %d.\n", nome, populacao);
            if (populacao == 10) {
                aumentarRaioInfluencia();
            }
        } else {
            System.out.printf("Cidade %s não tem comida suficiente para crescer.\n", nome);
        }
    }
    /**
     * Método para aumentar o raio de influência da cidade
     */
    private void aumentarRaioInfluencia() {
        raioInfluencia = 4; // Aumenta para 4 espaços (9x9)
        raioAumentado = true; // Marca que o raio foi aumentado
        System.out.printf("Cidade %s evoluiu e seu raio de influência cresceu para %dx%d.\n", nome, raioInfluencia * 2 + 1, raioInfluencia * 2 + 1);
    }
    /**
     * Método para obter o raio de influência
     */
    public boolean isRaioAumentado() {
        return raioAumentado;
    }
    /**
     * Método para obter a produção que resta
     * @return A produção resta
     */
    public int getProducaoRestante() {
        return gestaoProducao.getProducaoRestante();
    }
    /**
     * Método para consumir produção
     * @param quantidade A quantidade a ser consumida
     * @return true se a produção foi consumida, false caso contrário
     */
    public boolean consumirProducao(int quantidade) {
        return gestaoProducao.usarProducao(quantidade);
    }
    /**
     * Método para consumir comida pela população
     */
    public void consumirComidaPorPopulacao() {
        int comidaConsumida = populacao * 10;
        int comidaDisponivel = gestaoComida.getReservaDeComida();
        
        if (comidaDisponivel < comidaConsumida) {
            int pessoasNaoAlimentadas = (comidaConsumida - comidaDisponivel) / 10;
            reduzirPopulacao(pessoasNaoAlimentadas);
            gestaoComida.produzirComida(-comidaDisponivel);
            System.out.printf("Cidade %s não tinha recursos suficientes para alimentar toda a população. %d pessoas morreram.\n", nome, pessoasNaoAlimentadas);
        } else {
            gestaoComida.produzirComida(-comidaConsumida);
            System.out.printf("Cidade %s consumiu %d de comida. Comida restante: %d.\n", nome, comidaConsumida, gestaoComida.getReservaDeComida());
        }
    }

    public void incrementarContadorUnidade(Unidade unidade) {
        if (unidade instanceof Colonos) {
            contadorColonos++;
        } else if (unidade instanceof Militares) {
            contadorMilitares++;
        } else if (unidade instanceof Construtores) {
            contadorConstrutores++;
        }
    }  

}
