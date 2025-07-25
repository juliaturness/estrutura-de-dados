import esd.Pilha;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;

class TestPilha {

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criação de pilha: deve estar vazia ao ser criada")
    void criaPilha() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();
        assert(q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa empilhar valores")
    void adiciona() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.empilha(val);
            assert(q.comprimento() == j+1);
        }

        assert(! q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa empilhar valores e acessar topo")
    void topo() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.empilha(val);
            var dado = q.topo();
            assert(dado.equals(val));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa desempilhar valores")
    void remove() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.empilha(val);
        }

        for (int j=q.capacidade()-1; j >= 0; j--) {
            assert(! q.estaVazia());
            var val = q.desempilha();
            assert(val.equals(Integer.valueOf(j)));
        }

        assert(q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa acessar topo de pilha vazia")
    void frente_vazia() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();

        assertThrows(IndexOutOfBoundsException.class, () -> q.topo(), "acessar topo da pilha vazia deve disparar uma exceção IndexOutOfBoundsException");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa empilhar em pilha cheia")
    void pilha_cheia() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();
        int ini_cap = q.capacidade();

        for (int j=0; j < ini_cap*2; j++) {
            var val = Integer.valueOf(j);
            q.empilha(val);
        }

        assert(ini_cap < q.capacidade());

        for (int j=ini_cap*2-1; j >= 0 ; j--) {
            var val = q.desempilha();
            assert(val.equals(Integer.valueOf(j)));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa desempilhar de pilha vazia")
    void fila_vazia() throws InterruptedException, IOException {
        Pilha<Integer> q = new Pilha<>();

        assertThrows(IndexOutOfBoundsException.class, () -> q.desempilha(), "desempilhar de pilha vazia deve disparar uma exceção IndexOutOfBoundsException");
    }
}