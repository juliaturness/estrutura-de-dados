package atividades.arvore;

import esd.APB;
import esd.ListaSequencial; // Necessário para usar emOrdem

/**
 * Classe de utilidade para inserir todos os elementos de uma subárvore (outra árvore APB)
 * na árvore principal.
 */
public class InsereUmaSubArvore<T extends Comparable<T>> {

    /**
     * Insere todos os elementos de uma subárvore (outra árvore APB) na árvore principal.
     *
     * @param arvorePrincipal A árvore APB onde os elementos serão inseridos.
     * @param subArvore A árvore APB cujos elementos serão inseridos na árvore principal.
     */
    public void insereSubArvore(APB<T> arvorePrincipal, APB<T> subArvore) {
        if (arvorePrincipal == null || subArvore == null || subArvore.esta_vazia()) {
            return; // Nada para inserir ou nenhuma árvore alvo
        }

        // Percorre a sub-árvore e adiciona cada elemento à árvore principal
        // Usando travessia em ordem para obter os elementos
        ListaSequencial<T> elementosSubArvore = subArvore.emOrdem();
        for (int i = 0; i < elementosSubArvore.comprimento(); i++) {
            arvorePrincipal.adiciona(elementosSubArvore.obtem(i));
        }
    }

    // Exemplo de uso
    public static void main(String[] args) {
        APB<Integer> arvorePrincipal = new APB<>();
        arvorePrincipal.adiciona(50);
        arvorePrincipal.adiciona(30);
        arvorePrincipal.adiciona(70);

        APB<Integer> subArvore = new APB<>();
        subArvore.adiciona(25);
        subArvore.adiciona(40);
        subArvore.adiciona(60);
        subArvore.adiciona(80);

        InsereUmaSubArvore<Integer> inserter = new InsereUmaSubArvore<>();
        System.out.println("Árvore Principal antes: " + arvorePrincipal.emOrdem());
        System.out.println("Sub-Árvore: " + subArvore.emOrdem());

        inserter.insereSubArvore(arvorePrincipal, subArvore);

        System.out.println("Árvore Principal depois: " + arvorePrincipal.emOrdem());
        // Esperado: [25, 30, 40, 50, 60, 70, 80]
    }
}
