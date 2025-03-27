import esd.Fila;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.IntegerConversion;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;

class TestFila {

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criação de fila: deve estar vazia ao ser criada")
    void criaFila() throws InterruptedException, IOException {
        Fila q = new Fila();
        assert(q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores")
    void adiciona() throws InterruptedException, IOException {
        Fila q = new Fila();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            assert(q.length() == j+1);
        }

        assert(! q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores e acessar frente")
    void frente() throws InterruptedException, IOException {
        Fila q = new Fila();

        var primeiro = Integer.valueOf(0);
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            var dado = (Integer)q.frente();
            assert(dado.equals(primeiro));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores a partir de um posição diferente do início da área de armazenamento, para evidenciar ser fila circular")
    void adiciona_circular() throws InterruptedException, IOException {
        Fila q = new Fila();

        q.adiciona(Integer.valueOf(3));
        q.adiciona(Integer.valueOf(3));
        q.remove();
        q.remove();
        
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            assert(q.length() == j+1);
        }

        for (int j=0; j < q.capacidade(); j++) {
            assert(! q.estaVazia());
            var val = (Integer)q.remove();
            assert(val.equals(Integer.valueOf(j)));
        }

        assert(q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa desenfileirar valores")
    void remove() throws InterruptedException, IOException {
        Fila q = new Fila();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        for (int j=0; j < q.capacidade(); j++) {
            assert(! q.estaVazia());
            var val = (Integer)q.remove();
            assert(val.equals(Integer.valueOf(j)));
        }

        assert(q.estaVazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa acessar frente de fila vazia")
    void frente_vazia() throws InterruptedException, IOException {
        Fila q = new Fila();

        assertThrows(IndexOutOfBoundsException.class, () -> q.frente(), "acessar frente de fila vazia deve disparar uma exceção IndexOutOfBoundsException");
    }    

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar em fila cheia")
    void fila_cheia() throws InterruptedException, IOException {
        Fila q = new Fila();
        int ini_cap = q.capacidade();

        for (int j=0; j < ini_cap*2; j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        assert(ini_cap < q.capacidade());

        for (int j=0; j < ini_cap*2; j++) {
            var val = (Integer)q.remove();
            assert(val.equals(Integer.valueOf(j)));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa desenfileirar de fila vazia")
    void fila_vazia() throws InterruptedException, IOException {
        Fila q = new Fila();

        assertThrows(IndexOutOfBoundsException.class, () -> q.remove(), "desenfileirar de fila vazia deve disparar uma exceção IndexOutOfBoundsException");
    }        
}
