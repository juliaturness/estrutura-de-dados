package atividades;

import esd.Pilha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BalanceadorDeP {

    // classe auxiliar para guardar posição da linha e coluna
    static class Posicao {
        int linha;
        int coluna;

        public Posicao(int linha, int coluna) {
           this.linha = linha;
           this.coluna = coluna;
        }
    }

    public static void main(String[] args) throws IOException {

        //Define o argumento como caminho
        String CaminhoArquivo = args[0];

        // para ler cada linha do arquivo
        BufferedReader br = new BufferedReader(new FileReader(CaminhoArquivo));
        String linha;
        int numLinha = 1;
        Pilha<Posicao> pilha = new Pilha<>();

        //enquanto estiver tendo linha para ler
        while ((linha = br.readLine()) != null) {
            // e enquanto o indice não for maior que linha.lenght, ou seja, enquanto houver linha
            for (int i = 0; i < linha.length(); i++) {
                // atribui o indice para o caracter
                char c = linha.charAt(i);
                int coluna = i + 1;
                // empilha para verificar depois
                if (c == '(') {
                    pilha.empilha(new Posicao(numLinha, coluna));
                } else if (c == ')') {
                    // verifica se a pilha ta vazia, se estiver é pq há um parentese fechado sem abertura
                    if (pilha.estaVazia()) {
                        System.out.println("Parêntese fechado na linha " + numLinha + ", coluna " + coluna);
                        return;
                    }
                    // se houver um '(' e um ')' é pq ta balanceado
                    pilha.desempilha();
                }
            }
            numLinha++;
        }
        // Se a pilha não estiver vazia é pq há um parentese aberto sem fechamento
        if (!pilha.estaVazia()) {
            Posicao restante = pilha.desempilha();
            System.out.println("Parentese aberto na linha " + restante.linha + ", coluna " + restante.coluna);
        } else {
            System.out.println("Tudo Balanceado!!");
        }
    }
}
