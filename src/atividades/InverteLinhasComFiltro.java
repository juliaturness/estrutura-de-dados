package atividades;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import esd.unidade1.Deque;

public class InverteLinhasComFiltro {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("arquivo invalido");
            return;
        }

        String nomeArquivo = args[0];
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists() || !arquivo.canRead() || arquivo.length() == 0) {
            System.out.println("arquivo invalido");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            Deque<String> maiusculas = new Deque<>();
            Deque<String> minusculas = new Deque<>();
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (linha.isEmpty()) continue;

                char primeiroChar = linha.charAt(0);

                if (Character.isLowerCase(primeiroChar)) {
                    minusculas.insere(linha);
                } else if (Character.isUpperCase(primeiroChar)) {
                    maiusculas.insere(linha);
                }

            }


            while (!minusculas.esta_vazia()) {
                System.out.println(minusculas.extrai_inicio());
            }


            while (!maiusculas.esta_vazia()) {
                System.out.println(maiusculas.extrai_inicio());
            }

        } catch (IOException e) {
            System.out.println("arquivo invalido");
        }
    }
}