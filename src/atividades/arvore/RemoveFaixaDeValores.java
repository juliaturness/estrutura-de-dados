package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

/**
 * Classe de utilidade para remover todos os valores dentro de uma faixa especificada
 * de uma árvore APB.
 */
public class RemoveFaixaDeValores<T extends Comparable<T>> {

    /**
     * Remove todos os valores de uma árvore APB que estão dentro de uma faixa especificada (inclusive).
     *
     * @param arvore A árvore APB da qual remover os valores.
     * @param de O limite inferior da faixa (inclusive).
     * @param ate O limite superior da faixa (inclusive).
     */
    public void removeFaixa(APB<T> arvore, T de, T ate) {
        if (arvore == null || arvore.esta_vazia()) {
            return;
        }

        // Obtém todos os elementos na faixa primeiro para evitar problemas de modificação concorrente
        // se estivéssemos iterando e removendo diretamente.
        // Usa o método 'faixa' existente da APB.
        ListaSequencial<T> valoresParaRemover = arvore.faixa(de, ate);

        for (int i = 0; i < valoresParaRemover.comprimento(); i++) {
            arvore.remove(valoresParaRemover.obtem(i));
        }
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
        arvore.adiciona(10);
        arvore.adiciona(25);
        arvore.adiciona(35);
        arvore.adiciona(45);
        arvore.adiciona(55);
        arvore.adiciona(65);
        arvore.adiciona(75);
        arvore.adiciona(85);

        RemoveFaixaDeValores<Integer> removedor = new RemoveFaixaDeValores<>();

        System.out.println("Árvore antes da remoção: " + arvore.emOrdem()); // [10, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85]

        // Remove valores de 30 a 60 (inclusive)
        removedor.removeFaixa(arvore, 30, 60);

        System.out.println("Árvore depois da remoção (30-60): " + arvore.emOrdem());
        // Esperado: [10, 20, 25, 65, 70, 75, 80, 85] (30, 35, 40, 45, 50, 55, 60 removidos)

        System.out.println("\n--- Teste 2: Remover faixa que inclui a raiz ---");
        APB<Integer> arvore2 = new APB<>();
        arvore2.adiciona(50);
        arvore2.adiciona(30);
        arvore2.adiciona(70);
        arvore2.adiciona(40);
        arvore2.adiciona(60);
        System.out.println("Árvore 2 antes: " + arvore2.emOrdem());
        removedor.removeFaixa(arvore2, 45, 75); // Remove 50, 60, 70
        System.out.println("Árvore 2 depois (45-75): " + arvore2.emOrdem()); // Esperado: [30, 40]
    }
}
