package esd;

public class APB <T extends Comparable> {
    class NodoAPB {
        T valor;
        NodoAPB esq = null, dir = null, pai = null;

        NodoAPB(T val, NodoAPB nodo_pai) {
            valor = val;
            pai = nodo_pai;
        }
    }


    NodoAPB raiz = null;

    // Adiciona um valor na árvore
    public void adiciona(T val) {
        NodoAPB atual = raiz;

        if (atual == null) {
            raiz = new NodoAPB(val, null);
            return;
        }

        while (true) {
            if (val.compareTo(atual.valor) == 0) {
                atual.valor = val;
                return;
            } else if (val.compareTo(atual.valor) < 0) {
                if (atual.esq != null) {
                    atual = atual.esq;
                } else {
                    atual.esq = new NodoAPB(val, atual);
                    return;
                }
            } else {
                if (atual.dir != null) {
                    atual = atual.dir;
                } else {
                    atual.dir = new NodoAPB(val, atual);
                    return;
                }
            }
        }

    }

    // Procura um valor e retorna o nodo correspondente
    public T procura(T val) {
        NodoAPB atual = raiz;
        
        while (atual!= null) {
            int cmp = val.compareTo(atual.valor);
            if (cmp == 0) {
                return atual.valor;
            } else if (cmp < 0) {
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }
        return null;
    }


    // Remove um valor da árvore
    public void remove(T val) {
    }

    // Retorna a raiz
    public T obtem_raiz() {
        return raiz != null ? raiz.valor : null;
    }

    public void listeInOrder() {
        // Implementação futura (listagem em-ordem)
    }

    public void listePreOrder() {

    }

    public void listePostOrder() {
    }

    public void listeEmLargura() {
    }

    // Retorna o menor valor
    public T menor() {
        return null;
    }

    // Retorna o maior valor
    public T maior() {
        return null;
    }

    // Retorna o maior valor menor que um dado
    public T menorQue(T val) {
        return null;
    }

    // Retorna o menor valor maior que um dado
    public T maiorQue(T val) {
        return null;
    }

    // Lista de valores menores que um dado
    public void menoresQue(T val) {
    }

    // Lista de valores maiores que um dado
    public void maioresQue(T val) {
    }

    // Altura da árvore
    public int altura() {
        return -1;
    }

    // Comprimento (número de nós)
    public int comprimento() {
        return 0;
    }

    // Balanceia a árvore
    public void balanceia() {
    }

    // Limpa a árvore
    public void limpa() {
        raiz = null;
    }

    public boolean esta_vazia() {
        return raiz == null;
    }

}