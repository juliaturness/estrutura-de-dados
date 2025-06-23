package esd;

import java.security.InvalidParameterException;
import java.util.Random;

public class Lista<T extends Comparable<T>> {
    class Node {
        T valor = null;
        Node proximo;
        Node antecessor;

        Node() {
            proximo = this;
            antecessor = this;
        }

        Node(T valor) {
            this();
            this.valor = valor;
        }

        void conecta(Node sucessor) {
            this.proximo = sucessor;
            this.antecessor = sucessor.antecessor;
            this.antecessor.proximo = this;
            this.proximo.antecessor = this;
        }

        void desconecta() {
            this.antecessor.proximo = this.proximo;
            this.proximo.antecessor = this.antecessor;
        }

        T obtemValor() {
            return valor;
        }

        Node obtemProximo() {
            return proximo;
        }
    }

    private Node guarda;
    private int len = 0;

    public Lista() {
        guarda = new Node();
    }

    public void adiciona(T valor) {
        Node nodo = new Node(valor);
        nodo.conecta(guarda);
        len++;
    }

    public void insere(int indice, T valor) {
        if (indice < 0 || indice > len) {
            throw new InvalidParameterException("Indice invalido");
        }

        Node nodo = new Node(valor);
        Node sucessor = (indice == len) ? guarda : obtem_nodo(indice);
        nodo.conecta(sucessor);
        len++;
    }

    public void remove(int indice) {
        if (indice < 0 || indice >= len) {
            throw new InvalidParameterException("ruim");
        }
        Node nodo = obtem_nodo(indice);
        nodo.desconecta();
        len--;
    }

    public void remove_ultimo() {
        if (len == 0) return;
        guarda.antecessor.desconecta();
        len--;
    }

    public T obtem(int indice) {
        return obtem_nodo(indice).valor;
    }

    public T obtem_primeiro() {
        if (esta_vazia()) return null;
        return guarda.proximo.valor;
    }

    public T obtem_ultimo() {
        if (esta_vazia()) return null;
        return guarda.antecessor.valor;
    }

    public int procura(T valor) {
        Node atual = guarda.proximo;
        int i = 0;
        while (atual != guarda) {
            if ((valor == null && atual.valor == null) || (valor != null && valor.equals(atual.valor))) {
                return i;
            }
            atual = atual.proximo;
            i++;
        }
        return -1;
    }

    public void substitui(int indice, T valor) {
        Node nodo = obtem_nodo(indice);
        nodo.valor = valor;
    }

    public int comprimento() {
        return len;
    }

    public void limpa() {
        guarda.proximo = guarda;
        guarda.antecessor = guarda;
        len = 0;
    }

    public void ordena() {
        if (len <= 1) return;

        boolean trocou;
        do {
            trocou = false;
            Node atual = guarda.proximo;
            while (atual.proximo != guarda) {
                Node prox = atual.proximo;
                if (atual.valor.compareTo(prox.valor) > 0) {
                    T temp = atual.valor;
                    atual.valor = prox.valor;
                    prox.valor = temp;
                    trocou = true;
                }
                atual = atual.proximo;
            }
        } while (trocou);
    }

    public void inverte() {
        if (len <= 1) return;

        Node atual = guarda;
        do {
            Node temp = atual.proximo;
            atual.proximo = atual.antecessor;
            atual.antecessor = temp;
            atual = temp;
        } while (atual != guarda);
    }

    public void embaralha() {
        if (len <= 1) return;

        Random rand = new Random();
        for (int i = len - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Node ni = obtem_nodo(i);
            Node nj = obtem_nodo(j);
            T temp = ni.valor;
            ni.valor = nj.valor;
            nj.valor = temp;
        }
    }

    public boolean esta_vazia() {
        return len == 0;
    }

    private Node obtem_nodo(int indice) {
        if (indice < 0 || indice >= len) {
            throw new InvalidParameterException("indice fora dos limites");
        }
        Node atual = guarda.proximo;
        for (int i = 0; i < indice; i++) {
            atual = atual.proximo;
        }
        return atual;
    }
    public boolean esta_ordenada() {
        if (len <= 1) {
            return true;
        }

        Node atual = guarda.proximo;
        while (atual.proximo != guarda) {
            if (atual.valor.compareTo(atual.proximo.valor) > 0) {
                return false;
            }
            atual = atual.proximo;
        }
        return true;
    }


}
