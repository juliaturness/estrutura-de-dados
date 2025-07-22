package atividades.hash;

import esd.TabHash;
import esd.ListaSequencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContadorDePalavras {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Arquivo invalido");
            return;
        }

        String nomeArquivo = args[0];
        File arquivo = new File(nomeArquivo);
        Scanner scanner;

        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo invalido");
            return;
        }

        TabHash<String, Integer> tabela = new TabHash<>();

        while (scanner.hasNext()) {
            String palavra = scanner.next().toLowerCase();

            int contador = tabela.obtem_ou_default(palavra, 0);
            tabela.adiciona(palavra, contador + 1);
        }

        scanner.close();

        ListaSequencial<String> chaves = tabela.chaves();
        for (int i = 0; i < chaves.comprimento(); i++) {
            String palavra = chaves.obtem(i);
            int ocorrencias = tabela.obtem(palavra);
            System.out.println(palavra + " " + ocorrencias);
        }
    }
}
