package atividades.hash;

import esd.TabHash;
import esd.ListaSequencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComparaConjuntoPalavras {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("FALSO"); // não foi possível comparar
            return;
        }

        String arquivo1 = args[0];
        String arquivo2 = args[1];

        TabHash<String, Boolean> conjunto1 = new TabHash<>();
        TabHash<String, Boolean> conjunto2 = new TabHash<>();

        if (!carregaPalavras(arquivo1, conjunto1) || !carregaPalavras(arquivo2, conjunto2)) {
            System.out.println("FALSO");
            return;
        }

        // Verifica se os conjuntos têm o mesmo tamanho
        if (conjunto1.comprimento() != conjunto2.comprimento()) {
            System.out.println("FALSO");
            return;
        }

        // Verifica se todas as palavras do primeiro conjunto estão no segundo
        ListaSequencial<String> chaves1 = conjunto1.chaves();
        for (int i = 0; i < chaves1.comprimento(); i++) {
            String palavra = chaves1.obtem(i);
            if (!conjunto2.contem(palavra)) {
                System.out.println("FALSO");
                return;
            }
        }

        System.out.println("VERDADEIRO");
    }

    // Carrega palavras únicas de um arquivo para uma tabela hash
    private static boolean carregaPalavras(String nomeArquivo, TabHash<String, Boolean> tabela) {
        try {
            Scanner scanner = new Scanner(new File(nomeArquivo));
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (!linha.isEmpty()) {
                    tabela.adiciona(linha, true); // valor booleano é irrelevante
                }
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
