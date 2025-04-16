import esd.Deque;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.IntegerConversion;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;

class TestDeque {

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criação de Deque: deve estar vazia ao ser criada")
    void criaDeque() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();
        assert(q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa anexar valores")
    void adiciona() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            assert(q.comprimento() == j+1);
        }

        assert(! q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa inserir valores")
    void insere() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.insere(val);
            assert(q.comprimento() == j+1);
        }

        assert(! q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores e acessar acessa_inicio")
    void acessa_inicio() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        var primeiro = Integer.valueOf(0);
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            var dado = q.acessa_inicio();
            assert(dado.equals(primeiro));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores e acessar acessa_final")
    void acessa_final() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            var dado = q.acessa_final();
            assert(dado.equals(val));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores e acessar posições quaisquer")
    void acessa_qualquer() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            var dado = q.acessa(j);
            assert(dado.equals(val));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar valores a partir de um posição diferente do início da área de armazenamento, para evidenciar se Deque foi feito como fila circular")
    void adiciona_circular() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        q.adiciona(Integer.valueOf(3));
        q.adiciona(Integer.valueOf(3));
        q.extrai_inicio();
        q.extrai_inicio();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            assert(q.comprimento() == j+1);
        }

        for (int j=0; j < q.capacidade(); j++) {
            assert(! q.esta_vazia());
            var val = q.extrai_inicio();
            assert(val.equals(Integer.valueOf(j)));
        }

        assert(q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa retirar valores do inicio")
    void remove_inicio() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        for (int j=0; j < q.capacidade(); j++) {
            assert(! q.esta_vazia());
            var val = q.extrai_inicio();
            assert(val.equals(Integer.valueOf(j)));
        }

        assert(q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa retirar valores do final")
    void remove_final() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        for (int j=0; j < q.capacidade(); j++) {
            assert(! q.esta_vazia());
            var val = q.extrai_final();
            assert(val.equals(Integer.valueOf(q.capacidade() - (j + 1))));
        }

        assert(q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa acessar acessa_inicio de Deque vazia")
    void acessa_inicio_vazia() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        assertThrows(IndexOutOfBoundsException.class, () -> q.acessa_inicio(), "acessar acessa_inicio de Deque vazia deve disparar uma exceção IndexOutOfBoundsException");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa acessar acessa_final de Deque vazia")
    void acessa_final_vazia() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        assertThrows(IndexOutOfBoundsException.class, () -> q.acessa_inicio(), "acessar acessa_final de Deque vazia deve disparar uma exceção IndexOutOfBoundsException");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa acessar acessa com posição inválida")
    void acessa_invalido() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        assertThrows(IndexOutOfBoundsException.class, () -> q.acessa(0), "acessar posição inválida de Deque deve disparar uma exceção IndexOutOfBoundsException");

        int len = q.capacidade()/2;
        for (int j=0; j < len; j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }


        assertThrows(IndexOutOfBoundsException.class, () -> q.acessa(len), "acessar posição inválida de Deque deve disparar uma exceção IndexOutOfBoundsException");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa enfileirar em Deque cheia")
    void Deque_cheia() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();
        int ini_cap = q.capacidade();

        for (int j=0; j < ini_cap*2; j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        assert(ini_cap < q.capacidade());

        for (int j=0; j < ini_cap*2; j++) {
            var val = q.extrai_inicio();
            assert(val.equals(Integer.valueOf(j)));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa desenfileirar de Deque vazia")
    void Deque_vazia() throws InterruptedException, IOException {
        Deque<Integer> q = new Deque<>();

        assertThrows(IndexOutOfBoundsException.class, () -> q.extrai_inicio(), "extrai_inicio de Deque vazia deve disparar uma exceção IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class, () -> q.extrai_final(), "extrai_final de Deque vazia deve disparar uma exceção IndexOutOfBoundsException");
    }
}