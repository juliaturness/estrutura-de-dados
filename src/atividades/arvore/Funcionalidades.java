package atividades.arvore;

import esd.*;

public class Funcionalidades<T extends Comparable<T>> {

    public class Pair<A, B> {
        public A primeiro;
        public B segundo;

        public Pair(A primeiro, B segundo) {
            this.primeiro = primeiro;
            this.segundo = segundo;
        }
    }


    public T antecessor(APB<T> arvore, T val) {
        if (arvore == null || arvore.esta_vazia()) return null;
        return arvore.menor_que(val);
    }

    public T sucessor(APB<T> arvore, T val) {
        if (arvore == null || arvore.esta_vazia()) return null;
        return arvore.maior_que(val);
    }

    public APB<T> clonarArvore(APB<T> arvoreOriginal) {
        APB<T> novaArvore = new APB<>();
        if (arvoreOriginal == null || arvoreOriginal.esta_vazia()) return novaArvore;

        ListaSequencial<T> elementos = arvoreOriginal.emOrdem();
        for (int i = 0; i < elementos.comprimento(); i++) {
            novaArvore.adiciona(elementos.obtem(i));
        }

        return novaArvore;
    }

    public boolean compara(APB<T> arvore1, APB<T> arvore2) {
        if (arvore1 == null && arvore2 == null) return true;
        if (arvore1 == null || arvore2 == null) return false;

        ListaSequencial<T> elementos1 = arvore1.emOrdem();
        ListaSequencial<T> elementos2 = arvore2.emOrdem();

        if (elementos1.comprimento() != elementos2.comprimento()) return false;

        for (int i = 0; i < elementos1.comprimento(); i++) {
            if (elementos1.obtem(i).compareTo(elementos2.obtem(i)) != 0) return false;
        }

        return true;
    }

    public APB<T> criarArvoreDesbalanceadaCrescente(ListaSequencial<T> elementos) {
        APB<T> arvore = new APB<>();

        // Inserção simples com seleção do menor a cada passo
        ListaSequencial<T> copia = copiarLista(elementos);

        while (copia.comprimento() > 0) {
            int menorIndice = 0;
            T menorValor = copia.obtem(0);

            for (int i = 1; i < copia.comprimento(); i++) {
                T atual = copia.obtem(i);
                if (atual.compareTo(menorValor) < 0) {
                    menorValor = atual;
                    menorIndice = i;
                }
            }

            arvore.adiciona(menorValor);
            copia.remove(menorIndice);
        }

        return arvore;
    }

    public APB<T> criarArvoreDesbalanceadaDecrescente(ListaSequencial<T> elementos) {
        APB<T> arvore = new APB<>();

        // Inserção simples com seleção do maior a cada passo
        ListaSequencial<T> copia = copiarLista(elementos);

        while (copia.comprimento() > 0) {
            int maiorIndice = 0;
            T maiorValor = copia.obtem(0);

            for (int i = 1; i < copia.comprimento(); i++) {
                T atual = copia.obtem(i);
                if (atual.compareTo(maiorValor) > 0) {
                    maiorValor = atual;
                    maiorIndice = i;
                }
            }

            arvore.adiciona(maiorValor);
            copia.remove(maiorIndice);
        }

        return arvore;
    }

    public void insereSubArvore(APB<T> arvorePrincipal, APB<T> subArvore) {
        if (arvorePrincipal == null || subArvore == null || subArvore.esta_vazia()) return;

        ListaSequencial<T> elementos = subArvore.emOrdem();
        for (int i = 0; i < elementos.comprimento(); i++) {
            arvorePrincipal.adiciona(elementos.obtem(i));
        }
    }

    public APB<T> mesclarArvores(APB<T> arvore1, APB<T> arvore2) {
        APB<T> nova = new APB<>();

        if (arvore1 != null) {
            ListaSequencial<T> lista1 = arvore1.emOrdem();
            for (int i = 0; i < lista1.comprimento(); i++) {
                nova.adiciona(lista1.obtem(i));
            }
        }

        if (arvore2 != null) {
            ListaSequencial<T> lista2 = arvore2.emOrdem();
            for (int i = 0; i < lista2.comprimento(); i++) {
                nova.adiciona(lista2.obtem(i));
            }
        }

        return nova;
    }

    public void removeFaixa(APB<T> arvore, T de, T ate) {
        if (arvore == null || arvore.esta_vazia()) return;

        ListaSequencial<T> lista = arvore.faixa(de, ate);
        for (int i = 0; i < lista.comprimento(); i++) {
            arvore.remove(lista.obtem(i));
        }
    }

    public Pair<APB<T>, APB<T>> separaArvore(APB<T> arvoreOriginal, T divisor) {
        APB<T> menoresOuIguais = new APB<>();
        APB<T> maiores = new APB<>();

        if (arvoreOriginal == null || arvoreOriginal.esta_vazia()) {
            return new Pair<>(menoresOuIguais, maiores);
        }

        ListaSequencial<T> elementos = arvoreOriginal.emOrdem();
        for (int i = 0; i < elementos.comprimento(); i++) {
            T val = elementos.obtem(i);
            if (val.compareTo(divisor) <= 0) {
                menoresOuIguais.adiciona(val);
            } else {
                maiores.adiciona(val);
            }
        }

        return new Pair<>(menoresOuIguais, maiores);
    }

    // Utilitário para copiar uma ListaSequencial
    private ListaSequencial<T> copiarLista(ListaSequencial<T> original) {
        ListaSequencial<T> copia = new ListaSequencial<>();
        for (int i = 0; i < original.comprimento(); i++) {
            copia.adiciona(original.obtem(i));
        }
        return copia;
    }
}
