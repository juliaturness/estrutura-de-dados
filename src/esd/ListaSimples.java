package esd;

import java.util.Random;

public class ListaSimples {
    protected Node inicio = null;
    protected Node ultimo = null;
    protected int len = 0;

    // Classe interna Node conforme o diagrama
    class Node {
        private Object valor;
        private Node proximo = null;

        public Node(Object valor) {
            this.valor = valor;
        }

        public Object obtemValor() {
            return valor;
        }

        public Node obtemProximo() {
            return proximo;
        }
    }

    public void adiciona(Object valor) {
        Node nodo = new Node(valor);
        if (inicio == null) {
            inicio = nodo;
        } else {
            ultimo.proximo = nodo;
        }
        ultimo = nodo;
        len++;
    }

    public void insere(int indice, Object valor) {
        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        Node novo = new Node(valor);

        if (indice == 0) {
            novo.proximo = inicio;
            inicio = novo;
            if (len == 0) {
                ultimo = novo;
            }
        } else {
            Node anterior = inicio;
            for (int i = 0; i < indice - 1; i++) {
                anterior = anterior.proximo;
            }
            novo.proximo = anterior.proximo;
            anterior.proximo = novo;
            if (indice == len) {
                ultimo = novo;
            }
        }

        len++;
    }

    public Object remove(int indice) {
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        Object valor;

        if (indice == 0) {
            valor = inicio.valor;
            inicio = inicio.proximo;
            if (len == 1) {
                ultimo = null;
            }
        } else {
            Node anterior = inicio;
            for (int i = 0; i < indice - 1; i++) {
                anterior = anterior.proximo;
            }
            Node removido = anterior.proximo;
            valor = removido.valor;
            anterior.proximo = removido.proximo;
            if (removido == ultimo) {
                ultimo = anterior;
            }
        }

        len--;
        return valor;
    }

    public Object remove_ultimo() {
        return remove(len - 1);
    }

    public Object obtem(int indice) {
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        Node atual = inicio;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }

        return atual.valor;
    }

    public Object obtem_primeiro() {
        if (inicio == null) throw new IllegalStateException("Lista vazia");
        return inicio.valor;
    }

    public Object obtem_ultimo() {
        if (ultimo == null) throw new IllegalStateException("Lista vazia");
        return ultimo.valor;
    }

    public int procura(Object valor) {
        Node atual = inicio;
        int i = 0;
        while (atual != null) {
            if (atual.valor.equals(valor)) {
                return i;
            }
            atual = atual.proximo;
            i++;
        }
        return -1;
    }

    public void substitui(int indice, Object valor) {
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Indice inválido");
        }

        Node atual = inicio;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        atual.valor = valor;
    }

    public int comprimento() {
        return len;
    }

    public void limpa() {
        inicio = null;
        ultimo = null;
        len = 0;
    }

    public void ordena() {
        if (len < 2) return;
        for (int i = 0; i < len - 1; i++) {
            Node a = inicio;
            Node b = inicio.proximo;
            for (int j = 0; j < len - i - 1; j++) {
                Comparable va = (Comparable) a.valor;
                Comparable vb = (Comparable) b.valor;
                if (va.compareTo(vb) > 0) {
                    Object temp = a.valor;
                    a.valor = b.valor;
                    b.valor = temp;
                }
                a = b;
                b = b.proximo;
            }
        }
    }

    public void inverte() {
        Node anterior = null;
        Node atual = inicio;
        Node proximo;
        ultimo = inicio;

        while (atual != null) {
            proximo = atual.proximo;
            atual.proximo = anterior;
            anterior = atual;
            atual = proximo;
        }

        inicio = anterior;
    }

    public void embaralha() {
        if (len < 2) return;

        Random rand = new Random();
        for (int i = len - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            if (i != j) {
                Node ni = inicio;
                for (int k = 0; k < i; k++) ni = ni.proximo;

                Node nj = inicio;
                for (int k = 0; k < j; k++) nj = nj.proximo;

                Object temp = ni.valor;
                ni.valor = nj.valor;
                nj.valor = temp;
            }
        }
    }

    public boolean esta_vazia() {
        return len == 0;
    }
}
