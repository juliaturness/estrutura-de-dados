package atividades.arvore;

import esd.APB;
import esd.Fila;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MediaMovel {

    private Scanner arquivo;
    private APB<Double> arvore;

    private int intervalo;
    private Fila<Double> filaJanela;
    private double soma;

    public MediaMovel(String caminho) {
        arquivo = scan(caminho);
        arvore = new APB<>();

        intervalo = 50;
        filaJanela = new Fila<>();
        soma = 0.0;
    }

    public Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao ler o arquivo no caminho: " + caminho);
        }
    }

    public void read() {

        while (arquivo.hasNext()) {

            String linha = arquivo.nextLine().trim();
            if (linha.isEmpty()) continue;

            Double valor = Double.parseDouble(linha);


            if (filaJanela.comprimento() == intervalo) {
                // Remove valor mais antigo da janela
                double valorRemovido = filaJanela.remove();
                arvore.remove(valorRemovido);
                soma -= valorRemovido;
            }

            // Adiciona novo valor
            filaJanela.adiciona(valor);
            arvore.adiciona(valor);
            soma += valor;

            if (filaJanela.comprimento() == intervalo) {
                double mediaMovel = soma / intervalo;
                System.out.printf("%.4f\n", mediaMovel);
            }
        }

    }

    public static void main(String[] args) {

        if (args.length != 1)
            throw new IllegalArgumentException("<MediaMovel> Um argumento requerido.");

        MediaMovel mm = new MediaMovel(args[0]);
        mm.read();

    }

}
