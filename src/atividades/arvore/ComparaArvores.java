package atividades.arvore;

import esd.APB;
import esd.ListaSequencial; // Necessário para usar emOrdem

/**
 * Classe de utilidade para comparar duas árvores APB.
 *
 * NOTA: Devido à classe `esd.APB.NodoAPB` ser privada, esta implementação
 * compara apenas os *elementos* das árvores, não a sua *estrutura* exata.
 * Duas árvores podem ter os mesmos elementos mas estruturas diferentes (ex: balanceamento).
 * Para comparar a estrutura, seria necessário acesso direto aos nós.
 */
public class ComparaArvores<T extends Comparable<T>> {

    /**
     * Compara duas árvores APB para verificar se contêm os mesmos elementos.
     * Não compara a estrutura exata da árvore.
     *
     * @param arvore1 A primeira árvore APB.
     * @param arvore2 A segunda árvore APB.
     * @return true se as árvores contêm os mesmos elementos, false caso contrário.
     */
    public boolean saoArvoresIguais(APB<T> arvore1, APB<T> arvore2) {
        // Se ambas são nulas, são consideradas iguais
        if (arvore1 == null && arvore2 == null) {
            return true;
        }
        // Se uma é nula e a outra não, são diferentes
        if (arvore1 == null || arvore2 == null) {
            return false;
        }

        // Compara os elementos usando a travessia em ordem
        ListaSequencial<T> elementos1 = arvore1.emOrdem();
        ListaSequencial<T> elementos2 = arvore2.emOrdem();

        if (elementos1.comprimento() != elementos2.comprimento()) {
            return false; // Tamanhos diferentes, não são iguais
        }

        for (int i = 0; i < elementos1.comprimento(); i++) {
            if (elementos1.obtem(i).compareTo(elementos2.obtem(i)) != 0) {
                return false; // Elementos diferentes na mesma posição
            }
        }
        return true; // Todos os elementos são iguais e na mesma ordem
    }

    // Exemplo de uso
    public static void main(String[] args) {
        APB<Integer> arvore1 = new APB<>();
        arvore1.adiciona(50);
        arvore1.adiciona(30);
        arvore1.adiciona(70);
        arvore1.adiciona(20);
        arvore1.adiciona(40);

        APB<Integer> arvore2 = new APB<>();
        arvore2.adiciona(50);
        arvore2.adiciona(30);
        arvore2.adiciona(70);
        arvore2.adiciona(20);
        arvore2.adiciona(40);

        APB<Integer> arvore3 = new APB<>();
        arvore3.adiciona(50);
        arvore3.adiciona(30);
        arvore3.adiciona(70);
        arvore3.adiciona(20);
        arvore3.adiciona(45); // Valor diferente

        APB<Integer> arvore4 = new APB<>();
        arvore4.adiciona(50);
        arvore4.adiciona(30);
        arvore4.adiciona(70);
        arvore4.adiciona(20);
        // Falta o 40, estrutura diferente, mas também elementos diferentes

        ComparaArvores<Integer> comparador = new ComparaArvores<>();

        System.out.println("Árvore 1 (em ordem): " + arvore1.emOrdem());
        System.out.println("Árvore 2 (em ordem): " + arvore2.emOrdem());
        System.out.println("Árvore 3 (em ordem): " + arvore3.emOrdem());
        System.out.println("Árvore 4 (em ordem): " + arvore4.emOrdem());

        System.out.println("Árvore 1 e Árvore 2 são iguais (elementos)? " + comparador.saoArvoresIguais(arvore1, arvore2)); // Esperado: true
        System.out.println("Árvore 1 e Árvore 3 são iguais (elementos)? " + comparador.saoArvoresIguais(arvore1, arvore3)); // Esperado: false
        System.out.println("Árvore 1 e Árvore 4 são iguais (elementos)? " + comparador.saoArvoresIguais(arvore1, arvore4)); // Esperado: false
        System.out.println("Árvore 1 e null são iguais (elementos)? " + comparador.saoArvoresIguais(arvore1, null)); // Esperado: false
        System.out.println("null e null são iguais (elementos)? " + comparador.saoArvoresIguais(null, null)); // Esperado: true
    }
}
