package atividades.hash;

import esd.ListaSequencial;
import esd.TabHash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SugestaoDePalavra {
    public static void main(String[] args) {

        // Criar uma tabela hash para armazenar prefixos e palavras
        TabHash<String, ListaSequencial<String>> tabelaHash = new TabHash<>();

        // Ler o arquivo de palavras
        try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim().toLowerCase(); // Remove espaços e converte para minúsculas

                // Para cada palavra, geramos todos os prefixos possíveis
                for (int i = 1; i <= linha.length(); i++) {
                    String prefixo = linha.substring(0, i); // Pega o prefixo da palavra

                    // Se o prefixo não existe na tabela, cria uma nova lista de palavras
                    ListaSequencial<String> palavras = tabelaHash.obtem_ou_default(prefixo, new ListaSequencial<>());
                    palavras.adiciona(linha);  // Adiciona a palavra à lista do prefixo
                    tabelaHash.adiciona(prefixo, palavras);  // Armazena na tabela
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um prefixo de palavra (ou pressione ENTER para sair):");

        // Loop para interação com o usuário
        while (true) {
            System.out.print("> ");
            String prefixo = scanner.nextLine().trim().toLowerCase(); // Lê o prefixo digitado

            if (prefixo.isEmpty()) {
                // Se o usuário pressionar ENTER sem digitar nada, o programa termina
                System.out.println("Programa encerrado.");
                break;
            }

            // Busca as palavras associadas ao prefixo
            ListaSequencial<String> palavrasSugeridas = tabelaHash.obtem_ou_default(prefixo, new ListaSequencial<>());

            if (palavrasSugeridas.comprimento() > 0) {
                // Ordena as palavras diretamente usando o método ordena da Lista
                palavrasSugeridas.ordena();

                System.out.println("Sugestões de palavras:");
                // Imprimir as palavras ordenadas
                for (int i = 0; i < palavrasSugeridas.comprimento(); i++) {
                    System.out.println(palavrasSugeridas.obtem(i));
                }
            } else {
                System.out.println("Nenhuma sugestão encontrada.");
            }
        }
    }
}
