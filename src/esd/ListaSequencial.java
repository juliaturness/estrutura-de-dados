package esd;

public class ListaSequencial<T> {
    T[] area; // vetor que armazena os elementos
    int len = 0; // controla o número de elementos armazenados
    final int defcap = 8; // capacidade inicial da lista

    @SuppressWarnings("unchecked")
    public ListaSequencial() {
        // cria a área de armazenamento com capacidade inicial padrão
        area = (T[]) new Object[defcap];
    }

    public void expande() {
        // expande a capacidade da lista: nova capacidade deve ser o dobro da atual
        T[] novo = (T[]) new Object[area.length * 2];

        for (int i = 0; i < len; i++) {
            novo[i] = area[i];
        }

        area = novo;
    }

    public boolean esta_vazia() {
        // retorna true se lista estiver vazia, ou false caso contrário
        return len == 0;
    }

    public int capacidade() {
        // retorna um inteiro que representa a capacidade da lista
        return area.length;
    }

    public void adiciona(T elemento) {
        // adiciona um valor ao final da lista
        if (len == area.length) {
            expande(); // expande a lista se estiver cheia
        }

        area[len] = elemento;
        len++;
    }

    public void insere(int indice, T elemento) {
        // insere um valor na posição indicada por "indice"
        // dispara IndexOutOfBoundsException se "indice" for inválido
        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException();
        }

        if (len == area.length) {
            expande(); // expande se necessário
        }

        /*
            desloca os elementos uma posição para a direita
            começando do fim até a posição de inserção
        */
        for (int i = len; i > indice; i--) {
            area[i] = area[i - 1];
        }

        area[indice] = elemento;
        len++;
    }

    public void remove(int indice) {
        // remove um valor da posição indicada pelo parâmetro "índice"
        // move para essa posição o valor que está no final da lista
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida

        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = indice; i < len - 1; i++) {
            area[i] = area[i + 1];
        }
        area[len - 1] = null;
        len--;
    }

    public void remove_ultimo() {
        // remove o último valor da lista
        // disparar uma exceção IndexOutOfBoundsException caso lista vazia
        if (len == 0) {
            throw new IndexOutOfBoundsException();
        }

        area[len - 1] = null;
        len--;
    }

    public int procura(T valor) {
        // retorna um inteiro que representa a posição onde valor
        // foi encontrado pela primeira vez (contando do início da lista)
        // retorna -1 se não o encontrar!

        for (int i = 0; i < len; i++) {
            if (valor.equals(area[i])) {
                return i;
            }
        }

        return -1;
    }

    public T obtem(int indice) {
        // retorna o valor armazenado na posição indicada pelo parâmetro "indice"
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException();
        }

        return area[indice];
    }

    public void substitui(int indice, T valor) {
        // armazena o valor na posição indicada por "índice", substituindo o valor lá armazenado atualmente
        // disparar uma exceção IndexOutOfBoundsException caso posição seja inválida
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException();
        }

        area[indice] = valor;
    }

    public int comprimento() {
        // retorna um inteiro que representa o comprimento da lista (quantos valores estão armazenados)
        return len;
    }

    public void limpa() {
        // esvazia a lista
        for (int i = 0; i < len; i++) {
            area[i] = null;
        }

        len = 0;
    }

    public void ordena(){
        if (len > 1){
            ordena_mescla(0, len);
        }
    }

    public void ordena_mescla(int pos1, int pos2){
        if( pos2 - pos1 >1 ){
            int meio = pos1 +(pos2 -pos1)/2;
            ordena_mescla(pos1, meio);
            ordena_mescla(meio, pos2);
            mescla(meio, pos1, pos2);
        }
    }

    public void mescla(int meio, int pos1, int pos2){
        int tamanhoEsq = meio - pos1;
        int tamanhoDir = pos2 - meio;

        T[] esquerda = (T[]) new Object[tamanhoEsq];
        T[] direita = (T[]) new Object[tamanhoDir];


        for (int i = 0; i < tamanhoEsq; i++) {
            esquerda[i] = area[pos1 + i];
        }

        for (int i = 0; i < tamanhoDir; i++) {
            direita[i] = area[meio + i];
        }
        int i = 0, j = 0, k = pos1;


        while (i < tamanhoEsq && j < tamanhoDir) {
            Comparable<T> elemEsq = (Comparable<T>) esquerda[i];
            T elemDir = (T) direita[j];
            if (elemEsq.compareTo(elemDir) <= 0) {
                area[k] = (T) esquerda[i];
                i++;
            } else {
                area[k] = elemDir;
                j++;
            }
            k++;
        }

        // Copia o restante da sublista esquerda, se houver
        while (i < tamanhoEsq) {
            area[k++] = (T) esquerda[i++];
        }

        // Copia o restante da sublista direita, se houver
        while (j < tamanhoDir) {
            area[k++] = (T) direita[j++];
        }
    }
}