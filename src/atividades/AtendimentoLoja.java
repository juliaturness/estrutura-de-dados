package atividades;

import java.util.Scanner;
import esd.ListaSimples;

public class AtendimentoLoja {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaSimples<String> fila = new ListaSimples<>();

        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                break;
            } else if (entrada.equals("?")) {
                if (fila.esta_vazia()) {
                    System.out.println("Nenhum cliente na fila.");
                } else {
                    String cliente = fila.obtem(0);
                    fila.remove(0);
                    System.out.println("Atendendo: " + cliente);
                }
            } else if (!entrada.isEmpty()) {
                fila.adiciona(entrada);
                System.out.println(fila.comprimento() - 1 + " cliente(s) na sua frente.");
            }
        }

        scanner.close();
    }
}
