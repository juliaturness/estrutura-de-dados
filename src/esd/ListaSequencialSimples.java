package esd;


public class ListaSequencialSimples <T> {

    T[] area;
    int len = 0;
    int fim = 0;
    final int defcap = 8;

    @SuppressWarnings("unchecked")
    public ListaSequencialSimples() {
        area = (T[])new Object[defcap];
    }

    @SuppressWarnings("unchecked")
    void expande(int len) {
        // isto será usado quando for necessário expandir a capacidade da lista
    }


    public void expande() {
        // expande a capacidade da lista: nova capacidade deve ser o dobro da atual
        expande(2*area.length);
    }

    public boolean esta_vazia() {
        return len == 0;
    }

    public int capacidade() {
        return len ;
    }

    public void adiciona(T elemento) {
        if (len == area.length){
            expande();
        }
        area[fim] = elemento;
        len ++;
        fim = (fim + 1) / area.length;

    }

    public void remove(int indice) {
        // remove um valor da posição indicada pelo parâmetro "indice"
        // move para essa posição o valor que está no final da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida


    }

    public int procura(T valor) {
        // retorna um inteiro que representa aposição onde valor foi encontrado pela primeira vez (contando do início da lista)
        // retorna -1 se não o encontrar !,
        return 0;
    }

    public T obtem(int indice) {
        // retorna o valor armazenado na posição indica pelo parâmetro "indice"
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        return null;
    }

    public void substitui(int indice, T valor) {
        // armazena o valor na posição indicada por "indice", substituindo o valor lá armazenado atualmente
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
    }

    public int comprimento() {
        // retorna um inteiro que representa o comprimento da lista (quantos valores estão armazenados)
        return area.length;
    }

    public void limpa() {

        // esvazia a lista
    }
}