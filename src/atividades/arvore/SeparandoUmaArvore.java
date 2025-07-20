package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

/**
 * Classe auxiliar para retornar um par de objetos.
 * Usada para o método de separação de árvores.
 * @param <A> Tipo do primeiro elemento.
 * @param <B> Tipo do segundo elemento.
 */
class Pair<A, B> {
    public final A first;
    public final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}

/**
 * Classe de utilidade para separar uma árvore APB em duas novas árvores
 * com base em um valor divisor.
 * Uma árvore conterá valores menores ou iguais ao divisor, e a outra, valores maiores.
 */
public class SeparandoUmaArvore<T extends Comparable<T>> {

    /**
     * Separa uma árvore APB em duas novas árvores com base em um valor divisor.
     *
     * @param arvoreOriginal A árvore APB a ser separada.
     * @param valorDivisor O valor que define o ponto de separação.
     * @return Um objeto Pair contendo duas novas árvores APB:
     *         - first: Contém todos os valores da arvoreOriginal menores ou iguais a valorDivisor.
     *         - second: Contém todos os valores da arvoreOriginal maiores que valorDivisor.
     */
    public Pair<APB<T>, APB<T>> separaArvore(APB<T> arvoreOriginal, T valorDivisor) {
        APB<T> menoresOuIguais = new APB<>();
        APB<T> maiores = new APB<>();

        if (arvoreOriginal == null || arvoreOriginal.esta_vazia()) {
            return new Pair<>(menoresOuIguais, maiores);
        }

        // Percorre a árvore original e distribui os elementos
        // Uma travessia em ordem garante que todos os elementos sejam visitados.
        ListaSequencial<T> todosOsElementos = arvoreOriginal.emOrdem();
        for (int i = 0; i < todosOsElementos.comprimento(); i++) {
            T elemento = todosOsElementos.obtem(i);
            if (elemento.compareTo(valorDivisor) <= 0) {
                menoresOuIguais.adiciona(elemento);
            } else {
                maiores.adiciona(elemento);
            }
        }

        return new Pair<>(menoresOuIguais, maiores);
    }

    // Exemplo de uso
    public static void main(String[] args) {
        APB<Integer> arvore = new APB<>();
        arvore.adiciona(50);
        arvore.adiciona(30);
        arvore.adiciona(70);
        arvore.adiciona(20);
        arvore.adiciona(40);
        arvore.adiciona(60);
        arvore.adiciona(80);
        arvore.adiciona(35);
        arvore.adiciona(45);
        arvore.adiciona(75);

        SeparandoUmaArvore<Integer> separador = new SeparandoUmaArvore<>();

        System.out.println("Árvore Original (em ordem): " + arvore.emOrdem()); // [20, 30, 35, 40, 45, 50, 60, 70, 75, 80]

        Integer divisor = 50;
        Pair<APB<Integer>, APB<Integer>> resultado = separador.separaArvore(arvore, divisor);

        APB<Integer> arvoreMenoresOuIguais = resultado.first;
        APB<Integer> arvoreMaiores = resultado.second;

        System.out.println("\nÁrvore com valores <= " + divisor + " (em ordem): " + arvoreMenoresOuIguais.emOrdem());
        // Esperado: [20, 30, 35, 40, 45, 50]
        System.out.println("Árvore com valores > " + divisor + " (em ordem): " + arvoreMaiores.emOrdem());
        // Esperado: [60, 70, 75, 80]

        System.out.println("\n--- Teste com divisor no limite inferior ---");
        divisor = 20;
        resultado = separador.separaArvore(arvore, divisor);
        System.out.println("Árvore com valores <= " + divisor + " (em ordem): " + resultado.first.emOrdem()); // Esperado: [20]
        System.out.println("Árvore com valores > " + divisor + " (em ordem): " + resultado.second.emOrdem()); // Esperado: [30, 35, 40, 45, 50, 60, 70, 75, 80]

        System.out.println("\n--- Teste com divisor no limite superior ---");
        divisor = 80;
        resultado = separador.separaArvore(arvore, divisor);
        System.out.println("Árvore com valores <= " + divisor + " (em ordem): " + resultado.first.emOrdem()); // Esperado: [20, 30, 35, 40, 45, 50, 60, 70, 75, 80]
        System.out.println("Árvore com valores > " + divisor + " (em ordem): " + resultado.second.emOrdem()); // Esperado: []
    }
}
