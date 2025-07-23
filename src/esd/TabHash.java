package esd;

public class TabHash<K, V> {

    public class Par {
        private K chave;
        private V valor;

        public Par(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K obtemChave() {
            return chave;
        }

        public V obtemValor() {
            return valor;
        }

        public void defineValor(V valor) {
            this.valor = valor;
        }

        @Override
        public String toString() {
            return "(" + chave + ", " + valor + ")";
        }
    }

    private ListaSequencial<ListaSequencial<Par>> tabela;
    private int tamanho;
    private int capacidade;
    private static final double FATOR_CARGA = 0.75;
    private static final int CAPACIDADE_INICIAL = 16;

    @SuppressWarnings("unchecked")
    public TabHash() {
        this.capacidade = CAPACIDADE_INICIAL;
        this.tabela = new ListaSequencial<>();
        this.tamanho = 0;

        for (int i = 0; i < capacidade; i++) {
            tabela.adiciona(new ListaSequencial<Par>());
        }
    }

    private int obtemIndice(K chave) {
        if (chave == null) {
            return 0;
        }
        return Math.abs(chave.hashCode()) % capacidade;
    }


    @SuppressWarnings("unchecked")
    private void rehash() {
        ListaSequencial<ListaSequencial<Par>> tabelaAntiga = tabela;
        int capacidadeAntiga = capacidade;

        // Dobra a capacidade
        capacidade = capacidade * 2;
        tabela = new ListaSequencial<>();
        tamanho = 0;

        // Inicializa nova tabela
        for (int i = 0; i < capacidade; i++) {
            tabela.adiciona(new ListaSequencial<Par>());
        }

        // Reinsere todos os elementos
        for (int i = 0; i < capacidadeAntiga; i++) {
            ListaSequencial<Par> lista = tabelaAntiga.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                Par par = lista.obtem(j);
                adiciona(par.obtemChave(), par.obtemValor());
            }
        }
    }

    public void adiciona(K chave, V valor) {
        if ((double) tamanho / capacidade >= FATOR_CARGA) {
            rehash();
        }

        int indice = obtemIndice(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);

        // Verifica se a chave já existe
        for (int i = 0; i < lista.comprimento(); i++) {
            Par par = lista.obtem(i);
            if (par.obtemChave().equals(chave)) {
                par.defineValor(valor);
                return;
            }
        }

        // Adiciona novo par
        lista.adiciona(new Par(chave, valor));
        tamanho++;
    }

    public V obtem(K chave) {
        int indice = obtemIndice(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);

        for (int i = 0; i < lista.comprimento(); i++) {
            Par par = lista.obtem(i);
            if (par.obtemChave().equals(chave)) {
                return par.obtemValor();
            }
        }

        throw new IndexOutOfBoundsException("Chave não encontrada: " + chave);
    }

    public V obtem_ou_default(K chave, V valorPadrao) {
        try {
            return obtem(chave);
        } catch (IndexOutOfBoundsException e) {
            return valorPadrao;
        }
    }

    public boolean remove(K chave) {
        int indice = obtemIndice(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);

        for (int i = 0; i < lista.comprimento(); i++) {
            Par par = lista.obtem(i);
            if (par.obtemChave().equals(chave)) {
                lista.remove(i);
                tamanho--;
                return true;
            }
        }

        return false;
    }

    public boolean contem(K chave) {
        try {
            obtem(chave);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean esta_vazia() {
        return tamanho == 0;
    }

    public int comprimento() {
        return tamanho;
    }

    public ListaSequencial<K> chaves() {
        ListaSequencial<K> chaves = new ListaSequencial<>();

        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                chaves.adiciona(lista.obtem(j).obtemChave());
            }
        }

        return chaves;
    }

    public ListaSequencial<V> valores() {
        ListaSequencial<V> valores = new ListaSequencial<>();

        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                valores.adiciona(lista.obtem(j).obtemValor());
            }
        }

        return valores;
    }

    public ListaSequencial<Par> items() {
        ListaSequencial<Par> items = new ListaSequencial<>();

        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                items.adiciona(lista.obtem(j));
            }
        }

        return items;
    }

    public void limpa() {
        for (int i = 0; i < capacidade; i++) {
            tabela.obtem(i).limpa();
        }
        tamanho = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TabHash {\n");

        for (int i = 0; i < capacidade; i++) {
            sb.append("  [").append(i).append("]: ");
            ListaSequencial<Par> lista = tabela.obtem(i);
            if (lista.esta_vazia()) {
                sb.append("∅");
            } else {
                sb.append(lista.toString());
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }

}
