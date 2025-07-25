package atividades.hash;

import esd.Conjunto;

public class HashComConjunto {
    public class Main {
        public static void main(String[] args) {
            Conjunto<Integer> conjunto1 = new Conjunto<>();
            Conjunto<Integer> conjunto2 = new Conjunto<>();

            // Adicionando elementos ao conjunto1
            conjunto1.adiciona(1);
            conjunto1.adiciona(2);
            conjunto1.adiciona(3);

            // Adicionando elementos ao conjunto2
            conjunto2.adiciona(3);
            conjunto2.adiciona(4);
            conjunto2.adiciona(5);

            // União de conjunto1 e conjunto2
            Conjunto<Integer> uniao = conjunto1.uniao(conjunto2);
            System.out.println("União: " + uniao);

            // Interseção de conjunto1 e conjunto2
            Conjunto<Integer> intersecao = conjunto1.intersecao(conjunto2);
            System.out.println("Interseção: " + intersecao);

            // Diferença de conjunto1 e conjunto2
            Conjunto<Integer> diferenca = conjunto1.diferenca(conjunto2);
            System.out.println("Diferença: " + diferenca);

            // Verifica se conjunto1 é subconjunto de conjunto2
            System.out.println("É subconjunto? " + conjunto1.ehSubconjunto(conjunto2));
        }
    }

}
