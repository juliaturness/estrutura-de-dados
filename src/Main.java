
import esd.TabHash;
import esd.ListaSequencial;

public class Main {
    public static void main(String[] args) {
        TabHash<String, Integer> tabela = new TabHash<>(5);

        // Adiciona pares chave-valor
        tabela.adiciona("um", 1);
        tabela.adiciona("dois", 2);
        tabela.adiciona("tres", 3);
        tabela.adiciona("quatro", 4);
        tabela.adiciona("cinco", 5);

        System.out.println("Tamanho após inserções: " + tabela.tamanho());
        System.out.println("Valor da chave 'tres': " + tabela.obtem("tres"));

        // Atualiza valor da chave 'dois'
        tabela.adiciona("dois", 22);
        System.out.println("Valor atualizado da chave 'dois': " + tabela.obtem("dois"));

        // Verifica presença de chave
        System.out.println("Contém 'quatro'? " + tabela.contem("quatro"));
        System.out.println("Contém 'dez'? " + tabela.contem("dez"));

        // Remove chave 'um'
        tabela.remove("um");
        System.out.println("Tamanho após remover 'um': " + tabela.tamanho());
        System.out.println("Contém 'um'? " + tabela.contem("um"));

        // Lista todas as chaves
        System.out.print("Chaves: ");
        ListaSequencial<String> chaves = tabela.chaves();
        for (int i = 0; i < chaves.comprimento(); i++) {
            System.out.print(chaves.obtem(i) + " ");
        }
        System.out.println();

        // Lista todos os valores
        System.out.print("Valores: ");
        ListaSequencial<Integer> valores = tabela.valores();
        for (int i = 0; i < valores.comprimento(); i++) {
            System.out.print(valores.obtem(i) + " ");
        }
        System.out.println();

        // Imprime colisões
        System.out.println("Número de colisões: " + tabela.colisoes());

        // Limpa a tabela
        tabela.limpa();
        System.out.println("Está vazia após limpar? " + tabela.esta_vazia());
    }
}
