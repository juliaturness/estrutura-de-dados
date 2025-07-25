package atividades.conjunto;

import esd.Conjunto;
import esd.ListaSequencial;

import java.io.FileReader;
import java.util.Scanner;

public class ComparaConjuntoPalavras {

    private Scanner arquivo;
    private Conjunto<String> conjunto = new Conjunto<>();

    public ComparaConjuntoPalavras(String caminho) {
        arquivo = scan(caminho);
    }

    public Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        }  catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + caminho, e);
        }
    }

    public Conjunto read() {
        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim().toLowerCase();
            if (!linha.isEmpty()) conjunto.adiciona(linha);
        }
        return conjunto;
    }

    public static void main(String[] args) {
        if (args.length != 2)
            throw new RuntimeException("<ComparaConjuntoPalavras> requer 2 argumentos (caminho dos arquivos)");

        ComparaConjuntoPalavras ccp1 = new ComparaConjuntoPalavras(args[0]);
        ComparaConjuntoPalavras ccp2 = new ComparaConjuntoPalavras(args[1]);

        Conjunto c1 = ccp1.read();
        Conjunto c2 = ccp2.read();

        if (c1.equals(c2)) System.out.println("VERDADEIRO");
        else System.out.println("FALSO");

    }

}
