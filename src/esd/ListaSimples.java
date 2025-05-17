package esd;

public class ListaSimples <T> {
    class Node {
        T valor;
        Node proximo;

        Node(T valor) {
            this.valor = valor;
            proximo = null;
        }
    }

    Node primeiro = null;
    Node ultimo = null;
    int len = 0;

    public ListaSimples() {
    }

    // adiciona no fim
    public void adiciona(T valor) {
        if(len>0){
            ultimo.proximo = nodo;
        }

        // atualiza o ultimo para ser meu antecessor e o sucessor do ultimo ser eu, incrementa o len

    }

    // obtém o valor que está na posição dada por "indice"
    // se "indice" >= comprimento da lista, dispara exceção
    // IndexOutOfBoundException
    public T obtem(int indice) {
        //pobtem_node
        Node atual = primeiro;
        while (indice -- > 0){ atual = atual.proximo;}
        return atual;
    }

    // insere valor na posição dada por "indice"
    // se "indice" > comprimento da lista, dispara exceção
    // IndexOutOfBoundException
    public void insere(int indice, T valor) {
        //pego o indice a ser inserido, insiro na lista, atualizo que o
        // antecessor do sucessor é o meu antecessor || e o antecessor do meu sucessor sou eu
        // o meu sucessor é o sucessor do meu antecessor || o sucessor do meu antecessor sou eu
    }

    public boolean esta_vazia() {
        return true;
    }

    public int comprimento() {
        return 0;
    }

    public void remove(int indice) {
        //

    }

    public void substitui(int indice, T valor) {

    }

    public void inverte() {

    }

}