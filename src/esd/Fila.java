package esd;

public class Fila <T> {

    T[] area;
    int inicio = 0;
    int fim = 0;
    int len = 0;

    @SuppressWarnings("unchecked")
    public Fila(int cap) {
        area = (T[]) new Object[cap];
    }

    public int comprimento() {
        return len;
    }

    public int capacidade() {
        return area.length;
    }

    public void adiciona(T algo) {
        // dispara exceção IndexOutOfBounds
        // de fila cheia

        if(len == area.length) {
            throw new ArrayIndexOutOfBoundsException("Fila Cheia");
        }
            area[fim] = algo;
            fim++;
            if(fim == area.length) {
                fim = 0;
            }
            len++;
    }

    public T remove() {
        // dispara



        if(len == 0) {
            throw new ArrayIndexOutOfBoundsException("Fila vazia");
        }
        T algo = area[inicio];
        area[inicio] = null;
        inicio++;
       if (inicio == area.length) {
           inicio = 0;
       }
       len--;

        return algo;
    }

    public T frente() {
        if(len == 0) {
            throw new ArrayIndexOutOfBoundsException("Fila vazia");
        }
        return area[inicio];
    }

    public T fim() {
        if(len == 0) {
            throw new ArrayIndexOutOfBoundsException("Fila vazia");
        }
        int pos = fim - 1;
        if (pos < 0) {
            pos = area.length - 1;
        }
        return area[pos];
    }

    public boolean estaVazia() {
        return len == 0;
    }

    public void limpa() {

    }
}
