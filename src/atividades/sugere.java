package atividades;

import esd.ListaSimples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class sugere {
    public static void main(String[] args) {
        String arquivo = args[0];
        try {
            sugestão(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void sugestão(String arquivo) throws IOException {
        ListaSimples<String> palavras = new ListaSimples<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                palavras.adiciona(linha.trim());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // 2. Loop de interação com o usuário
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) break;

            boolean encontrou = false;
            for (int i = 0; i < palavras.comprimento(); i++) {
                String palavra = palavras.obtem(i);
                if (palavra.startsWith(entrada)) {
                    System.out.println(palavra);
                    encontrou = true;
                }
            }

            if (!encontrou) {
                System.out.println("Nenhuma sugestão encontrada.");
            }
        }

        scanner.close();
    }
}
