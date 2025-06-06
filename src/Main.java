
import esd.ListaSequencial  ;


public class Main {
    public static void main(String[] args) {
        ListaSequencial<Integer> lista = new ListaSequencial<>();

        lista.adiciona(5);
        lista.adiciona(3);
        lista.adiciona(8);
        lista.adiciona(1);
        lista.adiciona(4);
        lista.adiciona(9);
        lista.adiciona(2);
        lista.adiciona(7);

        System.out.println("Lista original:");
        for (int i = 0; i < lista.comprimento(); i++) {
            System.out.print(lista.obtem(i) + " ");
        }
        System.out.println();

        System.out.println("Lista após o embaralhamento:");
        for (int i = 0; i < lista.comprimento(); i++) {
            System.out.print(lista.obtem(i) + " ");
        }
        System.out.println();
    }
}
