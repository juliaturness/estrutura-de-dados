package atividades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import esd.ListaSequencial;

public class ConsultaMatricula {

    static class Estudante {
        int matricula;
        String nome;

        public Estudante(int matricula, String nome) {
            this.matricula = matricula;
            this.nome = nome;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, informe o nome do arquivo como argumento.");
            return;
        }

        String nomeArquivo = args[0];
        ListaSequencial<Estudante> listaEstudantes = new ListaSequencial<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",", 2);
                if (partes.length == 2) {
                    try {
                        int matricula = Integer.parseInt(partes[0].trim());
                        String nome = partes[1].trim();
                        listaEstudantes.adiciona(new Estudante(matricula, nome));
                    } catch (NumberFormatException e) {
                        System.err.println("Matrícula inválida na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Loop de consulta
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Digite matricula> ");
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                break;
            }

            try {
                int matriculaProcurada = Integer.parseInt(entrada);
                boolean encontrado = false;

                // Procura na lista
                for (int i = 0; i < listaEstudantes.comprimento(); i++) {
                    Estudante e = listaEstudantes.obtem(i);
                    if (e.matricula == matriculaProcurada) {
                        System.out.println("Estudante: " + e.nome);
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Matricula " + entrada +" desconhecida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Digite apenas números.");
            }
        }

        scanner.close();
    }
}