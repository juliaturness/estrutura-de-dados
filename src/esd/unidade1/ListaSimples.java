package esd.unidade1;

public class ListaSimples <T> {
    class Node {
        T valor;
        Node proximo;

        public Node(T valor) {
            this.valor = valor;
        }
    };

    Node primeiro = null;
    Node ultimo = null;
    int len = 0;

    // operações de Lista
    public ListaSimples() {}

    public void adiciona(T valor) {
        // adiciona no fim
        Node nodo = new Node(valor);

        if (primeiro != null) {
            ultimo.proximo = nodo;
        } else {
            primeiro = nodo;
        }
        ultimo = nodo;
        len++;
    }

    // obtem o valor que está na posição dada por "indice"
    // se "indice" >= comprimento da lista, dispara excessão
    // IndexOutOfBoundsException
    public T obtem(int indice) {
        if (indice >= len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        Node nodo = encontraNodo(indice);

        return nodo.valor;
    }

    // insere valor na prosição dada por "indice"
    // se "indice" > comprimento da lista, dispara excessão
    // IndexOutOfBoundsException
    public void insere(int indice, T valor) {
        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        Node novo = new Node(valor);

        if (indice == 0) {

            novo.proximo = primeiro;
            primeiro = novo;

            if (len == 0) {
                ultimo = novo;
            }

        } else {

            Node anterior = encontraNodo(indice - 1);

            novo.proximo = anterior.proximo;
            anterior.proximo = novo;

            if (indice == len) {
                ultimo = novo;
            }
        }

        len++;
    }

    public T remove(int indice) {
        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        T valor;

        if (indice == 0) {

            Node nodo = primeiro;
            valor = nodo.valor;

            primeiro = primeiro.proximo;

            if (len == 1) {
                ultimo = null;
            }

        } else {

            Node anterior = encontraNodo(indice - 1);

            Node nodo = anterior.proximo;
            valor = nodo.valor;
            anterior.proximo = nodo.proximo;

            if (nodo == ultimo) {
                ultimo = anterior;
            }
        }

        len--;
        return valor;
    }

    public T remove_ultimo() {
        if (len == 0) {
            throw new IndexOutOfBoundsException("Lista vazia");
        }

        T valor;

        if (len == 1) {
            valor = primeiro.valor;
            primeiro = null;
            ultimo = null;

        } else {
            Node anterior = primeiro;

            while (anterior.proximo != ultimo) {
                anterior = anterior.proximo;
            }
            valor = ultimo.valor;
            anterior.proximo = null;
            ultimo = anterior;
        }

        len--;
        return valor;
    }

    public T obtem_primeiro() {
        return primeiro.valor;
    }

    public T obtem_ultimo() {
        return ultimo.valor;
    }

    public int procura(T valor) {
        Node nodo = primeiro;
        int indice = 0;

        while (nodo != null) {
            if (valor.equals(nodo.valor)) {
                return indice;
            }
            nodo = nodo.proximo;
            indice++;
        }

        return -1;
    }

    public void substitui(int indice, T valor) {

        if (len == 0) {
            throw new IndexOutOfBoundsException("Lista vazia");
        }

        Node nodo = primeiro;

        for (int i = 0; i < indice; i++) {
            nodo = nodo.proximo;
        }

        nodo.valor = valor;
    }

    public int comprimento() {
        return len;
    }

    public void limpa() {
        primeiro = null;
        ultimo = null;
        len = 0;
    }

    public boolean esta_vazia() {
        return len == 0;
    }

    public void ordena() {}

    public void inverte() {}

    public void embaralha() {}

    private Node encontraNodo(int indice) {
        if (indice == 0) {
            return primeiro;
        }

        else if (indice == (len - 1)) {
            return ultimo;

        } else {
            Node nodo = primeiro;
            for (int i = 0; i < indice; i++) {
                nodo = nodo.proximo;
            }
            return nodo;
        }
    }

}