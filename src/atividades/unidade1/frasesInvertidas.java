package atividades.unidade1;

import esd.Pilha;

import java.util.Scanner;

public class frasesInvertidas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva uma frase: ");
        String frase = sc.nextLine();
        System.out.println("Conferindo...");
        sc.close();


        String [] palavras = frase.split(" ");

        Pilha<String> pilha = new Pilha<>();

        for (String palavra : palavras) {
            pilha.empilha(palavra);
        }

        boolean igual = true;
        int i =0;
        while (!pilha.estaVazia() && igual) {
            String palavraOriginal = palavras[i];
            String palavraInvertida = pilha.desempilha();

            if (!palavraOriginal.equals(palavraInvertida)) {
                igual = false;
            }
            i++;
        }
        System.out.println(igual ? "VERDADEIRO" : "FALSO");
    }
}
