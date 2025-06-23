package atividades;

import esd.Fila;  // Sua implementação da Fila
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumarioDePalavras {


    static class PalavraPosicoes {
        String palavra;
        Fila<Integer> posicoes;

        PalavraPosicoes(String palavra) {
            this.palavra = palavra;
            this.posicoes = new Fila<>();
        }
    }

    public static void main(String[] args) {
        String nomeArquivo = args[0];

        Fila<PalavraPosicoes> palavras = new Fila<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            int posicao = 1;

            while ((linha = br.readLine()) != null) {
                String[] palavrasDaLinha = linha.split("\\s+");

                for (String palavra : palavrasDaLinha) {
                    if (!palavra.isEmpty()) {
                        adicionarPalavra(palavras, palavra, posicao);
                    }
                    posicao++;
                }
            }

            while (!palavras.estaVazia()) {
                PalavraPosicoes pp = palavras.remove();
                System.out.print(pp.palavra + " ");
                while (!pp.posicoes.estaVazia()) {
                    System.out.print(pp.posicoes.remove() + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void adicionarPalavra(Fila<PalavraPosicoes> palavras, String palavra, int posicao) {
        Fila<PalavraPosicoes> filaTemp = new Fila<>();

        boolean encontrada = false;

        while (!palavras.estaVazia()) {
            PalavraPosicoes pp = palavras.remove();
            if (pp.palavra.equals(palavra)) {
                pp.posicoes.adiciona(posicao);  // adiciona a nova posição
                encontrada = true;
            }
            filaTemp.adiciona(pp);
        }

        // se a palavra não foi encontrada, criamos uma nova entrada
        if (!encontrada) {
            PalavraPosicoes novaPalavra = new PalavraPosicoes(palavra);
            novaPalavra.posicoes.adiciona(posicao);  // adiciona a posição
            palavras.adiciona(novaPalavra);
        }

        while (!filaTemp.estaVazia()) {
            palavras.adiciona(filaTemp.remove());
        }
    }
}
