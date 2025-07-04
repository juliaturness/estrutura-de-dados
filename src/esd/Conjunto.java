package esd;

/**
 * Implementação de um conjunto usando tabela hash internamente
 * @param <V> Tipo dos elementos armazenados no conjunto
 */
public class Conjunto<V> {
    private TabHash<V, Object> tab = new TabHash<>();

    /**
     * Construtor do conjunto
     */
    public Conjunto() {
        // A tabela hash é inicializada automaticamente
    }

    /**
     * Adiciona um valor ao conjunto
     * @param valor Valor a ser adicionado
     */
    public void adiciona(V valor) {
        // Usamos null como valor associado, pois só nos importa a chave
        tab.adiciona(valor, null);
    }

    /**
     * Verifica se o conjunto contém um valor
     * @param valor Valor a ser verificado
     * @return true se o valor está no conjunto, false caso contrário
     */
    public boolean contem(V valor) {
        return tab.contem(valor);
    }

    /**
     * Remove um valor do conjunto
     * @param valor Valor a ser removido
     */
    public void remove(V valor) {
        tab.remove(valor);
    }

    /**
     * Verifica se o conjunto está vazio
     * @return true se o conjunto está vazio, false caso contrário
     */
    public boolean estaVazia() {
        return tab.esta_vazia();
    }

    /**
     * Retorna o número de elementos no conjunto
     * @return Número de elementos
     */
    public int tamanho() {
        // Implementação alternativa: contar os itens não nulos na tabela
        return tab.comprimento();
    }

    /**
     * Retorna uma lista com todos os elementos do conjunto
     * @return ListaSequencial contendo os elementos
     */
    public ListaSequencial<V> elementos() {
        return tab.chaves();
    }

    /**
     * Limpa o conjunto, removendo todos os elementos
     */
    public void limpa() {
        // Implementação alternativa: criar tabela vazia
        this.tab = new TabHash<>();
    }

    @Override
    public String toString() {
        return elementos().toString();
    }
}