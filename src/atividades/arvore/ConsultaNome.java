package atividades.arvore;
import esd.APB;

import java.io.*;
import java.util.Scanner;

public class ConsultaNome {

    public static void main(String[] args) {
        // Criar árvore binária de pesquisa (APB) para consultar nomes
        APB<String> arvore = new APB<>();

        // Carregar nomes do arquivo para a árvore
        carregarNomesDoArquivo("nomes.txt", arvore);

        // Loop de consulta
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Consultar> ");
            String nomeConsultado = scanner.nextLine().trim();

            // Se o usuário pressionar ENTER, termina o programa
            if (nomeConsultado.isEmpty()) {
                break;
            }

            // Consulta o nome na árvore
            if (arvore.procura(nomeConsultado) != null) {
            }
        }

        scanner.close();
        System.out.println("Programa terminado.");
    }

    // Método para carregar nomes do arquivo e inserir na árvore
    public static void carregarNomesDoArquivo(String nomeArquivo, APB<String> arvore) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                arvore.adiciona(linha.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
