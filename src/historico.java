import esd.Deque;

import java.util.Scanner;

public class historico {

    public static void main(String[] args) {
        // deque para armazenar as entradas
        Deque<String> entrada = new Deque<>();
        Scanner scanner = new Scanner(System.in);


        while(true) {
            // aqui eu guardo com comandos e coloco todas as letras para minusculas
            System.out.print(">");
            String comando = scanner.next();
            comando = comando.toLowerCase();


            // switch utilizado para comparar os comandos
            switch (comando) {
                case "historico":
                    // se o comando for historico ent vai adicionar o comando
                    // equanto o deque não estiver vazio ele vai extrair o final para que seja na ordem mais recente

                    entrada.adiciona(comando);
                    System.out.println("Acessando o seu histórico: ");

                    while (!entrada.esta_vazia()) {
                        System.out.println(entrada.extrai_final());
                    }

                    System.out.println("Comando executado");
                    break;

                case "sair":
                    System.out.println("Comando executado");
                    System.out.println("Saindo... Tchau Tchau até a próxima");
                    return;

                default:
                    // se o usuário der mais que cinco comandos ent vai retirar o comando mais velho

                    entrada.adiciona(comando);
                    if (entrada.capacidade() >=5) {
                        entrada.extrai_inicio();
                    }
                    System.out.println("Comando executado");
                    break;
            }
        }
    }
}
