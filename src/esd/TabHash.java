package esd;

import java.util.Objects;
import java.util.Random;

/**
 * Implementação de uma Tabela Hash genérica com tratamento de colisão por encadeamento
 *
 * @param <K> Tipo das chaves
 * @param <V> Tipo dos valores
 */
public class TabHash<K, V> {

    private ListaSequencial<ListaSequencial<Par>> tabela;
    private int capacidade;
    private int tamanho;

    private static final int CAPACIDADE_INICIAL = 11;
    private static final double FATOR_CARGA = 0.75;

    /**
     * Classe interna representando um par chave-valor
     */
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Par par = (Par) o;
            return Objects.equals(chave, par.chave);
        }

        @Override
        public int hashCode() {
            return Objects.hash(chave);
        }
    }

    /**
     * Construtor: inicializa a tabela hash com listas encadeadas vazias
     */
    @SuppressWarnings("unchecked")
    public TabHash() {
        this.capacidade = CAPACIDADE_INICIAL;
        this.tabela = new ListaSequencial<>();
        this.tamanho = 0;

        for (int i = 0; i < capacidade; i++) {
            tabela.adiciona(new ListaSequencial<Par>());
        }
    }

    /**
     * Função de hash para distribuir chaves pela tabela
     */
    private int hash(K chave) {
        return (chave.hashCode() & 0x7FFFFFFF) % capacidade;
    }

    /**
     * Redimensiona a tabela ao ultrapassar o fator de carga
     */
    private void redimensiona() {
        int novaCapacidade = capacidade * 2;
        ListaSequencial<ListaSequencial<Par>> novaTabela = new ListaSequencial<>();

        for (int i = 0; i < novaCapacidade; i++) {
            novaTabela.adiciona(new ListaSequencial<Par>());
        }

        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                Par par = lista.obtem(j);
                int novoIndice = (par.obtemChave().hashCode() & 0x7FFFFFFF) % novaCapacidade;
                novaTabela.obtem(novoIndice).adiciona(par);
            }
        }

        this.tabela = novaTabela;
        this.capacidade = novaCapacidade;
    }

    /**
     * Adiciona ou atualiza um par chave-valor
     */
    public void adiciona(K chave, V valor) {
        if ((double) tamanho / capacidade >= FATOR_CARGA) {
            redimensiona();
        }

        int indice = hash(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);
        Par novoPar = new Par(chave, valor);

        int posicao = lista.procura(novoPar);
        if (posicao != -1) {
            lista.obtem(posicao).defineValor(valor);
        } else {
            lista.adiciona(novoPar);
            tamanho++;
        }
    }

    /**
     * Retorna o valor associado à chave ou lança exceção se não encontrado
     */
    public V obtem(K chave) {
        int indice = hash(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);
        Par procurado = new Par(chave, null);

        int posicao = lista.procura(procurado);
        if (posicao != -1) {
            return lista.obtem(posicao).obtemValor();
        }

        throw new IndexOutOfBoundsException("Chave não encontrada.");
    }

    /**
     * Remove um par pela chave
     */
    public void remove(K chave) {
        int indice = hash(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);
        Par procurado = new Par(chave, null);

        int posicao = lista.procura(procurado);
        if (posicao != -1) {
            lista.remove(posicao);
            tamanho--;
        }
    }

    /**
     * Verifica se uma chave está presente na tabela
     */
    public boolean contem(K chave) {
        int indice = hash(chave);
        ListaSequencial<Par> lista = tabela.obtem(indice);
        return lista.procura(new Par(chave, null)) != -1;
    }

    /**
     * Obtém o valor ou um valor padrão se a chave não existir
     */
    public V obtem_ou_default(K chave, V defval) {
        try {
            return obtem(chave);
        } catch (IndexOutOfBoundsException e) {
            return defval;
        }
    }

    /**
     * Retorna o número de elementos armazenados
     */
    public int comprimento() {
        return tamanho;
    }

    /**
     * Verifica se a tabela está vazia
     */
    public boolean esta_vazia() {
        return tamanho == 0;
    }

    /**
     * Retorna todas as chaves armazenadas
     */
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

    /**
     * Retorna todos os valores armazenados
     */
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

    /**
     * Retorna todos os pares chave-valor armazenados
     */
    public ListaSequencial<Par> items() {
        ListaSequencial<Par> itens = new ListaSequencial<>();

        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                itens.adiciona(lista.obtem(j));
            }
        }

        return itens;
    }

    // Método para clonar a tabela hash
    public TabHash<K, V> clona() {
        TabHash<K, V> novaTabela = new TabHash<>();
        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                Par par = lista.obtem(j);
                novaTabela.adiciona(par.obtemChave(), par.obtemValor());
            }
        }
        return novaTabela;
    }
    /**
     * Inverte a ordem dos elementos na tabela hash
     */
    public void inverte() {
        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            lista.inverte();  // Supondo que a classe ListaSequencial tenha o método 'inverte'
        }
    }

    /**
     * Embaralha os elementos na tabela hash
     */
    public void embaralha() {
        Random rand = new Random();

        for (int i = 0; i < capacidade; i++) {
            ListaSequencial<Par> lista = tabela.obtem(i);
            lista.embaralha();  // Supondo que a classe ListaSequencial tenha o método 'embaralha'
        }
    }

}