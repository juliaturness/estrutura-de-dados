package atividades.arvore;

import esd.APB;

/**
 * Classe de utilidade para encontrar o antecessor de um valor em uma árvore APB.
 * O antecessor é o maior valor que é menor que o valor dado.
 */
public class Antecessor<T extends Comparable<T>> {

    /**
     * Encontra o antecessor (maior valor menor que) de um dado valor na árvore APB.
     * Utiliza o método `menor_que` da classe APB.
     *
     * @param arvore A árvore APB onde procurar.
     * @param val O valor para o qual encontrar o antecessor.
     * @return O valor do antecessor, ou null se não houver antecessor.
     */
    public T encontraAntecessor(APB<T> arvore, T val) {
        if (arvore == null || arvore.esta_vazia()) {
            return null;
        }
        // O método menor_que da APB já faz exatamente o que precisamos para o antecessor
        return arvore.menor_que(val);
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
        arvore.adiciona(35); // Adiciona 35 para testar antecessor de 40

        Antecessor<Integer> antecessorFinder = new Antecessor<>();

        System.out.println("Árvore (em ordem): " + arvore.emOrdem());

        System.out.println("Antecessor de 50: " + antecessorFinder.encontraAntecessor(arvore, 50)); // Esperado: 45 (se 45 existisse), ou 40
        System.out.println("Antecessor de 30: " + antecessorFinder.encontraAntecessor(arvore, 30)); // Esperado: 20
        System.out.println("Antecessor de 70: " + antecessorFinder.encontraAntecessor(arvore, 70)); // Esperado: 60
        System.out.println("Antecessor de 20: " + antecessorFinder.encontraAntecessor(arvore, 20)); // Esperado: null
        System.out.println("Antecessor de 40: " + antecessorFinder.encontraAntecessor(arvore, 40)); // Esperado: 35
        System.out.println("Antecessor de 65 (não existe): " + antecessorFinder.encontraAntecessor(arvore, 65)); // Esperado: 60
        System.out.println("Antecessor de 10 (não existe): " + antecessorFinder.encontraAntecessor(arvore, 10)); // Esperado: null
    }
}
