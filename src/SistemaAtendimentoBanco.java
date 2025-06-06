import java.util.Scanner;
import esd.ListaSimples;

public class SistemaAtendimentoBanco {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 6 filas: A (0) até F (5)
        ListaSimples<String>[] filas = new ListaSimples[6];
        for (int i = 0; i < 6; i++) {
            filas[i] = new ListaSimples<>();
        }

        int contador = 1;

        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                break;

            } else if (entrada.equals("?")) {
                boolean encontrado = false;

                for (int i = 0; i < 6; i++) {
                    if (!filas[i].esta_vazia()) {
                        String senha = filas[i].obtem(0);
                        filas[i].remove(0);
                        System.out.println("Atendendo: " + senha);
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Nenhum cliente na fila.");
                }

            } else if (entrada.matches("[A-F]")) {
                int prioridade = entrada.charAt(0) - 'A';
                String senha = entrada + String.format("%03d", contador++);
                int naFrente = 0;

                // Conta clientes nas filas de prioridade mais alta ou mesma com chegada antes
                for (int i = 0; i < prioridade; i++) {
                    naFrente += filas[i].comprimento();
                }
                naFrente += filas[prioridade].comprimento();

                filas[prioridade].adiciona(senha);

                System.out.println(senha + " - " + (naFrente - 1) + " cliente(s) na sua frente.");
            }
        }

        scanner.close();
    }
}
