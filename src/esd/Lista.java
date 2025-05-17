package esd;

import java.security.InvalidParameterException;

public class Lista <T> {
    class Node {
        T valor = null;
        Node proximo;
        Node antecessor;

        // operações de Node
        Node() {
            // este nodo deve inicialmente ser seu próprio sucessor e antecessor
            proximo = this;
            antecessor = this;
        }

        Node(T valor) {
            // inicializa o nodo, que deve inicialmente ser seu próprio sucessor e antecessor
            proximo = this;
            antecessor = this;
            this.valor = valor;
        }

        void conecta(Node sucessor) {
            // insere este nodo antes de sucessor
            this.proximo = sucessor;
            this.antecessor = sucessor.antecessor;
            this.antecessor.proximo = this;
            this.proximo.antecessor = this;
        }

        void desconecta() {
            // desconecta este nodo, desfazendo as referências de seu antecessor e sucessor
            this.antecessor.proximo = this.proximo;
            this.proximo.antecessor = this.antecessor;
        }
    };

    Node guarda;
    int len = 0;

    // operações de Lista
    public Lista() {
        guarda = new Node();
    }

    public void adiciona(T valor){
        Node nodo = new Node();
        nodo.conecta(guarda);
        len++;
    }

    public void insere(int indice, T valor){
        if (indice < 0 || indice > len){
            throw new InvalidParameterException("Indice invalido");
        }
        Node nodo = new Node();
        Node sucessor = obtem_nodo(indice);
        nodo.conecta(sucessor);
        len++;
    }

    //tem que ser node em vez de public
     public void obtem_nodo(int indice){
        Node atual = guarda.proximo;
        while(indice -- > 0){
            atual = atual.proximo;
        }
     }
};