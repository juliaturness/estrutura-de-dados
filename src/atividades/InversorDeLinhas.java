package atividades;

import esd.Deque;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class InversorDeLinhas {
    public static void main(String[] args) throws IOException {

        String arquivo= "src/teste.txt";

        try {
            iverte(arquivo);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void iverte(String arquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            Deque<String> sequenciaAtual = new Deque<>();
            boolean atualMaiuscula = false;
            boolean primeiraLinha = true;
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (linha.isEmpty()) continue;

                boolean linhaMaiuscula = !linha.isEmpty() && Character.isUpperCase(linha.charAt(0));

                if (primeiraLinha) {
                    atualMaiuscula = linhaMaiuscula;
                    primeiraLinha = false;
                }

                if (linhaMaiuscula != atualMaiuscula) {
                    imprimeSequencia(sequenciaAtual, atualMaiuscula);
                    sequenciaAtual = new Deque<>();
                    atualMaiuscula = linhaMaiuscula;
                }

                if (atualMaiuscula) {
                    sequenciaAtual.adiciona(linha); // Mantém ordem original
                } else {
                    sequenciaAtual.insere(linha); // Inverte ordem
                }
            }

            // Imprime a última sequência
            if (!primeiraLinha) {
                imprimeSequencia(sequenciaAtual, atualMaiuscula);
            }
        } catch (IOException e) {
            System.out.println("arquivo invalido");
        }
    }

    private static void imprimeSequencia(Deque<String> sequencia, boolean maiuscula) {
        while (!sequencia.esta_vazia()) {
            System.out.println(maiuscula ? sequencia.extrai_inicio() : sequencia.extrai_inicio());
        }
    }
}