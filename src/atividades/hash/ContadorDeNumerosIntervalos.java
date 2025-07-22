package atividades.hash;
import esd.TabHash;
import esd.ListaSequencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class ContadorDeNumerosIntervalos {

        public static void main(String[] args) {
            Scanner entradaTeclado = new Scanner(System.in);
            String nomeArquivo = entradaTeclado.nextLine();
            entradaTeclado.close();

            File arquivo = new File(nomeArquivo);
            Scanner scanner;

            try {
                scanner = new Scanner(arquivo);
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo invalido");
                return;
            }

            TabHash<Integer, Integer> contagemIntervalos = new TabHash<>();
            int menorNumero = Integer.MAX_VALUE;

            while (scanner.hasNext()) {
                int numero = scanner.nextInt();
                if (numero < menorNumero) {
                    menorNumero = numero;
                }
            }

            // Releitura do arquivo para atribuir os números aos intervalos
            try {
                scanner = new Scanner(arquivo);
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo invalido");
                return;
            }

            while (scanner.hasNext()) {
                int numero = scanner.nextInt();
                int intervaloInferior = ((numero - menorNumero) / 1_000_000) * 1_000_000 + menorNumero;

                int count = contagemIntervalos.obtem_ou_default(intervaloInferior, 0);
                contagemIntervalos.adiciona(intervaloInferior, count + 1);
            }

            scanner.close();

            // Ordena os limites inferiores dos intervalos
            ListaSequencial<Integer> chaves = contagemIntervalos.chaves();
            Integer[] intervalos = new Integer[chaves.comprimento()];
            for (int i = 0; i < chaves.comprimento(); i++) {
                intervalos[i] = chaves.obtem(i);
            }

            Arrays.sort(intervalos);

            // Imprime a contagem por intervalo
            for (int intervalo : intervalos) {
                int count = contagemIntervalos.obtem(intervalo);
                System.out.println(intervalo + " " + count);
            }
        }
    }

