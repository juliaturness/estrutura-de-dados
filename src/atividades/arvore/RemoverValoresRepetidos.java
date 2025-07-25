package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

import java.io.FileReader;
import java.util.Scanner;

public class RemoverValoresRepetidos {

    private Scanner arquivo;
    private APB<String> arvore = new APB<>();

    public RemoverValoresRepetidos(String caminho) {
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
            String linha = arquivo.nextLine().trim();
            if (!linha.isEmpty()) arvore.adiciona(linha);
        }
    }

    public void print() {
        ListaSequencial<String> lista = arvore.emOrdem();
        for (int i = 0; i < lista.comprimento(); i++) {
            System.out.println(lista.obtem(i));
        }
    }

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("<MatriculaUnica> requer 1 argumento (caminho do arquivo)");

        RemoverValoresRepetidos rvr = new RemoverValoresRepetidos(args[0]);
        rvr.read();
        rvr.print();
    }

}
