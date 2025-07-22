package atividades.arvore;


import esd.APB;
import esd.ListaSequencial;

import java.io.*;
import java.util.*;

public class UltimasPalavras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        try {
            // Criando a árvore binária de pesquisa
            APB<String> arvore = new APB<>();

            // Lendo o arquivo e inserindo as palavras na árvore
            BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Quebra a linha em palavras usando espaço em branco
                String[] palavras = linha.split("\\s+");
                for (String palavra : palavras) {
                    arvore.adiciona(palavra);
                }
            }

            // Obtendo as palavras ordenadas através da travessia em ordem
            ListaSequencial listaOrdenada = arvore.emOrdem();

            // Exibindo as últimas 5 palavras (ou menos se houver menos palavras)
            int total = listaOrdenada.comprimento();
            int inicio = Math.max(0, total - 5);  // Início da exibição das últimas 5 palavras

            for (int i = inicio; i < total; i++) {
                System.out.println(listaOrdenada.obtem(i));
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
