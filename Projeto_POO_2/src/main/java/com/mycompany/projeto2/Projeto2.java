/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author nelso    
 */
public class Projeto2 {
    
    private Floresta floresta;
    private Pedreira pedreira;
    private Menus menus;
    
    
    public static void imprimirMapa(char[][] mapa) {
        int comprimento = mapa.length;
        int largura = mapa[0].length;

        System.out.print("   "); 
        for (int col = 0; col < largura; col++) {
            System.out.printf("%3dx ", col);
        }
        System.out.println();

        for (int linha = 0; linha < comprimento; linha++) {
            System.out.printf("%3dy ", linha); 
            for (int col = 0; col < largura; col++) {
                System.out.print(" " + mapa[linha][col] + "   ");
            }
            System.out.println();
        }
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int largura = 20;      
        int comprimento = 20;  
        Menus menus = new Menus();
        Mapa mapa = new Mapa(largura, comprimento);
        boolean cidadeCriada = false; 
        List<Player> players = new ArrayList<>();
        Terrenos[][] terrenos = mapa.getMapa();
        System.out.println("Quantos jogadores irao participar?");
        int numJogadores = scanner.nextInt();
        scanner.nextLine();  

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Nome do jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            players.add(new Player(nome));
        }

        boolean jogoAtivo = true;
        int turno = 1;

        System.out.println("\n=== CRIAÇÃO DE CIDADES INICIAIS ===");
        for (Player player : players) {
            System.out.println("\n### " + player.getNome() + ", escolha uma posição para sua primeira cidade. ###");
            System.out.println(menus.imprimeLegenda()); 
            mapa.exibirMapa(); 
            cidadeCriada = false;
            while (!cidadeCriada) {
                System.out.println("Digite as coordenadas (x y) para criar sua cidade:");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (mapa.criarCidade(x, y, player)) {
                    cidadeCriada = true;
                } else {
                    System.out.println("Posição inválida para criar uma cidade. Tente novamente.");
                }
            }
        }
        while (jogoAtivo) {
            System.out.println("\n===== TURNO " + turno + " =====");
            if (turno > 1) { 
                System.out.println("\n=== FINALIZANDO TURNO DAS CIDADES ===");
                for (Player player : players) {
                    for (Cidades cidade : player.getCidades()) {
                        cidade.proximoTurno(); 
                        System.out.println("A cidade " + cidade.getNome() + " finalizou seu turno.");
                    }
                }
            }
            for (Player player : players) {
                System.out.println("\n### Turno de " + player.getNome() + " ###");
                System.out.println(menus.imprimeLegenda()); 
                mapa.exibirMapa(); 
                boolean turnoAtivo = true;
                
                while (turnoAtivo) { 
                    System.out.println(menus.ImprimirMenu());
                    int escolha = scanner.nextInt();
                    
                    switch (escolha) {
                        case 1: 
                            menus.menuUnidades(scanner, player, mapa);
                            break;
                            
                        case 2: 
                            menus.menuAtacarUnidade(scanner, player, mapa);
                            break;
                            
                        case 3:
                            menus.menuGerirCidades(scanner, player, mapa);
                            break;
                            
                        case 4:
                            System.out.println(menus.imprimeLegenda()); 
                            mapa.exibirMapa();
                            break;
                        case 5:
                            if (menus.encerrarTurno(scanner, player)) {
                                System.out.println(player.getNome() + " encerrou seu turno.");
                                turnoAtivo = false;
                            } else {
                                System.out.println("Turno ainda ativo. Continue suas ações.");
                            }
                            break;
                            
                        default:
                            System.out.println("Escolha inválida, tente novamente.");
                    }
                    int ouroTotal = player.calcularOuroTotal();
                    System.out.println("Ouro total produzido pelas cidades: " + ouroTotal); 
                }
            }

            System.out.println("\nFim do turno " + turno + ".");
            System.out.println("Deseja continuar o jogo? (1 - Sim, 2 - Não)");
            int continuar = scanner.nextInt();
            
            if (continuar != 1) {
                jogoAtivo = false;
            }
            turno++;
        }
        
        System.out.println("\nJogo encerrado. Obrigado por jogar!");
        scanner.close();
    }
}
