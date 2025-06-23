package esd;

// a classe tabhash implementa uma tabela hash (também conhecida como mapa hash ou dicionário).
// ela é genérica, aceitando um tipo para a chave (k) e um tipo para o valor (v).
// o objetivo é armazenar pares chave-valor e permitir acesso, inserção e remoção em tempo médio constante o(1).
public class TabHash<K, V> {

    // --- classe interna 'par' ---
    // representa um único par chave-valor que será armazenado na tabela.
    // sendo uma classe interna (não-estática), ela tem acesso aos tipos genéricos k e v da classe tabhash.
    public class Par {
        private K chave; // a chave usada para identificar o dado.
        private V valor; // o valor ou dado associado à chave.

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

        public void atualizaValor(V novoValor) {
            this.valor = novoValor;
        }
    }

    // --- constantes e atributos principais ---

    // capacidade inicial padrão da tabela hash se nenhuma for especificada.
    // o uso de um número primo (como 11) ajuda a distribuir melhor os hashes, reduzindo colisões.
    private static final int capacidadeInicial = 11;

    // fator de carga máximo. é a "taxa de ocupação" máxima permitida.
    // se (número de elementos / capacidade) ultrapassar 0.75, a tabela será redimensionada (aumentada).
    // isso é crucial para manter o desempenho o(1), evitando que as listas de colisão fiquem muito longas.
    private static final double cargaMaxima = 0.75;

    // a estrutura de dados principal. é um array de listas sequenciais.
    // cada posição no array é um "balde" (bucket).
    // quando ocorre uma colisão (duas chaves geram o mesmo hash), os pares são adicionados à lista naquele "balde".
    // esta técnica é chamada de "encadeamento separado" (separate chaining).
    private ListaSequencial<Par>[] tab;

    // a capacidade atual do array 'tab'.
    private int capacidade;

    // o número total de pares chave-valor armazenados na tabela.
    // manter este contador evita ter que recalcular o tamanho toda vez, tornando a operação o(1).
    private int tamanho;

    // --- construtores ---

    // construtor padrão (sem argumentos).
    // ele chama o outro construtor com a capacidade inicial padrão.
    public TabHash() {
        this(capacidadeInicial);
    }

    // construtor que cria a tabela com uma capacidade específica.
    @SuppressWarnings("unchecked") // suprime o aviso sobre a criação de um array genérico, que é uma limitação do java.
    public TabHash(int capacidade) {
        this.capacidade = capacidade;
        this.tamanho = 0;
        // 1. cria o array de "baldes" com a capacidade definida.
        this.tab = new ListaSequencial[capacidade];
        // 2. inicializa cada "balde" com uma lista vazia. passo essencial!
        for (int i = 0; i < capacidade; i++) {
            tab[i] = new ListaSequencial<>();
        }
    }

    // --- métodos internos principais (o "motor" da tabela) ---

    // a função de hash. converte a chave em um índice válido para o array 'tab'.
    private int hash(K chave) {
        // 1. `chave.hashcode()`: pega o código hash padrão do objeto chave. pode ser positivo ou negativo.
        // 2. `& 0x7fffffff`: faz uma operação bitwise and com uma máscara que força o bit de sinal a ser 0.
        //    isso garante que o resultado seja sempre um número não-negativo de forma eficiente e segura.
        // 3. `% capacidade`: calcula o resto da divisão pela capacidade, mapeando o hash para um índice de 0 a (capacidade-1).
        return (chave.hashCode() & 0x7fffffff) % capacidade;
    }

    // redimensiona a tabela quando o fator de carga é excedido. isso é chamado de "rehashing".
    @SuppressWarnings("unchecked")
    private void redimensionar(int novaCapacidade) {
        // cria uma tabela temporária, nova e maior.
        TabHash<K, V> temp = new TabHash<>(novaCapacidade);

        // itera sobre todos os elementos da tabela antiga.
        for (int i = 0; i < capacidade; i++) {
            for (int j = 0; j < tab[i].comprimento(); j++) {
                Par par = tab[i].obtem(j);
                // adiciona cada par na nova tabela. a função `adiciona` da tabela `temp`
                // usará o novo hash (com a nova capacidade) para colocar o par em sua nova posição correta.
                temp.adiciona(par.obtemChave(), par.obtemValor());
            }
        }
        // substitui os atributos da tabela atual pelos da nova tabela redimensionada.
        this.capacidade = temp.capacidade;
        this.tab = temp.tab;
        this.tamanho = temp.tamanho;
    }

    // --- métodos públicos principais ---

