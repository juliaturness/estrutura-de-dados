package atividades.arvore;

import esd.APB;

/**
 * Classe de utilidade para demonstrar o cálculo do nível de profundidade máxima (altura)
 * de uma árvore APB.
 * Utiliza o método 'altura()' já existente na classe APB.
 */
public class nivelDeProfundidadeMaxima<T extends Comparable<T>> {

    /**
     * Demonstra o cálculo do nível de profundidade máxima (altura) de uma árvore APB.
     *
     * @param arvore A árvore APB a ser analisada.
     */
    public void demonstraProfundidadeMaxima(APB<T> arvore) {
        if (arvore == null || arvore.esta_vazia()) {
            System.out.println("A árvore está vazia. A profundidade máxima é 0.");
            return;
        }

        int altura = arvore.altura();
        System.out.println("A profundidade máxima (altura) da árvore é: " + altura);
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
        arvore.adiciona(10); // Nível 4
        arvore.adiciona(5);  // Nível 5

        nivelDeProfundidadeMaxima<Integer> demo = new nivelDeProfundidadeMaxima<>();
        System.out.println("Árvore (em ordem): " + arvore.emOrdem());
        demo.demonstraProfundidadeMaxima(arvore); // Esperado: 5

        System.out.println("\n--- Teste com árvore vazia ---");
        APB<String> arvoreVazia = new APB<>();
        nivelDeProfundidadeMaxima<String> demoVazia = new nivelDeProfundidadeMaxima<>();
        demoVazia.demonstraProfundidadeMaxima(arvoreVazia); // Esperado: "A árvore está vazia..."

        System.out.println("\n--- Teste com árvore de um nó ---");
        APB<Double> arvoreUmNo = new APB<>();
        arvoreUmNo.adiciona(3.14);
        nivelDeProfundidadeMaxima<Double> demoUmNo = new nivelDeProfundidadeMaxima<>();
        System.out.println("Árvore (em ordem): " + arvoreUmNo.emOrdem());
        demoUmNo.demonstraProfundidadeMaxima(arvoreUmNo); // Esperado: 1
    }
}
