package atividades.arvore;

import esd.APB;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe de utilidade para criar árvores APB intencionalmente desbalanceadas.
 * Isso é útil para testar o desempenho de operações em árvores degeneradas.
 */
public class Desbalanceia<T extends Comparable<T>> {

    /**
     * Cria uma árvore APB intencionalmente desbalanceada inserindo elementos em ordem crescente.
     * Isso resulta em uma árvore que se assemelha a uma lista encadeada (inclinada para a direita).
     *
     * @param elementos Uma lista de elementos a serem inseridos.
     * @return Uma nova árvore APB desbalanceada.
     */
    public APB<T> criarArvoreDesbalanceadaCrescente(List<T> elementos) {
        APB<T> arvore = new APB<>();
        // Ordena os elementos para garantir uma árvore inclinada (para a direita)
        List<T> sortedElements = new ArrayList<>(elementos);
        Collections.sort(sortedElements); // Requer que T seja Comparable

        for (T element : sortedElements) {
            arvore.adiciona(element);
        }
        return arvore;
    }

    /**
     * Cria uma árvore APB intencionalmente desbalanceada inserindo elementos em ordem decrescente.
     * Isso resulta em uma árvore que se assemelha a uma lista encadeada (inclinada para a esquerda).
     *
     * @param elementos Uma lista de elementos a serem inseridos.
     * @return Uma nova árvore APB desbalanceada.
     */
    public APB<T> criarArvoreDesbalanceadaDecrescente(List<T> elementos) {
        APB<T> arvore = new APB<>();
        // Ordena os elementos em ordem inversa para garantir uma árvore inclinada (para a esquerda)
        List<T> sortedElements = new ArrayList<>(elementos);
        Collections.sort(sortedElements, Collections.reverseOrder()); // Requer que T seja Comparable

        for (T element : sortedElements) {
            arvore.adiciona(element);
        }
        return arvore;
    }

    // Exemplo de uso
    public static void main(String[] args) {
        Desbalanceia<Integer> desbalanceador = new Desbalanceia<>();

        List<Integer> dados = List.of(10, 20, 30, 40, 50, 60, 70);

        System.out.println("--- Árvore desbalanceada (crescente) ---");
        APB<Integer> arvoreCrescente = desbalanceador.criarArvoreDesbalanceadaCrescente(dados);
        System.out.println("Elementos (em ordem): " + arvoreCrescente.emOrdem());
        System.out.println("Altura da árvore: " + arvoreCrescente.altura()); // Esperado: 7 (para 7 nós)

        System.out.println("\n--- Árvore desbalanceada (decrescente) ---");
        APB<Integer> arvoreDecrescente = desbalanceador.criarArvoreDesbalanceadaDecrescente(dados);
        System.out.println("Elementos (em ordem): " + arvoreDecrescente.emOrdem());
        System.out.println("Altura da árvore: " + arvoreDecrescente.altura()); // Esperado: 7 (para 7 nós)

        System.out.println("\n--- Árvore balanceada (para comparação) ---");
        APB<Integer> arvoreBalanceada = new APB<>();
        arvoreBalanceada.adiciona(40);
        arvoreBalanceada.adiciona(20);
        arvoreBalanceada.adiciona(60);
        arvoreBalanceada.adiciona(10);
        arvoreBalanceada.adiciona(30);
        arvoreBalanceada.adiciona(50);
        arvoreBalanceada.adiciona(70);
        System.out.println("Elementos (em ordem): " + arvoreBalanceada.emOrdem());
        System.out.println("Altura da árvore: " + arvoreBalanceada.altura()); // Esperado: 3 (para 7 nós)
    }
}
