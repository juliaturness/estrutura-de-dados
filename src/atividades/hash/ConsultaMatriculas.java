package atividades.hash;

import esd.TabHash;

import java.io.*;
import java.util.Scanner;

public class ConsultaMatriculas {
    public static void main(String[] args) {
        // Verifica se o arquivo foi fornecido
        if (args.length != 1) {
            System.out.println("Uso: java MatriculaSistema <caminho_do_arquivo>");
            return;
        }

        String arquivo = args[0];
        TabHash<Integer, String> tabelaHash = new TabHash<>();

        // Lê o arquivo e insere os dados na tabela hash
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Lê cada linha, separando número e nome
                String[] partes = linha.split(",");
                int matricula = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();

                // Insere na tabela hash
                tabelaHash.adiciona(matricula, nome);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Interage com o usuário para procurar matrículas
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Digite matricula> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                // Se o usuário apertar ENTER sem digitar nada, encerra o programa
                System.out.println("Programa encerrado.");
                break;
            }

            try {
                int matriculaDigitada = Integer.parseInt(input);
                String nome = tabelaHash.obtem(matriculaDigitada);

                if (nome != null) {
                    // Matrícula encontrada
                    System.out.println("Estudante: " + nome);
                } else {
                    // Matrícula não encontrada
                    System.out.println("Matricula " + matriculaDigitada + " desconhecida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número de matrícula válido.");
            }
        }

        scanner.close();
    }
}
