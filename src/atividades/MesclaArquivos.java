package atividades;

import esd.Fila;

import java.io.*;
import java.util.*;

public class MesclaArquivos {

    public static Fila<String> leArquivo(String nomeArquivo) {
        String[] palavras = new String[100];
        int count = 0;

        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            while (scanner.hasNext()) {
                if (count == palavras.length) {
                    String[] novo = new String[palavras.length * 2];
                    for (int i = 0; i < palavras.length; i++) {
                        novo[i] = palavras[i];
                    }
                    palavras = novo;
                }
                palavras[count++] = scanner.next();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao abrir o arquivo: " + nomeArquivo);
        }

        Arrays.sort(palavras, 0, count);

        Fila<String> filaOrdenada = new Fila<>();
        for (int i = 0; i < count; i++) {
            filaOrdenada.adiciona(palavras[i]);
        }

        return filaOrdenada;
    }

    public static void mesclaArquivos(String nomeArquivo1, String nomeArquivo2) {
        Fila<String> fila1 = leArquivo(nomeArquivo1);
        Fila<String> fila2 = leArquivo(nomeArquivo2);

        while (!fila1.estaVazia() && !fila2.estaVazia()) {
            if (fila1.frente().compareTo(fila2.frente()) <= 0) {
                System.out.println(fila1.remove());
            } else {
                System.out.println(fila2.remove());
            }
        }

        while (!fila1.estaVazia()) {
            System.out.println(fila1.remove());
        }

        while (!fila2.estaVazia()) {
            System.out.println(fila2.remove());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java MesclaArquivos <arquivo1> <arquivo2>");
            return;
        }

        mesclaArquivos(args[0], args[1]);
    }
}
