import esd.Lista;
import esd.ListaSequencial;
import esd.TabHash;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Random;

class TestTabHash {

    final int cap = 11;

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criar uma tabela")
    void cria() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        assert(tab.esta_vazia());
        assert(tab.comprimento() == 0);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa adicionar chaves e valores")
    void adiciona() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
            assert(!tab.esta_vazia());
            assert(tab.comprimento() == j+1);
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa adicionar chaves e valores e forçar rehashing")
    void adiciona_muitos() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        double t0 = System.currentTimeMillis();
        for (int j=0; j < 100000; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
            assert(!tab.esta_vazia());
            assert(tab.comprimento() == j+1);
            assert(tab.obtem(j).equals(val));
        }
        assert((System.currentTimeMillis() - t0) < 1000);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter valor associado a chave")
    void obtem() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {tab.obtem(1);});
        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> {tab.obtem(100);});
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa substituir valor associado a chave")
    void substitui() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
        }
        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j+100);
            tab.adiciona(j, val);
            assert(tab.obtem(j).equals(val));

        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter valor de chave inexistente, obtendo então default")
    void obtem_ou_default() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        assert(tab.obtem_ou_default(1, "vazia").equals("vazia"));

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
        }
        assert(tab.obtem_ou_default(100, "inexistente").equals("inexistente"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover chave")
    void remove() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
        }

        for (int j=0; j < 7; j++) {
            int k = j;
            tab.remove(j);
            assertThrows(IndexOutOfBoundsException.class, () -> {tab.obtem(k);});
        }

        assert(tab.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa verificar se chave existe")
    void contem() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();

        assert(! tab.contem(1));
        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
            assert(tab.contem(j));
        }

        assert(!tab.contem(100));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter lista de chaves")
    void chaves() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();
        HashSet<Integer> c1 = new HashSet<>();
        HashSet<Integer> c2 = new HashSet<>();
        ListaSequencial l = tab.chaves();
        assert(l.esta_vazia());

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
            c1.add(j);
        }
        l = tab.chaves();
        assert(l.comprimento() == 7);
        for (int pos=0; pos < l.comprimento(); pos++) {
            Integer k = (Integer)l.obtem(pos);
            c2.add(k);
        }
        assert(c1.equals(c2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter lista de valores")
    void valores() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();
        HashSet<String> c1 = new HashSet<>();
        HashSet<String> c2 = new HashSet<>();
        ListaSequencial l = tab.valores();
        assert(l.esta_vazia());

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
            c1.add(val);
        }
        l = tab.valores();
        assert(l.comprimento() == 7);
        for (int pos=0; pos < l.comprimento(); pos++) {
            String v = (String)l.obtem(pos);
            c2.add(v);
        }
        assert(c1.equals(c2));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter lista de items")
    void items() throws InterruptedException, IOException {
        TabHash<Integer, String> tab = new TabHash<>();
        HashMap<Integer, String> t1 = new HashMap<>();
        HashSet<String> c2 = new HashSet<>();
        ListaSequencial l = tab.items();
        assert(l.esta_vazia());

        for (int j=0; j < 7; j++) {
            String val = String.valueOf(j);
            tab.adiciona(j, val);
            t1.put(j, val);
        }
        l = tab.items();
        assert(l.comprimento() == 7);
        for (int pos=0; pos < l.comprimento(); pos++) {
            TabHash<Integer,String>.Par p = (TabHash<Integer,String>.Par)l.obtem(pos);
            var _val = t1.get(p.obtemChave());
            assert(_val != null);
            t1.remove(p.obtemChave());
        }
        assert(t1.isEmpty());
    }
}