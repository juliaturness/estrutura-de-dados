package atividades.hash;

import esd.TabHash;
import esd.ListaSequencial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AgrupaPalavrasPorTamanho {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Arquivo invalido");
            return;
        }

        String nomeArquivo = args[0];

        TabHash<Integer, ListaSequencial<String>> tabela = new TabHash<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String palavra = linha.trim();
                if (palavra.isEmpty()) continue;

                int tamanho = palavra.length();

                ListaSequencial<String> lista = tabela.obtem_ou_default(tamanho, null);
                if (lista == null) {
                    lista = new ListaSequencial<>();
                    tabela.adiciona(tamanho, lista);
                }
                lista.adiciona(palavra);
            }

        } catch (IOException e) {
            System.out.println("Arquivo invalido");
            return;
        }

        // Obtem os tamanhos (chaves) e ordena
        ListaSequencial<Integer> chaves = tabela.chaves();
        Integer[] tamanhos = new Integer[chaves.comprimento()];
        for (int i = 0; i < tamanhos.length; i++) {
            tamanhos[i] = chaves.obtem(i);
        }

        // Ordenar tamanhos (bubblesort ou selection sort manual, se necessário)
        for (int i = 0; i < tamanhos.length - 1; i++) {
            for (int j = i + 1; j < tamanhos.length; j++) {
                if (tamanhos[i] > tamanhos[j]) {
                    int tmp = tamanhos[i];
                    tamanhos[i] = tamanhos[j];
                    tamanhos[j] = tmp;
                }
            }
        }

        // Imprimir palavras por grupo de tamanho
        for (int i = 0; i < tamanhos.length; i++) {
            ListaSequencial<String> palavras = tabela.obtem(tamanhos[i]);
            for (int j = 0; j < palavras.comprimento(); j++) {
                System.out.print(palavras.obtem(j) + " ");
            }
            System.out.println();
        }
    }
}
