package atividades.arvore;

import esd.APB;

import java.io.FileReader;
import java.util.Scanner;

public class ConsultaNome {

    private Scanner arquivo;
    private APB<String> arvore = new APB<>();

    public ConsultaNome(String caminho) {
        arquivo = scan(caminho);
    }

    public Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        }  catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + caminho, e);
        }
    }

    public void read() {
        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim().toLowerCase();
            if (!linha.isEmpty()) arvore.adiciona(linha);;
        }
    }

    public void consulta() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine().trim().toLowerCase();

            if (entrada.isEmpty())  break;

            String resultado = (arvore.procura(entrada) != null) ? "EXISTE" : "INEXISTENTE";
            System.out.println(entrada + ": " + resultado);
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("<ConsultaNome> requer 1 argumento (caminho do arquivo)");

        ConsultaNome cc = new ConsultaNome(args[0]);

        cc.read();
        cc.consulta();
    }

}


