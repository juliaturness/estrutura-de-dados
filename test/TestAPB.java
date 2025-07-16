import esd.APB;
import esd.Lista;
import esd.ListaSequencial;
import esd.TabHash;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;
import java.util.*;

class TestAPB {

    static APB<Integer> gera_arvore() {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }
        return arv;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criar uma árvore")
    void cria() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();

        assert(arv.esta_vazia());
        assert(arv.tamanho() == 0);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa adicionar valores")
    void adiciona() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
            assert(!arv.esta_vazia());
            assert(arv.tamanho() == j+1);
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa procurar valor")
    void obtem() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        for (int j=0; j < vals.length; j++) {
            assert(arv.procura(vals[j]) != null);
        }
        assert(arv.procura(100) == null);
        assert(arv.procura(14) == null);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa listar em ordem")
    void emOrdem() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        Arrays.sort(vals);
        ListaSequencial l = arv.emOrdem();
        assert(l.comprimento() == vals.length);

        for (int j=0; j < vals.length; j++) {
            Integer valor_em_ordem = (Integer)l.obtem(j);
            assert(vals[j] == valor_em_ordem.intValue());
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa listar pre ordem")
    void preOrdem() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        ListaSequencial l = arv.preOrdem();
        assert(l.comprimento() == vals.length);
        int[] vals_pre_ordem = {7,4,3,1,2,5,6,11,9,8,10,12,15};

        for (int j=0; j < vals_pre_ordem.length; j++) {
            Integer valor_pre_ordem = (Integer)l.obtem(j);
            assert(vals_pre_ordem[j] == valor_pre_ordem.intValue());
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa listar pos ordem")
    void posOrdem() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        ListaSequencial l = arv.posOrdem();
        assert(l.comprimento() == vals.length);
        int[] vals_pos_ordem = {2,1,3,6,5,4,8,10,9,15,12,11,7};

        for (int j=0; j < vals_pos_ordem.length; j++) {
            Integer valor_pos_ordem = (Integer)l.obtem(j);
            assert(vals_pos_ordem[j] == valor_pos_ordem.intValue());
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa listar em largura")
    void emLargura() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        ListaSequencial l = arv.emLargura();
        assert(l.comprimento() == vals.length);
        int[] vals_em_largura = {7,4,11,3,5,9,12,1,6,8,10,15,2};

        for (int j=0; j < vals_em_largura.length; j++) {
            Integer valor_em_largura = (Integer)l.obtem(j);
            assert(vals_em_largura[j] == valor_em_largura.intValue());
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter menor valor")
    void menor() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        assert(arv.menor().intValue() == 1);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter maior valor")
    void maior() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        assert(arv.maior().intValue() == 15);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter menor valor que")
    void menor_que() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        assert(arv.menor_que(5).intValue() == 5);
        assert(arv.menor_que(14).intValue() == 12);
        assert(arv.menor_que(20).intValue() == 15);
        assert(arv.menor_que(0) == null);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter maior valor que")
    void maior_que() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        assert(arv.maior_que(5).intValue() == 5);
        assert(arv.maior_que(14).intValue() == 15);
        assert(arv.maior_que(0).intValue() == 1);
        assert(arv.maior_que(17) == null);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter maiores que")
    void maiores_que() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        ListaSequencial l = arv.maiores_que(6);

        int[] maiores = {6, 7, 8, 9, 10, 11, 12, 15};
        assert(l.comprimento() == maiores.length);
        for (int j=0; j < maiores.length; j++) {
            assert(l.procura(maiores[j]) != -1);
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter menores que")
    void menores_que() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        ListaSequencial l = arv.menores_que(9);

        int[] menores = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assert(l.comprimento() == menores.length);
        for (int j=0; j < menores.length; j++) {
            assert(l.procura(menores[j]) != -1);
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter faixa de valores")
    void faixa() throws InterruptedException, IOException {
        APB<Integer> arv = new APB<>();
        Random rand = new Random();

        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }

        ListaSequencial l = arv.faixa(5, 13);

        int[] faixa = {5, 6, 7, 8, 9, 10, 11, 12};
        assert(l.comprimento() == faixa.length);
        for (int j=0; j < faixa.length; j++) {
            assert(l.procura(faixa[j]) != -1);
        }
    }
}