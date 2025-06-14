package atividades;

import esd.unidade1.Fila;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Comparador {

    public static void main(String[] args) {

        Fila<String> conjunto1 = lerPalavrasUnicas(args[0]);
        Fila<String> conjunto2 = lerPalavrasUnicas(args[1]);

        if (conjunto1 == null || conjunto2 == null) {
            System.out.println("arquivo invalido");
            return;
        }

        if (conjuntosIguais(conjunto1, conjunto2)) {
            System.out.println("VERDADEIRO");
        } else {
            System.out.println("FALSO");
        }
    }

    // Lê palavras únicas de um arquivo e armazena na fila
    private static Fila<String> lerPalavrasUnicas(String nomeArquivo) {
        Fila<String> fila = new Fila<>();

        File arquivo = new File(nomeArquivo);

        try (Scanner scanner = new Scanner(arquivo)) {
            while (scanner.hasNext()) {
                String palavra = scanner.next();
                if (!contem(fila, palavra)) {
                    fila.adiciona(palavra);
                }
            }
        } catch (FileNotFoundException e) {
            return null;
        }

        return fila;
    }


    private static boolean contem(Fila<String> fila, String palavra) {
        Fila<String> aux = new Fila<>();
        boolean achou = false;

        while (!fila.estaVazia()) {
            String atual = fila.remove();
            if (atual.equals(palavra)) {
                achou = true;
            }
            aux.adiciona(atual);
        }

        while (!aux.estaVazia()) {
            fila.adiciona(aux.remove());
        }

        return achou;
    }

    private static boolean conjuntosIguais(Fila<String> f1, Fila<String> f2) {
        return contemTodos(f1, f2) && contemTodos(f2, f1);
    }

    private static boolean contemTodos(Fila<String> f1, Fila<String> f2) {
        Fila<String> aux2 = new Fila<>();
        boolean resultado = true;

        while (!f2.estaVazia()) {
            String palavra = f2.remove();
            if (!contem(f1, palavra)) {
                resultado = false;
            }
            aux2.adiciona(palavra);
        }

        while (!aux2.estaVazia()) {
            f2.adiciona(aux2.remove());
        }

        return resultado;
    }
}
