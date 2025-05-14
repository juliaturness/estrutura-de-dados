//package esd;
//
//
//public class ListaSequencial <T> {
//
//    T[] area;
//    int len = 0;
//    final int defcap = 8;
//
//    @SuppressWarnings("unchecked")
//    public ListaSequencial() {
//        area = (T[])new Object[defcap];
//    }
//
//    @SuppressWarnings("unchecked")
//    void expande(int len) {
//        // isto será usado quando for necessário expandir a capacidade da lista
//    }
//
//
//    public void expande() {
//        // expande a capacidade da lista: nova capacidade deve ser o dobro da atual
//        expande(2*area.length);
//    }
//
//    public boolean esta_vazia() {
//        // retorna true se lista estiver vazia, ou false caso contrário
//    }
//
//    public int capacidade() {
//        // retorna um inteiro que representa a capacidade da lista
//    }
//
//    public void adiciona(T elemento) {
//        // adiciona um valor ao final da lista
//    }
//
//    public void insere(int indice, T elemento) {
//        // insere um valor na posição indicada por "indice"
//        // dispara IndexOutOfBoundsException se "indice" for inválido
//    }
//
//
//    public void remove(int indice) {
//        // remove um valor da posição indicada pelo parâmetro "indice"
//        // move uma posição para trás os valores das próximas posições
//        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
//    }
//
//    public void remove_ultimo() {
//        // remove o último valor da lista
//        // disparar uma exceção IndexOutOfBoundsException caso lista vazia
//    }
//
//    public int procura(T valor) {
//        // retorna um inteiro que representa aposição onde valor foi encontrado pela primeira vez (contando do início da lista)
//        // retorna -1 se não o encontrar !
//    }
//
//    public T obtem(int indice) {
//        // retorna o valor armazenado na posição indica pelo parâmetro "indice"
//        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
//    }
//
//    public void substitui(int indice, T valor) {
//        // armazena o valor na posição indicada por "indice", substituindo o valor lá armazenado atualmente
//        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
//    }
//
//    public int comprimento() {
//        // retorna um inteiro que representa o comprimento da lista (quantos valores estão armazenados)
//    }
//
//    public void limpa() {
//        // esvazia a lista
//    }
//}