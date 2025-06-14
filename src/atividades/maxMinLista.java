package atividades;

import esd.unidade1.ListaSequencialOrdenada;

public class maxMinLista{

    public static void main(String[] args) {
        ListaSequencialOrdenada<Integer> lista = new ListaSequencialOrdenada<>();

        lista.insere(5);
        lista.insere(6);
        lista.insere(3);
        lista.insere(11);
        lista.insere(10);
        lista.insere(1);


        System.out.println("\nMaior número: " + lista.maximo());
       

        System.out.println("\nMenor número: " + lista.minimo());
        
    }
}