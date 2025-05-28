import esd.ListaSequencial;

public class Main {
    public static void main(String[] args) {
        ListaSequencial<Integer> lista = new ListaSequencial<>();

        // Adicionando elementos fora de ordem
        lista.adiciona(5);
        lista.adiciona(3);
        lista.adiciona(3);
        lista.adiciona(1);
        lista.adiciona(4);
        lista.adiciona(2);
        lista.adiciona(5);
        lista.adiciona(9);

        lista.ordena();

        System.out.println("\nLista após a ordenação:");
        for (int i = 0; i < lista.comprimento(); i++) {
            System.out.print(lista.obtem(i) + " ");
        }
    }
}
