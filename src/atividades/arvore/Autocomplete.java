package atividades.arvore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Autocomplete {
    // Classe Nodo para a árvore binária de pesquisa
    static class Nodo {
        String valor;
        Nodo esq, dir;

        Nodo(String valor) {
            this.valor = valor;
            this.esq = this.dir = null;
        }
    }

    // Classe para a árvore binária de pesquisa
    static class ArvoreBinaria {
        Nodo raiz;

        public ArvoreBinaria() {
            raiz = null;
        }

        // Adiciona uma palavra na árvore
        public void adiciona(String palavra) {
            raiz = adicionaRecursivo(raiz, palavra);
        }

        private Nodo adicionaRecursivo(Nodo nodo, String palavra) {
            if (nodo == null) {
                return new Nodo(palavra);
            }

            if (palavra.compareTo(nodo.valor) < 0) {
                nodo.esq = adicionaRecursivo(nodo.esq, palavra);
            } else if (palavra.compareTo(nodo.valor) > 0) {
                nodo.dir = adicionaRecursivo(nodo.dir, palavra);
            }

            return nodo;
        }

        // Busca palavras que começam com a sequência fornecida
        public List<String> buscaPorPrefixo(String prefixo) {
            List<String> resultados = new ArrayList<>();
            buscaPorPrefixoRecursivo(raiz, prefixo, resultados);
            return resultados;
        }

        private void buscaPorPrefixoRecursivo(Nodo nodo, String prefixo, List<String> resultados) {
            if (nodo == null) {
                return;
            }

            // Se a palavra começa com o prefixo, adicione-a à lista de resultados
            if (nodo.valor.startsWith(prefixo)) {
                resultados.add(nodo.valor);
            }

            // Recursivamente procurar nas subárvores
            if (prefixo.compareTo(nodo.valor) < 0) {
                buscaPorPrefixoRecursivo(nodo.esq, prefixo, resultados);
            }

            if (prefixo.compareTo(nodo.valor) >= 0) {
                buscaPorPrefixoRecursivo(nodo.dir, prefixo, resultados);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();

        // Carregar palavras do arquivo dados.txt
        try (Scanner fileScanner = new Scanner(new File("dados.txt"))) {
            while (fileScanner.hasNext()) {
                String palavra = fileScanner.nextLine().trim().toLowerCase();
                arvore.adiciona(palavra);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            return;
        }

        // Loop para consulta de palavras
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                break; // Encerra o programa se o usuário pressionar ENTER sem digitar nada
            }

            // Buscar palavras que começam com o prefixo digitado
            List<String> resultados = arvore.buscaPorPrefixo(input);

            // Exibir resultados
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma palavra encontrada.");
            } else {
                resultados.sort(String::compareTo); // Ordena as palavras em ordem alfabética
                for (String palavra : resultados) {
                    System.out.println(palavra);
                }
            }
        }

        scanner.close();
    }
}
