package atividades.arvore;

import java.io.*;
import java.util.*;

public class ReordenaArquivo {

    // Método estático que reordena as palavras no arquivo
    public static void reordena(String nome_arquivo) {
        try {
            // Passo 1: Ler as palavras do arquivo
            List<String> palavras = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(nome_arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    palavras.add(linha.trim());
                }
            }

            // Passo 2: Ordenar as palavras (Isso é necessário para construir a árvore balanceada)
            Collections.sort(palavras);

            // Passo 3: Construir a árvore binária balanceada a partir das palavras ordenadas
            Nodo raiz = construirArvoreBalanceada(palavras, 0, palavras.size() - 1);

            // Passo 4: Realizar um percurso em ordem (in-order) para obter as palavras ordenadas
            List<String> palavrasOrdenadas = new ArrayList<>();
            percorrerEmOrdem(raiz, palavrasOrdenadas);

            // Passo 5: Reescrever o arquivo com as palavras ordenadas
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nome_arquivo))) {
                for (String palavra : palavrasOrdenadas) {
                    writer.write(palavra);
                    writer.newLine();
                }
                System.out.println("Arquivo reordenado com sucesso.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para construir a árvore binária balanceada
    private static Nodo construirArvoreBalanceada(List<String> palavras, int inicio, int fim) {
        if (inicio > fim) {
            return null;
        }

        // Encontrar o elemento médio
        int meio = (inicio + fim) / 2;
        Nodo nodo = new Nodo(palavras.get(meio));

        // Construir recursivamente a subárvore esquerda e direita
        nodo.esq = construirArvoreBalanceada(palavras, inicio, meio - 1);
        nodo.dir = construirArvoreBalanceada(palavras, meio + 1, fim);

        return nodo;
    }

    // Método para percorrer a árvore em ordem (in-order)
    private static void percorrerEmOrdem(Nodo nodo, List<String> palavrasOrdenadas) {
        if (nodo == null) {
            return;
        }

        // Recursivamente visitar a subárvore esquerda
        percorrerEmOrdem(nodo.esq, palavrasOrdenadas);

        // Adicionar o valor do nodo à lista
        palavrasOrdenadas.add(nodo.valor);

        // Recursivamente visitar a subárvore direita
        percorrerEmOrdem(nodo.dir, palavrasOrdenadas);
    }

    // Classe para representar um nodo da árvore binária
    static class Nodo {
        String valor;
        Nodo esq, dir;

        Nodo(String valor) {
            this.valor = valor;
            this.esq = this.dir = null;
        }
    }

    // Método principal para testar
    public static void main(String[] args) {
        // Exemplo de uso
        reordena("dados.txt"); // Substitua "dados.txt" pelo nome do arquivo
    }
}
