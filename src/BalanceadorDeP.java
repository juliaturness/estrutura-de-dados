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

        BufferedReader br = new BufferedReader(new FileReader(CaminhoArquivo));
        String linha;
        int numLinha = 1;
        Pilha<Posicao> pilha = new Pilha<>();
        while ((linha = br.readLine()) != null) {
            for (int i = 0; i < linha.length(); i++) {
                char c = linha.charAt(i);

                int coluna = i + 1;
                if (c == '(') {
                    pilha.empilha(new Posicao(numLinha, coluna));
                } else if (c == ')') {
                    if (pilha.estaVazia()) {
                        System.out.println("Parêntese fechado na linha " + numLinha + ", coluna " + coluna);
                        return;
                    }
                    pilha.desempilha();
                }
            }
            numLinha++;
        }
        if (!pilha.estaVazia()) {
            Posicao restante = pilha.desempilha();
            System.out.println("Parentese aberto na linha " + restante.linha + ", coluna " + restante.coluna);
        } else {
            System.out.println("Tudo Balanceado!!");
        }
    }
}
