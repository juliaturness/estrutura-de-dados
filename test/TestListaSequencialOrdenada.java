import esd.unidade1.ListaSequencialOrdenada;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestListaSequencialOrdenada {

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criação de lista: deve estar vazia ao ser criada")
    void criaListaSequencialOrdenada() throws InterruptedException, IOException {
        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>();

        assert(q.esta_vazia());
        assert(q.comprimento() == 0);
        assert(q.capacidade() > 0);
    }

    // testa se uma lista está ordenada
    boolean esta_ordenada(ListaSequencialOrdenada<Integer> q) {
        Integer prev = (Integer)q.obtem(0);
        for (int j=1; j < q.comprimento(); j++) {
            var val = q.obtem(j);
            if (val.compareTo(prev) < 0) {
                return false;
            }
            prev = val;
        }

        return true;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa inserir e obter valores")
    void insere() throws InterruptedException, IOException {
        // cria uma lista de Integer
        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>();

        Random rand = new Random();
        for (int j=0; j < q.capacidade(); j++) {
            // adiciona o número à lista e confere se seu comprimento foi incrementado
            q.insere(rand.nextInt(100));
            assert(q.comprimento() == j+1);
        }

        // ao final, verifica se lista está ordenada
        assert(esta_ordenada(q));

        assert(! q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover valores")
    void remove() throws InterruptedException, IOException {
        // cria uma lista de Integer
        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>();

        Random rand = new Random();
        for (int j=0; j < q.capacidade(); j++) {
            // adiciona o número à lista e confere se seu comprimento foi incrementado
            q.insere(rand.nextInt(100));
            assert(q.comprimento() == j+1);
        }

        // remove valor do início, do meio e do fim
        q.remove(q.obtem(0));
        assert(esta_ordenada(q));
        q.remove(q.obtem(q.comprimento()/2));
        assert(esta_ordenada(q));
        q.remove(q.obtem(q.comprimento()-1));
        assert(esta_ordenada(q));

        // não pode estar vazia ... removeu menos valores do que inseriu
        assert(! q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa procurar valores na lista")
    void procura() throws InterruptedException, IOException {
        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>();

        // procura em lista vazia
        assert(q.procura(Integer.valueOf(3)) == -1);

        // gera uma lista com valores conhecidos
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.insere(val);
        }

        // procura cada valor conhecido na lista
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            int indice = (Integer)q.procura(j);
            assert(indice == j);
        }

        // procura por um valor que não foi adicionado à lista
        assert(q.procura(Integer.valueOf(1001)) == -1);
    }

    <T extends Comparable> int busca_linear(ListaSequencialOrdenada<T> q, T elemento) {
        for (int pos=0; pos < q.comprimento(); pos++) {
            if (elemento.equals(q.obtem(pos))) {
                return pos;
            }
        }
        return -1;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa procurar valores na lista, supondo ser rápido (com busca binária)")
    void procura_rapido() throws InterruptedException, IOException {
        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>();
        Random rand = new Random();
        for (int j=0; j < 10000; j++) {
            q.insere(j*10000+rand.nextInt(9999));
        }

        // elementos que estão no meio e no final da lista
        var primeiro = q.obtem(0);
        var meio = q.obtem(q.comprimento()/2);
        var ultimo = q.obtem(q.comprimento()-1);

        // mede o tempo para algumas buscas lineares
        var t0 = System.currentTimeMillis();
        assert(busca_linear(q, primeiro) == 0);
        assert(busca_linear(q, meio) == q.comprimento()/2);
        assert(busca_linear(q, ultimo) == q.comprimento() - 1);
        long dt1 = System.currentTimeMillis() - t0;

        // mede o tempo para algumas buscas binárias
        t0 = System.currentTimeMillis();
        assert(q.procura(primeiro) == 0);
        assert(q.procura(meio) == q.comprimento()/2);
        assert(q.procura(ultimo) == q.comprimento() -1);
        long dt2 = System.currentTimeMillis() - t0;

        assert(dt2 < 4*dt1);

    }
}