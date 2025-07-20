package atividades.arvore;

import esd.APB;

/**
 * Classe de utilidade para demonstrar a obtenção do menor e maior valor em uma árvore APB.
 * Utiliza os métodos 'menor()' e 'maior()' já existentes na classe APB.
 */
public class MaiorValorMenorValor<T extends Comparable<T>> {

    /**
     * Demonstra a obtenção do menor e maior valor em uma árvore APB.
     *
     * @param arvore A árvore APB a ser analisada.
     */
    public void demonstraMinMax(APB<T> arvore) {
        if (arvore == null || arvore.esta_vazia()) {
            System.out.println("A árvore está vazia. Não há valores mínimo ou máximo.");
            return;
        }

        T menor = arvore.menor();
        T maior = arvore.maior();

        System.out.println("Menor valor na árvore: " + menor);
        System.out.println("Maior valor na árvore: " + maior);
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

        MaiorValorMenorValor<Integer> demo = new MaiorValorMenorValor<>();
        System.out.println("Árvore (em ordem): " + arvore.emOrdem());
        demo.demonstraMinMax(arvore); // Esperado: Menor: 20, Maior: 80

        System.out.println("\n--- Teste com árvore vazia ---");
        APB<String> arvoreVazia = new APB<>();
        MaiorValorMenorValor<String> demoVazia = new MaiorValorMenorValor<>();
        demoVazia.demonstraMinMax(arvoreVazia); // Esperado: "A árvore está vazia..."

        System.out.println("\n--- Teste com árvore de um nó ---");
        APB<Double> arvoreUmNo = new APB<>();
        arvoreUmNo.adiciona(3.14);
        MaiorValorMenorValor<Double> demoUmNo = new MaiorValorMenorValor<>();
        System.out.println("Árvore (em ordem): " + arvoreUmNo.emOrdem());
        demoUmNo.demonstraMinMax(arvoreUmNo); // Esperado: Menor: 3.14, Maior: 3.14
    }
}
