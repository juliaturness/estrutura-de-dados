import java.util.*;
import java.util.Queue;
import java.util.Scanner;

public class ContadorBinario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o numero: ");
        int numero = scanner.nextInt();

        Queue<String> fila = new ArrayDeque<>();
        fila.add("1");

        int count = 0;

        while (count < numero) {

            String binario = fila.poll();

            System.out.println(binario);
            count++;

            fila.add(binario + 0);
            fila.add(binario + 1);

        }
        scanner.close();
    }
}
