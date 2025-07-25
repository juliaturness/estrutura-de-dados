package esd;

// conjunto implementado usando tabela hash
// armazena elementos unicos sem duplicatas
public class Conjunto<T> {

    // usa tabela hash internamente : chaves sao os elementos, valores sao sempre true
    private TabHash<T, Boolean> tab;

    // cria conjunto vazio
    public Conjunto() {
        tab = new TabHash<>();
    }

    // adiciona elemento ao conjunto
    // se ja existe, n faz nada (conjuntos n tem duplicatas)
    public void adiciona(T elemento) {
        tab.adiciona(elemento, true);
    }

    // remove elemento do conjunto
    // retorna true se removeu, false se n existia
    public boolean remove(T elemento) {
        try {
            tab.remove(elemento);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    // verifica se elemento ta no conjunto
    public boolean contem(T elemento) {
        return tab.contem(elemento);
    }

    // verifica se conjunto ta vazio
    public boolean esta_vazio() {
        return tab.esta_vazia();
    }

    // retorna quantos elementos tem no conjunto
    public int comprimento() {
        return tab.comprimento();
    }

    // retorna lista com todos os elementos do conjunto
    public ListaSequencial<T> elementos() {
        return tab.chaves();
    }

    // remove todos os elementos
    public void limpa() {
        tab.limpa();
    }

    // cria novo conjunto com a uniao deste com outro
    // resultado contem elementos q estao em qualquer um dos dois
    public Conjunto<T> uniao(Conjunto<T> outro) {
        Conjunto<T> resultado = new Conjunto<>();
        // adiciona todos os elementos deste conjunto
        ListaSequencial<T> meus = elementos();
        for (int i = 0; i < meus.comprimento(); i++)
            resultado.adiciona(meus.obtem(i));
        // adiciona todos os elementos do outro conjunto
        ListaSequencial<T> outros = outro.elementos();
        for (int i = 0; i < outros.comprimento(); i++)
            resultado.adiciona(outros.obtem(i));
        return resultado;
    }

    // cria novo conjunto com a intersecao deste com outro
    // resultado contem elementos q estao nos dois conjuntos
    public Conjunto<T> intersecao(Conjunto<T> outro) {
        Conjunto<T> resultado = new Conjunto<>();
        // percorre elementos deste conjunto
        ListaSequencial<T> meus = elementos();
        for (int i = 0; i < meus.comprimento(); i++) {
            T elemento = meus.obtem(i);
            // se elemento ta no outro conjunto tambem, adiciona no resultado
            if (outro.contem(elemento)) resultado.adiciona(elemento);
        }
        return resultado;
    }

    // cria novo conjunto com a diferenca deste menos outro
    // resultado contem elementos q estao neste mas n no outro
    public Conjunto<T> diferenca(Conjunto<T> outro) {
        Conjunto<T> resultado = new Conjunto<>();
        // percorre elementos deste conjunto
        ListaSequencial<T> meus = elementos();
        for (int i = 0; i < meus.comprimento(); i++) {
            T elemento = meus.obtem(i);
            // se elemento n ta no outro conjunto, adiciona no resultado
            if (!outro.contem(elemento)) resultado.adiciona(elemento);
        }
        return resultado;
    }

    // verifica se este conjunto é subconjunto do outro
    // retorna true se todos os elementos deste estao no outro
    public boolean ehSubconjunto(Conjunto<T> outro) {
        ListaSequencial<T> meus = elementos();
        for (int i = 0; i < meus.comprimento(); i++) {
            T elemento = meus.obtem(i);
            // se algum elemento n ta no outro, n é subconjunto
            if (!outro.contem(elemento)) return false;
        }
        return true;
    }

    // verifica se este conjunto é superconjunto do outro
    // retorna true se contem todos os elementos do outro
    public boolean ehSuperconjunto(Conjunto<T> outro) {
        return outro.ehSubconjunto(this);
    }

    // verifica se dois conjuntos sao iguais
    // sao iguais se tem os msmos elementos
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conjunto<T> outro = (Conjunto<T>) obj;
        // se tem tamanhos diferentes, n sao iguais
        if (comprimento() != outro.comprimento()) return false;
        // verifica se todos os elementos deste estao no outro
        return ehSubconjunto(outro);
    }

    // cria copia do conjunto
    public Conjunto<T> clona() {
        Conjunto<T> copia = new Conjunto<>();
        ListaSequencial<T> meus = elementos();
        for (int i = 0; i < meus.comprimento(); i++)
            copia.adiciona(meus.obtem(i));
        return copia;
    }

    // verifica se dois conjuntos sao disjuntos (nao tem elementos em comum)
    public boolean ehDisjunto(Conjunto<T> outro) {
        ListaSequencial<T> meus = elementos();
        for (int i = 0; i < meus.comprimento(); i++) {
            T elemento = meus.obtem(i);
            if (outro.contem(elemento)) return false;
        }
        return true;
    }

    // converte para array
    public Object[] paraArray() {
        ListaSequencial<T> meus = elementos();
        Object[] array = new Object[meus.comprimento()];
        for (int i = 0; i < meus.comprimento(); i++)
            array[i] = meus.obtem(i);
        return array;
    }
}
