package atividades.arvore;

import esd.APB;
import esd.ListaSequencial; // Necessário para usar emOrdem

/**
 * Classe de utilidade para mesclar duas árvores APB em uma nova árvore.
 */
public class MesclaDuasArvores<T extends Comparable<T>> {

    /**
     * Mescla duas árvores APB em uma nova árvore.
     * Os elementos de ambas as árvores são adicionados à nova árvore.
     * Duplicatas (valores iguais) serão tratadas de acordo com o método 'adiciona' da APB (valor atualizado).
     *
     * @param arvore1 A primeira árvore APB.
     * @param arvore2 A segunda árvore APB.
     * @return Uma nova árvore APB contendo todos os elementos das duas árvores originais.
     */
    public APB<T> mesclarArvores(APB<T> arvore1, APB<T> arvore2) {
        APB<T> novaArvore = new APB<>();

        // Adiciona todos os elementos da primeira árvore
        if (arvore1 != null) {
            ListaSequencial<T> elementos1 = arvore1.emOrdem();
            for (int i = 0; i < elementos1.comprimento(); i++) {
                novaArvore.adiciona(elementos1.obtem(i));
            }
        }

        // Adiciona todos os elementos da segunda árvore
        if (arvore2 != null) {
            ListaSequencial<T> elementos2 = arvore2.emOrdem();
            for (int i = 0; i < elementos2.comprimento(); i++) {
                novaArvore.adiciona(elementos2.obtem(i));
            }
        }

        return novaArvore;
    }

    // Exemplo de uso
    public static void main(String[] args) {
        APB<Integer> arvoreA = new APB<>();
        arvoreA.adiciona(10);
        arvoreA.adiciona(5);
        arvoreA.adiciona(15);
        arvoreA.adiciona(3);
        arvoreA.adiciona(7);

        APB<Integer> arvoreB = new APB<>();
        arvoreB.adiciona(20);
        arvoreB.adiciona(12);
        arvoreB.adiciona(25);
        arvoreB.adiciona(7); // Valor duplicado para testar o comportamento

        MesclaDuasArvores<Integer> mesclador = new MesclaDuasArvores<>();
        APB<Integer> arvoreMesclada = mesclador.mesclarArvores(arvoreA, arvoreB);

        System.out.println("Árvore A (em ordem): " + arvoreA.emOrdem());
        System.out.println("Árvore B (em ordem): " + arvoreB.emOrdem());
        System.out.println("Árvore Mesclada (em ordem): " + arvoreMesclada.emOrdem());
        // Esperado: [3, 5, 7, 10, 12, 15, 20, 25]
    }
}
