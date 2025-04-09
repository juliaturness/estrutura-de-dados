package esd;

public class Fila <T> {

    T[] area;
    int inicio = 0;
    int fim = 0;
    int len = 0;
    final int TAMANHO = 8;

    @SuppressWarnings("unchecked")
    public Fila() {
        area = (T[]) new Object[TAMANHO];
    }

    public int comprimento() {
        return len;
    }

    public int capacidade() {
        return area.length;
    }

    public void adiciona(T algo) {
        // dispara exceção IndexOutOfBoundsException
        // se a fila estiver cheia

        if (len == area.length) {
//            throw new IndexOutOfBoundsException();
            expande();
        }

        area[fim] = algo;
        len++;
        // incremento circular
        fim = (fim + 1) % area.length;
    }

    public T remove() {
        // dispara exceção IndexOutOfBoundsException
        // se a fila estiver vazia

        if (len == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T algo = area[inicio];
        area[inicio] = null;
        len--;
        // incremento circular
        inicio = (inicio + 1) % area.length;

        return algo;
    }

    public T frente() {
        if (len == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return area[inicio];
    }

    public T tras() {
        if (len == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int pos = (fim - 1 + area.length) % area.length;
        return area[pos];
    }

    public boolean estaVazia() {
        return len == 0;
    }

    public void limpa() {
        for (int i = 0; i < len; i++) {
            area[(inicio + i) % area.length] = null;
        }

        inicio = 0;
        fim = 0;
        len = 0;
    }

    public void expande() {
        T[] novo = (T[]) new Object[area.length * 2];

        for (int i = 0; i < len; i++) {
            novo[i] = area[(inicio + i) % area.length];
        }

        area = novo;
        fim = len;
        inicio = 0;
    }
}