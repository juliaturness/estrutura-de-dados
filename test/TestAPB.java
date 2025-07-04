import esd.APB;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class TestAPB {

    static APB<Integer> gera_arvore() {
        APB<Integer> arv = new APB<>();
        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
        }
        return arv;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criar uma árvore")
    void cria() {
        APB<Integer> arv = new APB<>();
        assertTrue(arv.esta_vazia());
        assertEquals(0, arv.comprimento());
        assertEquals(-1, arv.altura());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa adicionar valores")
    void adiciona() {
        APB<Integer> arv = new APB<>();
        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            arv.adiciona(vals[j]);
            assertFalse(arv.esta_vazia());
            assertEquals(j+1, arv.comprimento());
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa procurar valor")
    void obtem() {
        APB<Integer> arv = gera_arvore();
        int[] vals = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 12, 15, 10};
        for (int j=0; j < vals.length; j++) {
            assertNotNull(arv.procura(vals[j]));
        }
        assertNull(arv.procura(100));
        assertNull(arv.procura(14));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter menor valor")
    void menor() {
        APB<Integer> arv = gera_arvore();
        assertEquals(1, arv.menor().intValue());

        APB<Integer> arvVazia = new APB<>();
        assertNull(arvVazia.menor());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter maior valor")
    void maior() {
        APB<Integer> arv = gera_arvore();
        assertEquals(15, arv.maior().intValue());

        APB<Integer> arvVazia = new APB<>();
        assertNull(arvVazia.maior());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter menor valor que")
    void menorQue() {
        APB<Integer> arv = gera_arvore();
        assertEquals(5, arv.menorQue(5).intValue());
        assertEquals(12, arv.menorQue(14).intValue());
        assertEquals(15, arv.menorQue(20).intValue());
        assertNull(arv.menorQue(0));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter maior valor que")
    void maiorQue() {
        APB<Integer> arv = gera_arvore();
        assertEquals(5, arv.maiorQue(5).intValue());
        assertEquals(15, arv.maiorQue(14).intValue());
        assertEquals(1, arv.maiorQue(0).intValue());
        assertNull(arv.maiorQue(17));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa limpar árvore")
    void limpa() {
        APB<Integer> arv = gera_arvore();
        arv.limpa();
        assertTrue(arv.esta_vazia());
        assertEquals(0, arv.comprimento());
        assertEquals(-1, arv.altura());
    }

//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa percurso in-order")
//    void inOrder() {
//        APB<Integer> arv = gera_arvore();
//        List<Integer> lista = arv.listeInOrder();
//        int[] esperado = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15};
//        assertEquals(esperado.length, lista.size());
//        for (int i = 0; i < esperado.length; i++) {
//            assertEquals(esperado[i], lista.get(i).intValue());
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa percurso pre-order")
//    void preOrder() {
//        APB<Integer> arv = gera_arvore();
//        List<Integer> lista = arv.listePreOrder();
//        int[] esperado = {7, 4, 3, 1, 2, 5, 6, 11, 9, 8, 10, 12, 15};
//        assertEquals(esperado.length, lista.size());
//        for (int i = 0; i < esperado.length; i++) {
//            assertEquals(esperado[i], lista.get(i).intValue());
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa percurso post-order")
//    void postOrder() {
//        APB<Integer> arv = gera_arvore();
//        List<Integer> lista = arv.listePostOrder();
//        int[] esperado = {2, 1, 3, 6, 5, 4, 8, 10, 9, 15, 12, 11, 7};
//        assertEquals(esperado.length, lista.size());
//        for (int i = 0; i < esperado.length; i++) {
//            assertEquals(esperado[i], lista.get(i).intValue());
//        }
//    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa altura da árvore")
    void altura() {
        APB<Integer> arv = gera_arvore();
        assertEquals(4, arv.altura());

        APB<Integer> arvVazia = new APB<>();
        assertEquals(-1, arvVazia.altura());

        APB<Integer> arvUnico = new APB<>();
        arvUnico.adiciona(1);
        assertEquals(0, arvUnico.altura());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa comprimento da árvore")
    void comprimento() {
        APB<Integer> arv = gera_arvore();
        assertEquals(13, arv.comprimento());

        APB<Integer> arvVazia = new APB<>();
        assertEquals(0, arvVazia.comprimento());
    }
}