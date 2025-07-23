package esd;

public class APB<T extends Comparable> {

    class NodoAPB {
        T valor;
        NodoAPB esq = null, dir = null, pai = null;

        NodoAPB(T val, NodoAPB nodo_pai) {
            valor = val;
            pai = nodo_pai;
        }
    }

    public NodoAPB raiz = null;

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

    public T procura(T val) {
        NodoAPB atual = raiz;

        while (atual != null) {
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

    public T remove(T val) {
        NodoAPB nodoRemovido = removeRec(raiz, val);
        return nodoRemovido != null ? nodoRemovido.valor : null;
    }

    private NodoAPB removeRec(NodoAPB nodo, T val) {
        if (nodo == null) return null;

        int cmp = val.compareTo(nodo.valor);

        if (cmp < 0) {
            nodo.esq = removeRec(nodo.esq, val);
        } else if (cmp > 0) {
            nodo.dir = removeRec(nodo.dir, val);
        } else {
            if (nodo.esq == null) {
                if (nodo.dir != null) nodo.dir.pai = nodo.pai;
                return nodo.dir;
            } else if (nodo.dir == null) {
                if (nodo.esq != null) nodo.esq.pai = nodo.pai;
                return nodo.esq;
            }
            NodoAPB sucessor = menorNodo(nodo.dir);
            nodo.valor = sucessor.valor;
            nodo.dir = removeRec(nodo.dir, sucessor.valor);
        }

        return nodo;
    }

    private NodoAPB menorNodo(NodoAPB nodo) {
        while (nodo.esq != null)
            nodo = nodo.esq;
        return nodo;
    }


    // Retorna a raiz
    public T obtem_raiz() {
        return raiz != null ? raiz.valor : null;
    }

    public void _emOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual == null) return;
        _emOrdem(atual.esq, lista);
        lista.adiciona(atual.valor);
        _emOrdem(atual.dir, lista);
    }

    public ListaSequencial emOrdem() {
        ListaSequencial lista = new ListaSequencial();
        if (raiz != null) _emOrdem(raiz, lista);
        return lista;
    }

