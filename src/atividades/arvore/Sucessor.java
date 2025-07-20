package atividades.arvore;

import esd.APB;

/**
 * Classe de utilidade para encontrar o sucessor de um valor em uma árvore APB.
 * O sucessor é o menor valor que é maior que o valor dado.
 */
public class Sucessor<T extends Comparable<T>> {

    /**
     * Encontra o sucessor (menor valor maior que) de um dado valor na árvore APB.
     * Utiliza o método `maior_que` da classe APB.
     *
     * @param arvore A árvore APB onde procurar.
     * @param val O valor para o qual encontrar o sucessor.
     * @return O valor do sucessor, ou null se não houver sucessor.
     */
    public T encontraSucessor(APB<T> arvore, T val) {
        if (arvore == null || arvore.esta_vazia()) {
            return null;
        }
        // O método maior_que da APB já faz exatamente o que precisamos para o sucessor
        return arvore.maior_que(val);
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
        arvore.adiciona(35); // Para testar sucessor de 30
        arvore.adiciona(45); // Para testar sucessor de 40
        arvore.adiciona(75); // Para testar sucessor de 70

        Sucessor<Integer> sucessorFinder = new Sucessor<>();

        System.out.println("Árvore (em ordem): " + arvore.emOrdem());

        System.out.println("Sucessor de 50: " + sucessorFinder.encontraSucessor(arvore, 50)); // Esperado: 60
        System.out.println("Sucessor de 30: " + sucessorFinder.encontraSucessor(arvore, 30)); // Esperado: 35
        System.out.println("Sucessor de 70: " + sucessorFinder.encontraSucessor(arvore, 70)); // Esperado: 75
        System.out.println("Sucessor de 20: " + sucessorFinder.encontraSucessor(arvore, 20)); // Esperado: 30
        System.out.println("Sucessor de 40: " + sucessorFinder.encontraSucessor(arvore, 40)); // Esperado: 45
        System.out.println("Sucessor de 80: " + sucessorFinder.encontraSucessor(arvore, 80)); // Esperado: null (maior valor)
        System.out.println("Sucessor de 10 (não existe): " + sucessorFinder.encontraSucessor(arvore, 10)); // Esperado: 20 (menor valor maior que 10)
        System.out.println("Sucessor de 90 (não existe): " + sucessorFinder.encontraSucessor(arvore, 90)); // Esperado: null
    }
}
