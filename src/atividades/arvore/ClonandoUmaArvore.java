package atividades.arvore;

import esd.APB;
import esd.ListaSequencial; // Necessário para usar emOrdem

/**
 * Classe de utilidade para criar uma cópia de uma árvore APB.
 *
 * NOTA: Devido à classe `esd.APB.NodoAPB` ser privada, esta implementação
 * clona apenas os *elementos* da árvore original, adicionando-os a uma nova árvore.
 * Isso significa que a *estrutura* exata (balanceamento, profundidade dos nós)
 * da árvore original pode não ser preservada se a árvore original for desbalanceada.
 * A nova árvore será construída como se os elementos fossem adicionados um a um.
 */
public class ClonandoUmaArvore<T extends Comparable<T>> {

    /**
     * Cria uma cópia de uma árvore APB adicionando todos os elementos da original
     * a uma nova árvore. A estrutura exata pode não ser preservada.
     *
     * @param arvoreOriginal A árvore APB a ser clonada.
     * @return Uma nova árvore APB contendo os mesmos elementos da original.
     */
    public APB<T> clonarArvore(APB<T> arvoreOriginal) {
        if (arvoreOriginal == null) {
            return null;
        }
        APB<T> novaArvore = new APB<>();
        if (arvoreOriginal.esta_vazia()) {
            return novaArvore;
        }

        // Obtém todos os elementos da árvore original em ordem
        ListaSequencial<T> elementos = arvoreOriginal.emOrdem();

        // Adiciona cada elemento à nova árvore
        for (int i = 0; i < elementos.comprimento(); i++) {
            novaArvore.adiciona(elementos.obtem(i));
        }
        return novaArvore;
    }

    // Exemplo de uso
    public static void main(String[] args) {
        APB<Integer> arvoreOriginal = new APB<>();
        arvoreOriginal.adiciona(50);
        arvoreOriginal.adiciona(30);
        arvoreOriginal.adiciona(70);
        arvoreOriginal.adiciona(20);
        arvoreOriginal.adiciona(40);
        arvoreOriginal.adiciona(10); // Adiciona para criar um desbalanceamento

        ClonandoUmaArvore<Integer> clonador = new ClonandoUmaArvore<>();
        APB<Integer> arvoreClonada = clonador.clonarArvore(arvoreOriginal);

        System.out.println("Árvore Original (em ordem): " + arvoreOriginal.emOrdem());
        System.out.println("Altura da Árvore Original: " + arvoreOriginal.altura());
        System.out.println("Árvore Clonada (em ordem): " + arvoreClonada.emOrdem());
        System.out.println("Altura da Árvore Clonada: " + arvoreClonada.altura());

        // Testa se são objetos verdadeiramente separados
        arvoreOriginal.adiciona(100);
        System.out.println("\nÁrvore Original após adição: " + arvoreOriginal.emOrdem());
        System.out.println("Árvore Clonada após adição na original: " + arvoreClonada.emOrdem()); // Deve permanecer inalterada
    }
}
