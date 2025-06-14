import esd.unidade1.ListaSequencialSimples;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestListaSequencialSimples {

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criação de lista: deve estar vazia ao ser criada")
    void criaListaSequencialSimples() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();
        assert(q.esta_vazia());
        assert(q.comprimento() == 0);
        assert(q.capacidade() > 0);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa obter valor de posição inválida")
    void obtem_invalido() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        // acessar uma posição em lista vazia
        assertThrows(IndexOutOfBoundsException.class, () -> {q.obtem(0);});
        q.adiciona(Integer.valueOf(10));

        // acessar uma posição negativa
        assertThrows(IndexOutOfBoundsException.class, () -> {q.obtem(-1);});
        // acessar uma posição igual ao comprimento da lista
        assertThrows(IndexOutOfBoundsException.class, () -> {q.obtem(1);});
        // acessar uma posição maior que comprimento da lista
        assertThrows(IndexOutOfBoundsException.class, () -> {q.obtem(7);});
    }

    ListaSequencialSimples gera_lista() {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        return q;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa anexar e obter valores")
    void adiciona() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        // gera uma lista com inteiros crescentes
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
            // verifica se comprimento correspodne à quantidade de valores adicionados
            assert(q.comprimento() == j+1);
        }

        // verifica se a lista contém os valoers na ordem em que foram adicionados
        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            assert(val.equals(q.obtem(j)));
        }

        // confere se a lista não está vazia
        assert(! q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover valores do inicio ou do meio da lista")
    void remove_do_meio() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = gera_lista();

        // remove valores de posições aleatórias
        Random rand = new Random();
        for (int j=q.comprimento()-1; j > 0; j--) {
            // lista ainda não pode estar vazia
            assert(! q.esta_vazia());
            // sorteia uma posição de onde remover um valor
            int pos = rand.nextInt(q.comprimento()-1);
            // copia o valor que está no final da lista
            Integer val = q.obtem(q.comprimento()-1);
            q.remove(pos);
            // verifica se o valor na posição após a remoção é o mesmo que estava no final da lista
            Integer val2 = q.obtem(pos);
            assert(val.equals(val2));
        }

        // confere se o comprimento é consistente com a quantidade de remoções
        assert(q.comprimento() == 1);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover valor de posição inválida")
    void remove_invalido() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        // remover de lista vazia
        assertThrows(IndexOutOfBoundsException.class, () -> {q.remove(0);});
        q.adiciona(Integer.valueOf(10));

        // acessar uma posição negativa
        assertThrows(IndexOutOfBoundsException.class, () -> {q.remove(-1);});
        // remover da posição igual ao comprimento
        assertThrows(IndexOutOfBoundsException.class, () -> {q.remove(1);});
        // remover da posição maior que comprimento
        assertThrows(IndexOutOfBoundsException.class, () -> {q.remove(7);});
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover valores do fim da lista")
    void remove_do_fim() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = gera_lista();

        // remove valor do fim da lista repetidas vezes
        for (int j=q.comprimento()-1; j > 0; j--) {
            assert(! q.esta_vazia());
            q.remove(j);
            // verifica se comprimento foi reduzido
            assert(q.comprimento() == j);
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa procurar valores na lista")
    void procura() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        // procura em lista vazia
        assert(q.procura(Integer.valueOf(3)) == -1);

        q = gera_lista();

        for (int j=0; j < q.capacidade(); j++) {
            var val = Integer.valueOf(j);
            // verifica se a posição o valor ptocurado está correta
            // note que cada posição contém um valor igual ao número dessa posição (ver gera_lista())
            int indice = q.procura(j);
            assert(indice == j);
        }

        // procura um valor que não existe numa lista não-vazia
        assert(q.procura(Integer.valueOf(1001)) == -1);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa limpar uma lista")
    void limpa() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        q.limpa();
        assert(q.comprimento() == 0);
        assert(q.esta_vazia());

        q = gera_lista();
        q.limpa();
        assert(q.comprimento() == 0);
        assert(q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa substituir valores")
    void substitui() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = gera_lista();

        Random rand = new Random();
        for (int j=q.comprimento(); j > 1 ; j--) {
            assert(! q.esta_vazia());
            // sorteia uma posição para substituição
            int pos = rand.nextInt(q.comprimento()-1);
            // obtém o valor dessa posição, e soma 100 a ele para gerar um novo valor
            Integer val = q.obtem(pos) + 100;
            // substitui o valor da posição pelo novo valor
            q.substitui(pos, val);
            // confere se ovalor atual na posição é o novo valor lá substituido
            var val2 = q.obtem(pos);
            assert(val.equals(val2));
        }

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa substituir valor de posição inválida")
    void substitui_invalido() throws InterruptedException, IOException {
        ListaSequencialSimples<Integer> q = new ListaSequencialSimples<>();

        // remover de lista vazia
        assertThrows(IndexOutOfBoundsException.class, () -> {q.substitui(0, 45);});
        q.adiciona(Integer.valueOf(10));

        // acessar uma posição negativa
        assertThrows(IndexOutOfBoundsException.class, () -> {q.substitui(-1, 44);});
        // remover da posição igual ao comprimento
        assertThrows(IndexOutOfBoundsException.class, () -> {q.substitui(1, 45);});
        // remover da posição maior que comprimento
        assertThrows(IndexOutOfBoundsException.class, () -> {q.substitui(7, 44);});
    }

}