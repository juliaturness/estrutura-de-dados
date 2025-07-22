package atividades.hash;


import esd.TabHash;
import esd.ListaSequencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RemoverPalavrasRepetidas {
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

        // Tabela Hash onde armazenamos as palavras
        TabHash<String, Boolean> tabela = new TabHash<>();

        // Lê as palavras do arquivo
        while (scanner.hasNext()) {
            String palavra = scanner.next().toLowerCase();

            // Verifica se a palavra já foi encontrada antes
            if (tabela.contem(palavra)) {
                // Se a palavra já existir, a removemos da tabela
                tabela.remove(palavra);
                System.out.println("Removendo palavra repetida: " + palavra);
            } else {
                // Se for a primeira vez que encontramos a palavra, a adicionamos
                tabela.adiciona(palavra, true);
            }
        }

        scanner.close();

        // Ao final, vamos imprimir as palavras que ficaram na tabela (não repetidas)
        ListaSequencial<String> chaves = tabela.chaves();
        System.out.println("\nPalavras não repetidas:");
        for (int i = 0; i < chaves.comprimento(); i++) {
            String palavra = chaves.obtem(i);
            System.out.println(palavra);
        }
    }
}
