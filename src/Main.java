
import esd.ListaSequencialSimples;

public class Main {
    public static void main(String[] args) {
        // Cria uma lista de String
        ListaSequencialSimples<String> lista = new ListaSequencialSimples<>();

        // anexa algumas strings ...
        lista.adiciona("4as2");
        lista.adiciona("16sa");
        lista.adiciona("15sas");
        lista.adiciona("asa");
        lista.adiciona("4");

        System.out.printf("Lista tem %d valores\n", lista.comprimento());

        // itera a lista para mostrar as strings
        for (int j=0; j < lista.comprimento(); j++) {
            System.out.println(lista.obtem(j));
        }
    }
}