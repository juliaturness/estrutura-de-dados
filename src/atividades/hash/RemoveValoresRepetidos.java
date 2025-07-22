package atividades.hash;
import esd.TabHash;
import esd.ListaSequencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class RemoveValoresRepetidos {
        public static void main(String[] args) {
            if (args.length < 1) {
                System.out.println("Arquivo invalido");
                return;
            }

            String nomeArquivo = args[0];
            File arquivo = new File(nomeArquivo);
            Scanner scanner;

            try {
                scanner = new Scanner(arquivo);
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo invalido");
                return;
            }

            TabHash<String, Boolean> tabela = new TabHash<>();

            while (scanner.hasNext()) {
                String matricula = scanner.next();
                if (!tabela.contem(matricula)) {
                    tabela.adiciona(matricula, true);
                }
            }

            scanner.close();

            // Obter todas as matrículas únicas
            ListaSequencial<String> chaves = tabela.chaves();

            // Copiar para array para ordenar
            String[] matriculas = new String[chaves.comprimento()];
            for (int i = 0; i < chaves.comprimento(); i++) {
                matriculas[i] = chaves.obtem(i);
            }

            Arrays.sort(matriculas); // ordenação lexicográfica

            for (String m : matriculas) {
                System.out.println(m);
            }
        }
    }
