import esd.Fila;

public class Main {
    public static void main(String[] args) {
        // cria uma Fila com capacidade para armazenar 8 dados
        Fila<String> q = new Fila<>(8);

        // Enfileira algumas String na Fila ...
        q.adiciona("banana");
        q.adiciona("graviola");
        q.adiciona("pitanga");
        q.adiciona("sapoti");
        q.adiciona("acerola");
        q.adiciona("laranja");

        // Enquanto fila não estiver vazia, mostra o valor na frente da fila e
        // em seguida o desenfileira
        while (! q.estaVazia()) {
            System.out.printf("valor na frente da fila: %s\n", q.frente());
            q.remove();
        }
    }
}