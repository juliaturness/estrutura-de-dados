package esd;

public class ListaSequencialOrdenada<T extends Comparable> {

    final int MinSubseqLen = 31;

    Comparable[] area; // array para armazenar os elementos da lista
    int len = 0; // comprimento atual da lista
    final int defcap = 8; // capacidade inicial da lista

    @SuppressWarnings("unchecked")
    public ListaSequencialOrdenada() {
        area = new Comparable[defcap];
    }

    @SuppressWarnings("unchecked")
    public void expande(int len) {
        // expande a capacidade da lista: nova capacidade deve ser o dobro da atual
        Comparable[] novo = new Comparable[area.length * 2];

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

    public void remove(T valor) {
        // remove o valor, porém preservando o ordenamento da lista

        // procura a posição do valor na lista usando a busca binária do método procura()
        int posicao = procura(valor);

        if (posicao == -1) {
            return;
        }

        // move todos os elementos à direita da posição de remoção uma posição para a esquerda
        for (int i = posicao; i < len - 1; i++) {
            area[i] = area[i + 1];
        }

        area[len - 1] = null;
        len--;
    }

    public T obtem(int indice) {
        // retorna o valor armazenado na posição indicada pelo parâmetro "indice"
        // dispara uma exceção IndexOutOfBoundsException caso posição seja inválida
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException();
        }

        return (T) area[indice];
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

    public void insere(T elemento) {
        // insere o valor na lista, preservando seu ordenamento

        if (len == area.length) {
            expande(len);
        }

        int posicao = 0;

        // busca binária para encontrar a posição correta de inserção
        while (posicao < len && elemento.compareTo((T) area[posicao]) > 0) {
            posicao++;
        }

        // move os elementos à direita da posição de inserção para abrir espaço
        for (int i = len; i > posicao; i--) {
            area[i] = area[i - 1];
        }

        area[posicao] = elemento;
        len++;
    }

    public int procura(T valor) {
        // procura o valor dentro da lista usando busca binária
        // retorna a posição onde se encontra, ou -1 caso não exista

        int inicio = 0; // início da busca
        int fim = len - 1; // fim da busca

        while (inicio <= fim) {
            // encontra o meio da lista
            int meio = inicio + (fim - inicio) / 2;

            // armazena o elemento central da lista
            T elementoMeio = (T) area[meio];

            // compara o valor sendo procurado e o meio da lista
            int comparacao = valor.compareTo(elementoMeio);

            if (comparacao == 0) {
                // valor encontrado
                return meio;

            } else if (comparacao < 0) {
                // se o valor for menor, pega a parcela final do array
                fim = meio - 1;

            } else {
                // se o valor for menor, pega a parcela inicial do array
                inicio = meio + 1;
            }
        }

        return -1; // se não encontrar o valor, retorna -1
    }

   public T maximo() {
    if (esta_vazia()) throw new RuntimeException("Lista está vazia");
    return obtem(len - 1); 
}

public T minimo() {
    if (esta_vazia()) throw new RuntimeException("Lista está vazia");
    return obtem(0); 
}
}