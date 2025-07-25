package atividades.hash;

import esd.Conjunto; // Importa a classe Conjunto do pacote esd

/**
 * Classe de demonstração para o uso da implementação de Conjunto.
 * Esta classe atua como o ponto de entrada (com o método main) para testar
 * as funcionalidades do Conjunto.
 */
public class HashComConjunto {

    public static void main(String[] args) {
        // Cria uma instância do seu Conjunto
        Conjunto<String> meuConjunto = new Conjunto<>();

        System.out.println("Conjunto vazio? " + meuConjunto.esta_vazio()); // true
        System.out.println("Tamanho: " + meuConjunto.comprimento()); // 0

        meuConjunto.adiciona("Maçã");
        meuConjunto.adiciona("Banana");
        meuConjunto.adiciona("Laranja");
        meuConjunto.adiciona("Maçã"); // Adicionando duplicata, não deve alterar o tamanho

        System.out.println("\nElementos no conjunto: " + meuConjunto); // [Maçã, Banana, Laranja] (ordem pode variar)
        System.out.println("Tamanho: " + meuConjunto.comprimento()); // 3
        System.out.println("Contém 'Banana'? " + meuConjunto.contem("Banana")); // true
        System.out.println("Contém 'Uva'? " + meuConjunto.contem("Uva")); // false

        meuConjunto.remove("Banana");
        System.out.println("\nElementos após remover 'Banana': " + meuConjunto);
        System.out.println("Tamanho: " + meuConjunto.comprimento()); // 2
        System.out.println("Contém 'Banana'? " + meuConjunto.contem("Banana")); // false

        meuConjunto.adiciona("Pera");
        meuConjunto.adiciona("Abacaxi");
        System.out.println("\nElementos após adicionar Pera e Abacaxi: " + meuConjunto);
        System.out.println("Tamanho: " + meuConjunto.comprimento());

        meuConjunto.limpa();
        System.out.println("\nElementos após limpar: " + meuConjunto); // []
        System.out.println("Conjunto vazio? " + meuConjunto.esta_vazio()); // true
        System.out.println("Tamanho: " + meuConjunto.comprimento()); // 0
    }
}
