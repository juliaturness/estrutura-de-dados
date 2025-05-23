import esd.ListaSimples;

public class Main {
    public static void main(String[] args) {
        ListaSimples lista = new ListaSimples();

        System.out.println("Adicionando elementos:");
        lista.adiciona("42");
        lista.adiciona("23");
        lista.adiciona("16");
        lista.adiciona("15");
        lista.adiciona("8");
        lista.adiciona("4");

        imprimeLista(lista);

        System.out.println("\nInserindo '88' na posição 2:");
        lista.insere(2, "88");
        imprimeLista(lista);

        System.out.println("\nSubstituindo valor da posição 0 por '99':");
        lista.substitui(0, "99");
        imprimeLista(lista);

        System.out.println("\nRemovendo o elemento da posição 3:");
        lista.remove(3);
        imprimeLista(lista);

        System.out.println("\nRemovendo o último elemento:");
        lista.remove_ultimo();
        imprimeLista(lista);

        System.out.println("\nComprimento da lista: " + lista.comprimento());
        System.out.println("Primeiro elemento: " + lista.obtem_primeiro());
        System.out.println("Último elemento: " + lista.obtem_ultimo());
        System.out.println("Elemento na posição 1: " + lista.obtem(1));
        System.out.println("Posição do valor '8': " + lista.procura("8"));
        System.out.println("Está vazia? " + lista.esta_vazia());

        System.out.println("\nInvertendo a lista:");
        lista.inverte();
        imprimeLista(lista);

        System.out.println("\nOrdenando a lista:");
        lista.ordena();
        imprimeLista(lista);

        System.out.println("\nEmbaralhando a lista:");
        lista.embaralha();
        imprimeLista(lista);

        System.out.println("\nLimpando a lista:");
        lista.limpa();
        imprimeLista(lista);
        System.out.println("Está vazia? " + lista.esta_vazia());
    }

    // Método auxiliar para imprimir a lista
    public static void imprimeLista(ListaSimples lista) {
        for (int i = 0; i < lista.comprimento(); i++) {
            System.out.printf("[%d] %s\n", i, lista.obtem(i));
        }
    }
}