    // adiciona um novo par chave-valor ou atualiza o valor se a chave já existir.
    public void adiciona(K chave, V valor) {
        // 1. verifica se a tabela está muito cheia e precisa ser redimensionada antes de adicionar.
        if ((double) tamanho / capacidade >= cargaMaxima) {
            redimensionar(2 * capacidade + 1); // uma boa prática é dobrar a capacidade.
        }

        // 2. calcula o índice (balde) onde o par deve ser armazenado.
        int pos = hash(chave);
        ListaSequencial<Par> lista = tab[pos];

        // 3. procura na lista daquele balde se a chave já existe.
        for (int i = 0; i < lista.comprimento(); i++) {
            Par par = lista.obtem(i);
            if (par.obtemChave().equals(chave)) {
                // se a chave já existe, apenas atualiza o valor e termina o método.
                par.atualizaValor(valor);
                return;
            }
        }

        // 4. se o loop terminou e a chave não foi encontrada, é um novo par.
        // adiciona o novo par à lista e incrementa o contador de tamanho total.
        lista.adiciona(new Par(chave, valor));
        tamanho++;
    }

    // obtém o valor associado a uma chave.
    public V obtem(K chave) {
        // 1. encontra o balde correto usando o hash.
        int pos = hash(chave);
        ListaSequencial<Par> lista = tab[pos];

        // 2. procura linearmente na pequena lista daquele balde.
        for (int i = 0; i < lista.comprimento(); i++) {
            Par par = lista.obtem(i);
            if (par.obtemChave().equals(chave)) {
                // se encontrou, retorna o valor.
                return par.obtemValor();
            }
        }

        // 3. se a chave não foi encontrada após percorrer a lista, lança uma exceção.
        // (isso foi um requisito do arquivo de teste).
        throw new IndexOutOfBoundsException("chave não encontrada na tabela: " + chave);
    }

    // remove um par chave-valor da tabela.
    public void remove(K chave) {
        int pos = hash(chave);
        ListaSequencial<Par> lista = tab[pos];

        for (int i = 0; i < lista.comprimento(); i++) {
            if (lista.obtem(i).obtemChave().equals(chave)) {
                // se encontrou a chave, remove o par da lista...
                lista.remove(i);
                // ...decrementa o tamanho total...
                tamanho--;
                // ...e termina o método.
                return;
            }
        }
    }

    // --- métodos auxiliares e de conveniência ---

    // verifica se uma chave existe na tabela.
    public boolean contem(K chave) {
        try {
            // reutiliza a lógica do método `obtem`.
            // se `obtem` funcionar, a chave existe e retorna true.
            obtem(chave);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // se `obtem` lançar uma exceção, a chave não existe e retorna false.
            return false;
        }
    }


    // retorna o número total de elementos na tabela. operação o(1) graças ao atributo 'tamanho'.
    public int comprimento() {
        return tamanho;
    }

    // verifica se a tabela está vazia. operação o(1).
    public boolean esta_vazia() {
        return tamanho == 0;
    }

    // tenta obter um valor. se a chave não existir, retorna um valor padrão (defval) em vez de lançar exceção.
    public V obtem_ou_default(K chave, V defval) {
        try {
            return obtem(chave);
        } catch (IndexOutOfBoundsException e) {
            return defval;
        }
    }

    // esvazia a tabela, removendo todos os elementos.
    public void limpa() {
        // itera por todos os baldes e limpa cada lista interna.
        for (int i = 0; i < capacidade; i++) {
            tab[i].limpa();
        }
        // reseta o contador de tamanho.
        tamanho = 0;
    }

    // --- métodos de visualização (views) ---

    // retorna uma lista com todas as chaves da tabela.
    public ListaSequencial<K> chaves() {
        ListaSequencial<K> listaChaves = new ListaSequencial<>();
        // itera por cada balde...
        for (int i = 0; i < capacidade; i++) {
            // ...e por cada par dentro da lista daquele balde...
            for (int j = 0; j < tab[i].comprimento(); j++) {
                // ...adicionando a chave à lista de resultados.
                listaChaves.adiciona(tab[i].obtem(j).obtemChave());
            }
        }
        return listaChaves;
    }

    // retorna uma lista com todos os valores da tabela.
    public ListaSequencial<V> valores() {
        ListaSequencial<V> listaValores = new ListaSequencial<>();
        for (int i = 0; i < capacidade; i++) {
            for (int j = 0; j < tab[i].comprimento(); j++) {
                listaValores.adiciona(tab[i].obtem(j).obtemValor());
            }
        }
        return listaValores;
    }

    // retorna uma lista com todos os pares (objetos par) da tabela.
    public ListaSequencial<Par> items() {
        ListaSequencial<Par> listaItems = new ListaSequencial<>();
        for (int i = 0; i < capacidade; i++) {
            for (int j = 0; j < tab[i].comprimento(); j++) {
                listaItems.adiciona(tab[i].obtem(j));
            }
        }
        return listaItems;
    }
}