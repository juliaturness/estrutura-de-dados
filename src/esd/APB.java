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

    // Remove um valor da árvore
    public void remove(T val) {
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

            if (atual.esq != null) {
                fila.adiciona(atual.esq);
            }
            if (atual.dir != null) {
                fila.adiciona(atual.dir);
            }
        }

        return lista;
    }

    public T menor() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.esq != null) {
            atual = atual.esq;
        }
        return atual.valor;
    }

    public T maior() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.dir != null) {
            atual = atual.dir;
        }
        return atual.valor;
    }

    public T menor_que(T val) {
        return _menorQue(raiz, val);
    }

    private T _menorQue(NodoAPB nodo, T val) {
        if (nodo == null) return null;

        if (nodo.valor.compareTo(val) < 0) {
            T resultado = _menorQue(nodo.dir, val);
            return resultado != null ? resultado : nodo.valor;
        }

        return _menorQue(nodo.esq, val);
    }

    public T maior_que(T val) {
        return _maiorQue(raiz, val);
    }

    private T _maiorQue(NodoAPB nodo, T val) {
        if (nodo == null) return null;

        if (nodo.valor.compareTo(val) > 0) {
            T resultado = _maiorQue(nodo.esq, val);
            return resultado != null ? resultado : nodo.valor;
        }

        return _maiorQue(nodo.dir, val);
    }

    public ListaSequencial menores_que(T val) {
        ListaSequencial lista = new ListaSequencial();
        _menoresQue(raiz, val, lista);
        return lista;
    }

    private void _menoresQue(NodoAPB nodo, T val, ListaSequencial lista) {
        if (nodo == null) return;

        _menoresQue(nodo.esq, val, lista);

        if (nodo.valor.compareTo(val) < 0) {
            lista.adiciona(nodo.valor);
        }

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

        if (nodo.valor.compareTo(val) > 0) {
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

    public int comprimento() {
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

    // Método público para inverter a árvore
    public void inverte() {
        raiz = inverteRec(raiz);
    }

    // Função recursiva que inverte a subárvore a partir do nodo fornecido
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








}
