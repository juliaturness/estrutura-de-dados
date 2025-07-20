package atividades.arvore;

import esd.APB;
import esd.ListaSequencial; // Necessário para usar emOrdem

/**
 * Classe de utilidade para contar o número de nós folha em uma árvore APB.
 *
 * NOTA: Devido à classe `esd.APB.NodoAPB` ser privada, não é possível
 * acessar diretamente a estrutura interna da árvore (filhos esq/dir)
 * a partir desta classe. Portanto, uma contagem precisa de folhas
 * (que exige saber se um nó tem filhos) não pode ser feita diretamente.
 *
 * Esta implementação apenas demonstra a limitação e não pode fornecer
 * uma contagem precisa de folhas sem modificar a classe APB.
 * Se a intenção era contar o número total de elementos, use `arvore.comprimento()`.
 */
public class ContaFOlha<T extends Comparable<T>> {

    /**
     * Este método não pode contar folhas de forma precisa devido à restrição
     * de acesso à estrutura interna da árvore (NodoAPB é privado).
     * Ele apenas retorna 0 e imprime uma mensagem de aviso.
     *
     * @param arvore A árvore APB a ser analisada.
     * @return Sempre 0, devido à limitação de acesso.
     */
    public int contaFolhas(APB<T> arvore) {
        System.out.println("AVISO: Não é possível contar folhas diretamente. A classe APB.NodoAPB é privada.");
        System.out.println("Para contar folhas, a classe APB precisaria expor um método para isso ou NodoAPB ser público.");
        return 0; // Não é possível implementar corretamente sem acesso aos nós
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

        ContaFOlha<Integer> contador = new ContaFOlha<>();
        System.out.println("Árvore (em ordem): " + arvore.emOrdem());
        System.out.println("Número de folhas na árvore: " + contador.contaFolhas(arvore));
    }
}
