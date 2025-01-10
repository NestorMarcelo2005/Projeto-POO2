package com.mycompany.projeto2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Representa o mapa do jogo, contendo terrenos, cidades e unidades.
 * Permite manipular e interagir com os elementos do mapa, como criar cidades,
 * trabalhar terrenos e mover unidades.
 */
public class Mapa {
    private Terrenos[][] mapa;
    private Random random;
    private int numero;
    private List<Cidades> listcidades;
    private final int distanciaMin = 2;
    private List<Terrenos> terrenosTrabalhados;
    private List<int[]> posicoesCidades;
    private Player player;

    /**
     * Construtor que inicializa o mapa com dimensões especificadas.
     *
     * @param largura    largura do mapa.
     * @param comprimento comprimento do mapa.
     */
    public Mapa(int largura, int comprimento) {
        this.mapa = new Terrenos[comprimento][largura];
        this.random = new Random();
        this.listcidades = new ArrayList<>();
        this.posicoesCidades = new ArrayList<>();
        this.terrenosTrabalhados = new ArrayList<>();
        preencherMapa();
    }

    /**
     * Cria uma nova cidade no mapa, se a posição for válida.
     * @param x      coordenada X da cidade.
     * @param y      coordenada Y da cidade.
     * @param player jogador que possui a cidade.
     * @return true se a cidade foi criada com sucesso, false caso contrário.
     */
    public boolean criarCidade(int x, int y, Player player) {
        try {
            if (!(mapa[y][x] instanceof TerrenoVazio)) {
                throw new IllegalArgumentException("O terreno não é adequado para uma cidade.");
            }
            if (!posicaoLivre(x, y)) {
                throw new IllegalArgumentException("A posição está muito próxima de outra cidade.");
            }

            Cidades novaCidade = new Cidades(10, 0, 10, x, y, this, player);
            posicoesCidades.add(new int[]{x, y});
            listcidades.add(novaCidade);
            player.adicionarCidade(novaCidade);

            mapa[y][x] = new TerrenoCidade(novaCidade, x, y);

            System.out.println("Cidade " + novaCidade.getNome() + " criada em (" + x + ", " + y + ")");
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar cidade: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cria um terreno trabalhado em uma posição específica, se permitido.
     *
     * @param x       coordenada X do terreno.
     * @param y       coordenada Y do terreno.
     * @param cidades cidade que trabalha o terreno.
     * @return true se o terreno foi trabalhado com sucesso, false caso contrário.
     */
    public boolean criarTerrenoTrabalhado(int x, int y, Cidades cidades) {
        try {
            if (!(mapa[y][x] instanceof Floresta || mapa[y][x] instanceof Pedreira)) {
                System.out.println("Terreno encontrado: " + mapa[y][x].getClass().getName());
                throw new IllegalArgumentException("O terreno não é adequado para trabalho. Esperado 'Floresta' ou 'Pedreira'!");
            }
            if (mapa[y][x] instanceof TerrenoTrabalhado) {
                throw new IllegalArgumentException("Este terreno já foi trabalhado.");
            }
            if (!posicaoLivre(x, y)) {
                throw new IllegalArgumentException("A posição está ocupada por outra unidade ou cidade.");
            }

            Terrenos terrenoOriginal = mapa[y][x];
            int quantidadeTrabalhadores = 1;
            TerrenoTrabalhado terrenoTrabalhado = new TerrenoTrabalhado(terrenoOriginal, cidades.getNome(), quantidadeTrabalhadores, x, y);
            mapa[y][x] = terrenoTrabalhado;
            cidades.adicionarTerrenoTrabalhado(terrenoTrabalhado);
            System.out.println("Terreno trabalhado criado em (" + x + ", " + y + ") pela cidade " + cidades.getNome());
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar terreno trabalhado: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Preenche o mapa com terrenos aleatórios.
     */
    public void preencherMapa() {
        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[i].length; j++) {
                numero = random.nextInt(101);
                if (numero < 5) {
                    this.mapa[i][j] = new Floresta(i, j);
                } else if (numero > 95) {
                    this.mapa[i][j] = new Pedreira(i, j);
                } else if (numero < 85 && numero > 80) {
                    this.mapa[i][j] = new Agua(i, j);
                } else {
                    this.mapa[i][j] = new TerrenoVazio(i, j);
                }
            }
        }
    }

    /**
     * Verifica se uma posição está livre para ser ocupada.
     *
     * @param x coordenada X da posição.
     * @param y coordenada Y da posição.
     * @return true se a posição está livre, false caso contrário.
     */
    public boolean posicaoLivre(int x, int y) {
        for (int[] cidade : posicoesCidades) {
            int cidadeX = cidade[0];
            int cidadeY = cidade[1];
            int minX = cidadeX - distanciaMin;
            int maxX = cidadeX + distanciaMin;
            int minY = cidadeY - distanciaMin;
            int maxY = cidadeY + distanciaMin;

            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Exibe o mapa na consola, representando cada tipo de terreno com um caractere específico.
     */
    public void exibirMapa() {
        int larguraMaxima = String.valueOf(mapa[0].length - 1).length();

        System.out.print("     ");
        for (int x = 0; x < mapa[0].length; x++) {
            System.out.print(String.format("x%-" + larguraMaxima + "d ", x));
        }
        System.out.println();

        for (int y = 0; y < mapa.length; y++) {
            System.out.print(String.format("y%-" + (larguraMaxima + 1) + "d", y));

            for (int x = 0; x < mapa[y].length; x++) {
                Terrenos terreno = mapa[y][x];
                String campo;

                if (terreno instanceof TerrenoTrabalhado) {
                    campo = "T";
                } else if (terreno instanceof Floresta) {
                    campo = "F";
                } else if (terreno instanceof Pedreira) {
                    campo = "P";
                } else if (terreno instanceof TerrenoCidade) {
                    campo = "C";
                } else if (terreno instanceof TerrenoUnidade) {
                    Unidade unidade = ((TerrenoUnidade) terreno).getUnidade();
                    if (unidade instanceof Militares) {
                        campo = "M";
                    } else if (unidade instanceof Construtores) {
                        campo = "B";
                    } else if (unidade instanceof Colonos) {
                        campo = "H";
                    } else {
                        campo = "?";
                    }
                } else {
                    campo = terreno.toString();
                }

                System.out.print("  " + campo + " ");
            }
            System.out.println();
        }
    }

    /**
     * Obtém o terreno em uma posição específica do mapa.
     * @param unidade unidade que vai ser movida
     * @param novaPosicaoX nova coordenada X da posição
     * @param novaPosicaoY nova coordenada Y da posição
     */
    public void moverUnidade(Unidade unidade, int novaPosicaoX, int novaPosicaoY) {
        try {
            int posXAtual = unidade.getPosicaoX();
            int posYAtual = unidade.getPosicaoY();
            
            Terrenos terrenoDestino = mapa[novaPosicaoY][novaPosicaoX];
            int custoMovimento = terrenoDestino.getCustoMovimento();
            
            if (unidade.getPontosMovimentos() < custoMovimento) {
                throw new IllegalArgumentException("Pontos insuficientes para movimento");
            }
    
            System.out.println("Posição Inicial: (" + posXAtual + "," + posYAtual + ")");
            mapa[posYAtual][posXAtual] = new TerrenoVazio(posYAtual,posXAtual);
    
            mapa[novaPosicaoY][novaPosicaoX] = new TerrenoUnidade(unidade,novaPosicaoY,novaPosicaoX);
            unidade.setPosicao(novaPosicaoX, novaPosicaoY);
            
            unidade.reduzirPontosMovimento(custoMovimento);
            
            System.out.println("Pontos de movimento restantes: " + unidade.getPontosMovimentos());
            System.out.println("Após movimento:");
            exibirMapa();
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Obtém o terreno em uma posição específica do mapa.
     * @return o terreno na posição especificada.
     */
    public List<Terrenos> getTerrenosTrabalhados() {
        return terrenosTrabalhados;
    }
    /**
     * Adiciona um terreno trabalhado à lista de terrenos trabalhados.
     * @param terreno o terreno trabalhado a ser adicionado.
     */
    public void adicionarTerrenoTrabalhado(Terrenos terreno) {
        terrenosTrabalhados.add(terreno);
    }
    /**
     * Busca uma cidade pelo nome.
     * @param nome o nome da cidade a ser buscada.
     * @return a cidade encontrada, ou null se não encontrada.
     */
    public Cidades buscarCidadePorNome(String nome) {
        for (Cidades cidade : listcidades) {
            if (cidade.getNome().equals(nome)) {
                return cidade;
            }
        }
        return null;
    }

    /**
     * Busca a posição da cidade pelo nome.
     * @param nome o nome da cidade a ser buscada.
     * @return a cidade encontrada, ou null se não encontrada.
     */
    public int[] buscarPosicaoCidade(String nome) {
        for (int i = 0; i < posicoesCidades.size(); i++) {
            if (listcidades.get(i).getNome().equals(nome)) {
                return posicoesCidades.get(i);
            }
        }
        return null;
    } 

    /**
     * Adiciona uma unidade ao mapa.
     * @param player o jogador que possui a unidade.
     * @param unidade a unidade a ser adicionada.
     * @param nomeCidade o nome da cidade onde a unidade será adicionada.
     * @return true se a unidade foi adicionada com sucesso, false caso contrário.
     */
    public boolean adicionarUnidade(Player player, Unidade unidade, String nomeCidade) {
        try {
            for (Cidades cidade : player.getCidades()) {
                if (cidade.getNome().equals(nomeCidade)) {
                        int cidadeX = cidade.getPosX();
                    int cidadeY = cidade.getPosY();
    
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            
                            if (dx == 0 && dy == 0) continue; 
                            
                            int novaX = normalizarCoordenada(cidadeX + dx, mapa[0].length);
                            int novaY = normalizarCoordenada(cidadeY + dy, mapa.length);
    
                            if (mapa[novaY][novaX] instanceof TerrenoVazio) {
                                
                                unidade.setPosicao(novaX, novaY);
                                mapa[novaY][novaX] = new TerrenoUnidade(unidade, novaX, novaY);
                                System.out.println("Unidade " + unidade.getNome() + " criada ao lado da cidade " + nomeCidade + " em (" + novaX + ", " + novaY + ").");
                                return true;
                            }
                        }
                    }
                    throw new IllegalArgumentException("Não há espaço disponível ao redor da cidade '" + nomeCidade + "' para adicionar a unidade.");
                }
            }
            throw new IllegalArgumentException("Cidade com o nome '" + nomeCidade + "' não encontrada.");
    
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar unidade: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }
    /**
     * Verifica se uma coordenada é válida no mapa.
     * @param x a coordenada X.
     * @param y a coordenada Y.
     * @return true se a coordenada é válida, false caso contrário.
     */
    public boolean coordenadaValida(int x, int y) {
        return x >= 0 && x < mapa[0].length && y >= 0 && y < mapa.length;
    }
    /**
     * Obtém o terreno em uma posição específica do mapa.
     * @param x a coordenada X da posição.
     * @param y a coordenada Y da posição.
     * @param raio o raio de busca.
     * @return a lista de terrenos no raio especificado.
     */
    public List<Terrenos> obterTerrenosNoRaio(int x, int y, int raio) {
        List<Terrenos> terrenosNoRaio = new ArrayList<>();
    
        for (int dx = -raio; dx <= raio; dx++) {
            for (int dy = -raio; dy <= raio; dy++) {
                int posX = normalizarCoordenada(x + dx, mapa[0].length);
                int posY = normalizarCoordenada(y + dy, mapa.length);
    
                if (coordenadaValida(posX, posY)) {
                    terrenosNoRaio.add(mapa[posY][posX]);
                }
            }
        }
    
        return terrenosNoRaio;
    }
   
    /**
     * Define as coordenadas de um terreno no mapa.
     * @param x a coordenada X.
     * @param y a coordenada Y. 
     * @param terreno o terreno a ser definido.
     */
    public void definirCoordenadas(int x, int y, Terrenos terreno) {
        if (coordenadaValida(x, y)) {
            mapa[x][y] = terreno;
            System.out.println("Coordenadas (" + x + ", " + y + ") definidas como: " + terreno.getClass().getSimpleName());
        } else {
            System.out.println("Erro: Coordenadas (" + x + ", " + y + ") inválidas!");
        }
    }

    /**
     * Obtém o terreno em uma posição específica do mapa.
     * @param unidade unidade que vai ser movida
     */
    public void removerUnidadeDoMapa(Unidade unidade) {
        if (unidade == null) return;
        int x = unidade.getPosicaoX();
        int y = unidade.getPosicaoY();
        if (x < 0 || x >= mapa.length || y < 0 || y >= mapa[0].length) {
            System.out.println("Posição inválida no mapa.");
            return;
        } 
        if (mapa[x][y] instanceof Terrenos) { 
            Terrenos terrenoAtual = mapa[x][y];
            if (terrenoAtual.temUnidade() && terrenoAtual.getUnidade() == unidade) {
                mapa[x][y] = new TerrenoVazio(x, y);
                System.out.println("Unidade removida do mapa na posição (" + x + ", " + y + ").");
            } else {
                System.out.println("A posição (" + x + ", " + y + ") não contém a unidade especificada.");
            }
        } else {
            System.out.println("A posição (" + x + ", " + y + ") não é válida para remoção de unidade.");
        }
    }

    /**
     * Adiciona uma unidade ao mapa.
     * @param unidade a unidade a ser adicionada.
     * @param nomeCidade o nome da cidade onde a unidade será adicionada.
     * @return true se a unidade foi adicionada com sucesso, false caso contrário.
     */
    public boolean adicionarUnidade(Unidade unidade, String nomeCidade) {
        try {
            for (int i = 0; i < posicoesCidades.size(); i++) {
                Cidades cidade = listcidades.get(i);
                if (cidade.getNome().equals(nomeCidade)) {
                    int cidadeX = posicoesCidades.get(i)[0];
                    int cidadeY = posicoesCidades.get(i)[1];

                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (dx == 0 && dy == 0) continue; 
                            
                            int novaX = normalizarCoordenada(cidadeX + dx, mapa[0].length);
                            int novaY = normalizarCoordenada(cidadeY + dy, mapa.length);
                            
                            if (mapa[novaY][novaX] instanceof TerrenoVazio) {
                                unidade.setPosicao(novaX, novaY);
                                mapa[novaY][novaX] = new TerrenoUnidade(unidade,novaY,novaX);
                                System.out.println("Unidade " + unidade.getNome() + " criada ao lado da cidade " + nomeCidade + " em (" + novaX + ", " + novaY + ").");
                                return true;
                            }
                        }
                    }
                    
                    throw new IllegalArgumentException("Não há espaço disponível ao redor da cidade '" + nomeCidade + "' para adicionar a unidade.");
                }
            }
            
            throw new IllegalArgumentException("Cidade com o nome '" + nomeCidade + "' não encontrada.");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar unidade: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }
    /**
     * Normaliza uma coordenada para o tamanho do mapa.
     * @param coordenada a coordenada a ser normalizada.
     * @param limite o tamanho do mapa.
     * @return a coordenada normalizada.
     */
    private int normalizarCoordenada(int coordenada, int limite) {
        if (coordenada < 0) {
            return (coordenada % limite + limite) % limite;
        }
        return coordenada % limite;
    }
    /**
     * Obtém o terreno em uma posição específica do mapa.
     * @param x a coordenada X da posição.
     * @param y a coordenada Y da posição.
     * @return o terreno na posição especificada.
     */
    public Terrenos getCoordenadas(int x, int y) {
        x = normalizarCoordenada(x, this.mapa[0].length);
        y = normalizarCoordenada(y, this.mapa.length);
        return this.mapa[y][x];
    }
    /**
     * Obtém o terreno em uma posição específica do mapa.
     * @return o terreno na posição especificada.
     */
    public Terrenos[][] getMapa() {
        return this.mapa;
    }
    public boolean existeCidadeProxima(int x, int y) {
        int distanciaMinima = 2; // Distância minima entre cidades
        
        for (Cidades cidade : listcidades) {
            int distanciaX = Math.abs(cidade.getPosX() - x);
            int distanciaY = Math.abs(cidade.getPosY() - y);
            
            if (distanciaX <= distanciaMinima && distanciaY <= distanciaMinima) {
                return true;
            }
        }
        return false;
    }
    public boolean criarCidade2(int x, int y, Player player) {
        try {
            // Verifica se a posição é um colono
            Terrenos terrenoAtual = mapa[y][x];
            if (terrenoAtual instanceof TerrenoUnidade && ((TerrenoUnidade) terrenoAtual).getUnidade() instanceof Colonos) {
                // Remove o colono (transforma o terreno ocupado por ele em vazio)
                mapa[y][x] = new TerrenoVazio(x, y);
            } else if (terrenoAtual instanceof Agua || !(terrenoAtual instanceof TerrenoVazio)) {
                throw new IllegalArgumentException("O terreno não é adequado para uma cidade.");
            }
    
            // Verifica se já existe uma cidade na posição (x, y)
            for (int[] pos : posicoesCidades) {
                if (pos[0] == x && pos[1] == y) {
                    throw new IllegalArgumentException("Já existe uma cidade nesta posição.");
                }
            }
    
            // Cria a nova cidade
            Cidades novaCidade = new Cidades(0, 0, 0, x, y, this, player);
            novaCidade.setNome("Cidade" + (listcidades.size() + 1));
    
            // Atualiza o mapa para refletir a cidade
            mapa[y][x] = new TerrenoCidade(novaCidade, x, y);
    
            // Adiciona a cidade à lista de cidades do player
            player.adicionarCidade(novaCidade);
    
            // Registra a posição da cidade
            posicoesCidades.add(new int[]{x, y});
    
            System.out.println("Cidade " + novaCidade.getNome() + " criada em (" + x + ", " + y + ")");
            return true;
    
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar cidade: " + e.getMessage());
            return false;
        }
    }
}
