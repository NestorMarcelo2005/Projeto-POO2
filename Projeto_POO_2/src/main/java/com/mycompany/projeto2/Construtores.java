/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto2;

/**
 *
 * @author mgng2
 */
public class Construtores extends Unidade{
    public Construtores(String nome) {
        super(nome, 10); 
    }
    

    @Override
    public void performAction() {
        System.out.println(getNome() + " está a construir uma melhoria no terreno.");
        // Lógica para construção de estradas, fazendas, minas, etc.
    }

    public void repairInfrastructure() {
        System.out.println(getNome() + " está a reparar infraestruturas danificadas.");
        // Lógica para reparo

    }
} 

