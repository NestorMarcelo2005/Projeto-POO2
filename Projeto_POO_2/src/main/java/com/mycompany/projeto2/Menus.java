
package com.mycompany.projeto2;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menus {
    /**
     * Imprime o menu principal do jogo.
     * @return String com o menu principal.
     */
    public String ImprimirMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n=== MENU PRINCIPAL ===\n");
        menu.append("1. Mover uma unidade/criar cidade\n");
        menu.append("2. Atacar com uma unidade\n");
        menu.append("3. Gerir cidade\n");
        menu.append("4. Ver o mapa\n");
        menu.append("5. Encerrar turno\n");
        menu.append("Escolha uma opção: ");
        return menu.toString();
    }
    /**
     * Imprime o menu de criação de cidade.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param player Jogador que está criando a cidade.
     * @param mapa Mapa do jogo.
     */
    public void menuUnidades(Scanner scanner, Player player, Mapa mapa) {
        while (true) {
            System.out.println("\nMenu de Unidades:");
            List<Unidade> unidades = player.getUnidades();
            if (unidades.isEmpty()) {
                System.out.println("Você não tem unidades para mover.");
                break;  
            }

            System.out.println("Unidades disponíveis para mover:");
            for (int i = 0; i < unidades.size(); i++) {
                System.out.println((i + 1) + ". " + unidades.get(i).getNome() + " (Posição: " 
                        + unidades.get(i).getPosicaoX() + ", " + unidades.get(i).getPosicaoY() + ")");
            }
            
            System.out.print("Escolha uma unidade para mover (1-" + unidades.size() + "): ");
            int escolha = scanner.nextInt() - 1;

            if (escolha < 0 || escolha >= unidades.size()) {
                System.out.println("Escolha inválida.");
                continue; 
            }

            Unidade unidadeEscolhida = unidades.get(escolha);
            System.out.println("Você escolheu a unidade: " + unidadeEscolhida.getNome());

            System.out.print("Digite a nova posição X (0-9): ");
            int novaPosicaoX = scanner.nextInt();

            System.out.print("Digite a nova posição Y (0-9): ");
            int novaPosicaoY = scanner.nextInt();
                try {
                    mapa.moverUnidade(unidadeEscolhida, novaPosicaoX, novaPosicaoY);
                    System.out.println("Movimento realizado com sucesso!");
                    
                    if (unidadeEscolhida instanceof Colonos) {
                        System.out.print("Deseja fundar uma cidade nesta posição? (sim/nao): ");
                        String resposta = scanner.next();
                        if (resposta.toLowerCase().startsWith("s")) {
                            String nomeCidade = scanner.nextLine();
                            
                            Colonos colono = (Colonos) unidadeEscolhida;
                            boolean sucesso = colono.fundarCidade(player, nomeCidade, mapa, novaPosicaoX, novaPosicaoY);
                            
                            if (!sucesso) {
                                System.out.println("Não foi possível fundar a cidade.");
                            }
                            return;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Falha no movimento: " + e.getMessage());
                }
            System.out.print("Deseja mover outra unidade? (sim/nao): ");
            char resposta = scanner.next().charAt(0);
            if (resposta != 's' && resposta != 'S') {
                break; 
            }
        }
    }
    
    /**
     * Selecionar uma unidade para atacar.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param player Jogador que está criando a cidade.
     * @return Unidade selecionada para atacar.
     */
    private Unidade selecionarUnidade(Scanner scanner, Player player) {
        System.out.println("Unidades disponíveis para atacar:");
    
        List<Unidade> unidades = player.getUnidades();
        if (unidades.isEmpty()) {
            System.out.println("Você não tem unidades para atacar.");
            return null; 
        }
        for (int i = 0; i < unidades.size(); i++) {
            Unidade unidade = unidades.get(i);
            System.out.println(i + ": " + unidade.getNome() + " - Tipo: Militar - Coordenadas: (" + unidade.getPosicaoX() + ", " + unidade.getPosicaoY() + ")");
        }

        System.out.println("Selecione uma unidade para atacar (insira o índice da unidade):");
        int indiceUnidade = scanner.nextInt();

        if (indiceUnidade < 0 || indiceUnidade >= unidades.size()) {
            System.out.println("Unidade inválida!");
            return null; 
        }
        
        return unidades.get(indiceUnidade); 
    }


    /**
     * Obter inimigos no raio de ataque da unidade.
     * @param unidade Unidade que está a atacar.
     * @param mapa Mapa do jogo.
     * @param raio Raio de ataque da unidade.
     * @param jogadorAtual Jogador que está a atacar.
     * @return Lista de inimigos no raio de ataque da unidade.
     */
    private List<Object> obterInimigosNoRaio(Unidade unidade, Mapa mapa, int raio, Player jogadorAtual) {
        List<Object> inimigos = new ArrayList<>();
        int unidadeX = unidade.getPosicaoX();
        int unidadeY = unidade.getPosicaoY();
        for (int dx = -raio; dx <= raio; dx++) {
            for (int dy = -raio; dy <= raio; dy++) {
                int posX = unidadeX + dx;
                int posY = unidadeY + dy;
                if (mapa.coordenadaValida(posX, posY)) {
                    Terrenos terreno = mapa.getCoordenadas(posX, posY);
                        if (terreno instanceof TerrenoUnidade) {
                        Unidade inimiga = ((TerrenoUnidade) terreno).getUnidade();
                        if (inimiga != null && !jogadorAtual.getUnidades().contains(inimiga)) {
                            inimigos.add(inimiga);
                        }
                    }
    
                    if (terreno instanceof TerrenoCidade) {
                        Cidades cidade = ((TerrenoCidade) terreno).getCidade();
                        if (cidade != null && !jogadorAtual.getCidades().contains(cidade)) {
                            inimigos.add(cidade);
                        }
                    }
                }
            }
        }
        return inimigos;
    }


    /**
     * Exibir alvos disponíveis para ataque.
     * @param unidadesAlvo Lista de unidades inimigas disponíveis.
     * @param cidadesAlvo Lista de cidades inimigas disponíveis.
     */
    private void exibirAlvosDisponiveis(List<Unidade> unidadesAlvo, List<Cidades> cidadesAlvo) {
        System.out.println("Alvos disponíveis para ataque:");
    
        if (!unidadesAlvo.isEmpty()) {
            System.out.println("Unidades inimigas:");
            for (int i = 0; i < unidadesAlvo.size(); i++) {
                System.out.println(i + ": " + unidadesAlvo.get(i).toString());
            }
        } else {
            System.out.println("Nenhuma unidade inimiga disponível.");
        }
    
        if (!cidadesAlvo.isEmpty()) {
            System.out.println("Cidades inimigas:");
            for (int i = 0; i < cidadesAlvo.size(); i++) {
                System.out.println((i + unidadesAlvo.size()) + ": " + cidadesAlvo.get(i).getNome());
            }
        } else {
            System.out.println("Nenhuma cidade disponível.");
        }
    }


    /**
     * Selecionar e atacar um alvo, unidade ou cidade.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param unidade Unidade que está a atacar.
     * @param unidadesAlvo Lista de unidades inimigas disponíveis.
     * @param cidadesAlvo Lista de cidades inimigas disponíveis.
     */
    private void selecionarEAtacarAlvo(Scanner scanner, Unidade unidade, List<Unidade> unidadesAlvo, List<Cidades> cidadesAlvo) {
        System.out.println("Escolha o índice do alvo:");
        int indiceAlvo = scanner.nextInt();
        if (indiceAlvo < unidadesAlvo.size()) {
            Unidade alvoUnidade = unidadesAlvo.get(indiceAlvo);
            System.out.println("Atacando unidade inimiga: " + alvoUnidade.getNome());
            unidade.attack(alvoUnidade);
        } else if (indiceAlvo - unidadesAlvo.size() < cidadesAlvo.size()) {
            Cidades alvoCidade = cidadesAlvo.get(indiceAlvo - unidadesAlvo.size());
            System.out.println("Atacando a cidade: " + alvoCidade.getNome());
            unidade.attackCity(alvoCidade);
        } else {
            System.out.println("Alvo inválido.");
        }
    }



    /**
     * Menu para gerenciar cidades.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param player Jogador que está gerenciando a cidade.
     * @param mapa Mapa do jogo.
     */
    public void menuGerirCidades(Scanner scanner, Player player, Mapa mapa) {
        System.out.println("Selecione uma cidade para gerenciar:");
        
        List<Cidades> cidades = player.getCidades();
        if (cidades.isEmpty()) {
            System.out.println("Você não possui cidades para gerenciar.");
            return;
        }
        
        for (int i = 0; i < cidades.size(); i++) {
            System.out.println((i + 1) + ". " + cidades.get(i).getNome());
        }
        System.out.print("Escolha o número da cidade: ");
        int escolhaCidade = scanner.nextInt();
        scanner.nextLine();
        
        if (escolhaCidade < 1 || escolhaCidade > cidades.size()) {
            System.out.println("Escolha inválida!");
            return;
        }
        
        Cidades cidadeSelecionada = cidades.get(escolhaCidade - 1);
        boolean gerenciando = true;
        
        while (gerenciando) {
            System.out.println("\n== GERENCIAR CIDADE: " + cidadeSelecionada.getNome() + " ==");
            System.out.println("1. Visualizar informações da cidade");
            System.out.println("2. Criar Unidade Militar");
            System.out.println("3. Criar Unidade Colono");
            System.out.println("4. Criar Unidade Construtor");
            System.out.println("5. Gerenciar terrenos");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (mapa == null) {
        return;
    }
            
            switch (escolha) {
                case 1:
                    System.out.println(cidadeSelecionada.toString());
                    break;
                case 2:
                    cidadeSelecionada.criarUnidade(player,"Militar",cidadeSelecionada.getNome(),mapa);
                    break;
                case 3:
                    cidadeSelecionada.criarUnidade(player,"Colono",cidadeSelecionada.getNome(),mapa);
                    break;
                case 4:
                    cidadeSelecionada.criarUnidade(player,"Construtor",cidadeSelecionada.getNome(),mapa);
                    break;
                case 5:
                    menuGerirTerrenos(scanner, player, mapa);
                    break;
                case 6:
                    gerenciando = false;
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    /**
     * Menu para gerenciar terrenos de uma cidade.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param player Jogador que está gerenciando a cidade.
     * @param mapa Mapa do jogo.
     */
    public void menuGerirTerrenos(Scanner scanner, Player player, Mapa mapa) {
        for (Cidades cidade : player.getCidades()) {
            System.out.println("Gerenciando terrenos para a cidade: " + cidade.getNome());
            boolean alocando = true;
            while (alocando) {
                System.out.println("1. Alocar cidadão a um terreno");
                System.out.println("2. Desalocar cidadão de um terreno");
                System.out.println("3. Voltar");
                int escolha = scanner.nextInt();
                switch (escolha) {
                        case 1: 
                        exibirCoordenadas(cidade.getPosX(),cidade.getPosY(), mapa); 
                        System.out.println("Coordenadas da cidade: (" + cidade.getPosX() + ", " + cidade.getPosY() + ")");
                        System.out.println("Digite as coordenadas (x y) do terreno para alocar cidadão:");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt(); 
                        Terrenos terreno = mapa.getCoordenadas(x, y);
                        if (terreno == null) {
                            System.out.println("Coordenadas inválidas ou terreno não encontrado.");
                        } else {
                            cidade.alocarCidadao(terreno, mapa);
                        }
                        break;
                    case 2:
                        System.out.println("Digite as coordenadas (x y) do terreno para desalocar cidadão:");
                        x = scanner.nextInt();
                        y = scanner.nextInt();
                        terreno = mapa.getCoordenadas(x, y);
                        cidade.desalocarCidadão(terreno);
                        break;
                    case 3:
                        alocando = false;
                        break;
                    default:
                        System.out.println("Escolha inválida, tente novamente.");
                }
            }
        }
    }
    /**
     * Exibir coordenadas ao redor da cidade.
     * @param x Coordenada X da cidade.
     * @param y Coordenada Y da cidade.
     * @param mapa Mapa do jogo.
     */
    public void exibirCoordenadas(int x, int y, Mapa mapa) {
        Terrenos[][] matrizMapa = mapa.getMapa(); 
        int tamanhoMapa = matrizMapa.length;
        int inicioX = Math.max(0, x - 3);
        int fimX = Math.min(tamanhoMapa - 1, x + 3);
        int inicioY = Math.max(0, y - 3);
        int fimY = Math.min(tamanhoMapa - 1, y + 3);
        System.out.println("Coordenadas ao redor da cidade:");
        for (int i = inicioX; i <= fimX; i++) {
            for (int j = inicioY; j <= fimY; j++) {
                Terrenos terreno = matrizMapa[i][j];
                if (terreno != null) {
                    if (terreno instanceof Floresta) {
                        System.out.println("Floresta encontrada em (" + j + ", " + i + ")");
                    } else if (terreno instanceof Pedreira) {
                        System.out.println("Pedreira encontrada em (" + j + ", " + i + ")");
                    }
                } else {
                    System.out.println("Espaço vazio em (" + j + ", " + i + ")");
                }
            }
        }
    }
    
    /**
     * Menu para criar uma cidade.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param player Jogador que está criando a cidade.
     * @return true se a cidade foi criada com sucesso, false caso contrário.
     */
    public boolean encerrarTurno(Scanner scanner, Player player) {
        System.out.println("Tem certeza que deseja encerrar seu turno? (1 - Sim, 2 - Não)");
        int confirmacao = scanner.nextInt();
        return confirmacao == 1;
    }


    /**
     * Menu para atacar com uma unidade.
     * @param scanner Scanner para leitura de dados do usuário.
     * @param player Jogador que está a atacar.
     * @param mapa Mapa do jogo.
     */
    public void menuAtacarUnidade(Scanner scanner, Player player, Mapa mapa) {
        System.out.println("=== Atacar com uma Unidade ===");
        Unidade unidade = selecionarUnidade(scanner, player);
        if (unidade == null) return;
        exibirUnidadesComCoordenadas(player);
        List<Object> inimigosAlvo = obterInimigosNoRaio(unidade, mapa, 1, player);
        List<Unidade> unidadesAlvo = new ArrayList<>();
        List<Cidades> cidadesAlvo = new ArrayList<>();

        for (Object inimigo : inimigosAlvo) {
            if (inimigo instanceof Unidade) {
                unidadesAlvo.add((Unidade) inimigo);
            } else if (inimigo instanceof Cidades) {
                cidadesAlvo.add((Cidades) inimigo);
            }
        }
        exibirAlvosDisponiveis(unidadesAlvo, cidadesAlvo);
        selecionarEAtacarAlvo(scanner, unidade, unidadesAlvo, cidadesAlvo);
    }
    
    

    /**
     * Exibir unidades e suas coordenadas.
     * @param player Jogador que tem a unidade.
     */
    private void exibirUnidadesComCoordenadas(Player player) {
        System.out.println("Unidades e suas coordenadas para o jogador: " + player.getNome());
        for (Unidade unidade : player.getUnidades()) {
            int x = unidade.getPosicaoX();
            int y = unidade.getPosicaoY();
            System.out.println(unidade.getNome() + " - Coordenadas: (" + x + ", " + y + ")");
        }
    }

    /**
     * Menu para imprimir a legenda do mapa
     * @return String com a legenda do mapa.
     */
    public String imprimeLegenda(){
        StringBuilder legenda = new StringBuilder();
        legenda.append("\n=== LEGENDA ===\n");
        legenda.append("C - Cidade\n");
        legenda.append("A - Água\n");
        legenda.append("F - Floresta\n");
        legenda.append("P - Pedreira\n");
        legenda.append("T - Terreno Trabalhado\n");
        legenda.append("M - Unidade Militar\n");
        legenda.append("B - Construtores\n");
        legenda.append("H - Colonos\n");
        return legenda.toString();
    }
} 