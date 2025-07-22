package atividades.hash;

import esd.TabHash;
import esd.ListaSequencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SepararPalavrasPorCaso {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Arquivo inválido");
            return;
        }

        String nomeArquivo = args[0];
        File arquivo = new File(nomeArquivo);
        Scanner scanner;

        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo inválido");
            return;
        }

        // Listas para armazenar as palavras que começam com maiúscula e minúscula
        ListaSequencial<String> maiusculas = new ListaSequencial<>();
        ListaSequencial<String> minusculas = new ListaSequencial<>();

        // Lê as palavras do arquivo e as separa de acordo com o caso da primeira letra
        while (scanner.hasNext()) {
            String palavra = scanner.next();

            // Verifica se a primeira letra é maiúscula ou minúscula
            if (Character.isUpperCase(palavra.charAt(0))) {
                maiusculas.adiciona(palavra);
            } else if (Character.isLowerCase(palavra.charAt(0))) {
                minusculas.adiciona(palavra);
            }
        }

        scanner.close();

        // Imprime as palavras que começam com letra maiúscula
        System.out.print("Palavras que começam com maiúscula: ");
        for (int i = 0; i < maiusculas.comprimento(); i++) {
            System.out.print(maiusculas.obtem(i) + " ");
        }
        System.out.println();

        // Imprime as palavras que começam com letra minúscula
        System.out.print("Palavras que começam com minúscula: ");
        for (int i = 0; i < minusculas.comprimento(); i++) {
            System.out.print(minusculas.obtem(i) + " ");
        }
        System.out.println();
    }
}
