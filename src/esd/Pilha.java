package esd;

public class Pilha <T> {
    T[] mem; // a área de armazenamento
    int len = 0; //a quantidade de dados armazenados
    final int defcap = 8; // capacidade inicial

    @SuppressWarnings("unchecked")
    public Pilha() {
        mem = (T[])new Object[defcap];
    }

    public int comprimento() {
        return  len;
    }

    public int capacidade() {
        return mem.length;
    }

    public void empilha(T algo) {

        if(len == mem.length) {
            T[] capmaior = (T[])new Object[mem.length * 2];
            for (int i = 0; i < len; i++) {
                capmaior[i] = mem[i];
            }
            mem = capmaior;
        }
        mem[len] = algo;
        len++;
    }

    public T desempilha() {

        if(estaVazia()){
            throw new ArrayIndexOutOfBoundsException("Pilha vazia");
        }
        T algo = mem[len-1];
        mem[len-1] = null;
        len--;
        return algo;
    }

    public T topo() {
        if(estaVazia()){
            throw new ArrayIndexOutOfBoundsException("Pilha vazia");
        }
        return (T)mem[len-1];
    }

    public boolean estaVazia() {
        return len == 0;
    }

    public void limpa() {
       for(int i = 0; i <len; i++) {
           mem[i] = null;
       }
       len = 0;
    }
}