    public void _preOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual == null) return;
        lista.adiciona(atual.valor);
        _preOrdem(atual.esq, lista);
        _preOrdem(atual.dir, lista);
    }

    public ListaSequencial preOrdem() {
        ListaSequencial lista = new ListaSequencial();
        if (raiz != null) _preOrdem(raiz, lista);
        return lista;
    }

    public void _posOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual == null) return;
        _posOrdem(atual.esq, lista);
        _posOrdem(atual.dir, lista);
        lista.adiciona(atual.valor);
    }

    public ListaSequencial posOrdem() {
        ListaSequencial lista = new ListaSequencial();
        if (raiz != null) _posOrdem(raiz, lista);
        return lista;
    }

    public ListaSequencial emLargura() {
        ListaSequencial lista = new ListaSequencial();
        Fila<NodoAPB> fila = new Fila<>();

        if (raiz != null) {
            fila.adiciona(raiz);
        }

        while (!fila.estaVazia()) {
            NodoAPB atual = fila.remove();
            lista.adiciona(atual.valor);
            if (atual.esq != null)
                fila.adiciona(atual.esq);
            if (atual.dir != null)
                fila.adiciona(atual.dir);

        }

        return lista;
    }

    public T menor() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.esq != null)
            atual = atual.esq;
        return atual.valor;
    }

    public T maior() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.dir != null)
            atual = atual.dir;
        return atual.valor;
    }

    public T menor_que(T val) {
        return _menorQue(raiz, val);
    }

    private T _menorQue(NodoAPB nodo, T val) {
        if (nodo == null) return null;
        int cmp = nodo.valor.compareTo(val);

        if (cmp == 0) {
            return nodo.valor;
        } else if (cmp > 0) {
            return _menorQue(nodo.esq, val);
        } else {
            T candidatoDir = _menorQue(nodo.dir, val);
            return (candidatoDir != null) ? candidatoDir : nodo.valor;
        }
    }

    public T maior_que(T val) {
        return _maiorQue(raiz, val);
    }
    private T _maiorQue(NodoAPB nodo, T val) {
        if (nodo == null) return null;

        int cmp = nodo.valor.compareTo(val);

        if (cmp == 0) {
            return nodo.valor;
        } else if (cmp < 0) {
            return _maiorQue(nodo.dir, val);
        } else {
            T candidatoEsq = _maiorQue(nodo.esq, val);
            return (candidatoEsq != null) ? candidatoEsq : nodo.valor;
        }
    }

    public ListaSequencial menores_que(T val) {
        ListaSequencial lista = new ListaSequencial();
        _menoresQue(raiz, val, lista);
        return lista;
    }

    private void _menoresQue(NodoAPB nodo, T val, ListaSequencial lista) {
        if (nodo == null) return;
        _menoresQue(nodo.esq, val, lista);
        if (nodo.valor.compareTo(val) <= 0)
            lista.adiciona(nodo.valor);
        _menoresQue(nodo.dir, val, lista);
    }


    public ListaSequencial maiores_que(T val) {
        ListaSequencial lista = new ListaSequencial();
        _maioresQue(raiz, val, lista);
        return lista;
    }


    private void _maioresQue(NodoAPB nodo, T val, ListaSequencial lista) {
        if (nodo == null) return;
        _maioresQue(nodo.esq, val, lista);
        if (nodo.valor.compareTo(val) >= 0) {
            lista.adiciona(nodo.valor);
        }

        _maioresQue(nodo.dir, val, lista);
    }


    public int altura() {
        return _altura(raiz);
    }

    int _altura(NodoAPB nodo) {
        if (nodo == null) return 0;

        int ae = 1 + _altura(nodo.esq);
        int ad = 1 + _altura(nodo.dir);

        return Math.max(ae, ad);
    }

    public int tamanho() {
        return _comprimento(raiz);
    }

    private int _comprimento(NodoAPB nodo) {
        if (nodo == null) return 0;
        return 1 + _comprimento(nodo.esq) + _comprimento(nodo.dir);
    }

    public void limpa() {
        raiz = null;
    }

    public boolean esta_vazia() {
        return raiz == null;
    }

    public ListaSequencial faixa(T de, T ate) {
        ListaSequencial lista = new ListaSequencial();
        _faixa(raiz, lista, de, ate);
        return lista;
    }

    private void _faixa(NodoAPB nodo, ListaSequencial lista, T de, T ate) {
        if (nodo == null) return;

        if (nodo.valor.compareTo(de) > 0) {
            _faixa(nodo.esq, lista, de, ate);
        }

        if (nodo.valor.compareTo(de) >= 0 && nodo.valor.compareTo(ate) <= 0) {
            lista.adiciona(nodo.valor);
        }

        if (nodo.valor.compareTo(ate) < 0) {
            _faixa(nodo.dir, lista, de, ate);
        }
    }

    public void inverte() {
        raiz = inverteRec(raiz);
    }

    private NodoAPB inverteRec(NodoAPB nodo) {
        if (nodo == null) return null;

        // Inverte recursivamente os filhos esquerdo e direito
        NodoAPB esquerdaInvertida = inverteRec(nodo.esq);
        NodoAPB direitaInvertida = inverteRec(nodo.dir);

        // Troca os filhos
        nodo.esq = direitaInvertida;
        nodo.dir = esquerdaInvertida;

        return nodo;
    }

    public int compara_arvores(APB<T> outraArvore) {
        return comparaNodos(this.raiz, outraArvore.raiz);
    }

    // Método recursivo para comparar dois nós
    private int comparaNodos(NodoAPB nodo1, NodoAPB nodo2) {
        // Caso base 1: Ambos os nós são null -> As árvores são idênticas até aqui
        if (nodo1 == null && nodo2 == null) {
            return 2;
        }

        // Caso base 2: Um dos nós é null e o outro não -> As árvores são diferentes
        if (nodo1 == null || nodo2 == null) {
            return 0;
        }

        // Caso base 3: Comparando os valores dos nós
        if (!nodo1.valor.equals(nodo2.valor)) {
            return 0; // Se os valores forem diferentes, as árvores são diferentes
        }

        // Comparando as subárvores esquerdas e direitas
        int esq = comparaNodos(nodo1.esq, nodo2.esq);
        int dir = comparaNodos(nodo1.dir, nodo2.dir);

        // Se ambas as subárvores esquerdas e direitas forem idênticas, então as árvores têm a mesma estrutura
        if (esq == 2 && dir == 2) {
            return 2; // As árvores são idênticas
        }

        // Caso contrário, se as árvores possuem os mesmos valores mas com topologias diferentes
        return 1;
    }

    // Clona a árvore
    public APB<T> clone() {
        APB<T> novaArvore = new APB<>();
        novaArvore.raiz = _clone(this.raiz, null);
        return novaArvore;
    }

    // Método auxiliar recursivo para clonar a árvore
    private NodoAPB _clone(NodoAPB nodo, NodoAPB pai) {
        if (nodo == null) return null;

        // Cria um novo nó com o mesmo valor e referência para o pai
        NodoAPB novoNodo = new NodoAPB(nodo.valor, pai);

        // Clona os filhos esquerdo e direito recursivamente
        novoNodo.esq = _clone(nodo.esq, novoNodo);
        novoNodo.dir = _clone(nodo.dir, novoNodo);

        return novoNodo;
    }

    public ListaSequencial<T> lista_folhas() {
        ListaSequencial<T> folhas = new ListaSequencial<>();
        _listarFolhas(raiz, folhas);  // passa a lista já instanciada para a recursão
        return folhas;
    }

    private void _listarFolhas(NodoAPB nodo, ListaSequencial<T> folhas) {
        if (nodo == null) {
            return;
        }
        if (nodo.esq == null && nodo.dir == null) {
            folhas.adiciona(nodo.valor);
        }
        _listarFolhas(nodo.esq, folhas);
        _listarFolhas(nodo.dir, folhas);
    }

}
