package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

import java.io.FileReader;
import java.util.Scanner;

public class ContadorDeNumerosEmIntervalos {

    public class Intervalo implements Comparable<Intervalo> {
        public long inicio;
        public int contagem = 0;

        public Intervalo(long inicio) {
            this.inicio = inicio;
        }

        @Override
        public int compareTo(Intervalo outro) {
            return Long.compare(this.inicio, outro.inicio);
        }

        @Override
        public String toString() {
            return inicio + " : " + contagem;
        }
    }


    private Scanner arquivo;
    private APB<Intervalo> arvore;

    public ContadorDeNumerosEmIntervalos(String caminho) {
        arquivo = scan(caminho);
        arvore = new APB<>();
    }

    private Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        } catch (Exception e ) {
            throw new RuntimeException("Não foi possível ler arquivo no caminho: " + caminho);
        }
    }

    public void read() {

        long menorNumero = Long.MAX_VALUE;

        while (arquivo.hasNext()) {

            String linha = arquivo.nextLine();
            if (linha.trim().isEmpty()) continue;

            long numero;
            try {
                numero = Long.parseLong(linha);
            } catch (NumberFormatException e) {
                continue;
            }

            if (numero < menorNumero) menorNumero = numero;

            long base = (numero / 1_000_000) * 1_000_000;

            Intervalo aux = new Intervalo(base);
            Intervalo intervalo = arvore.procura(aux);

            if (intervalo == null) {
                aux.contagem = 1;
                arvore.adiciona(aux);
                continue;
            }

            intervalo.contagem++;

        }

    }

    @Override
    public String toString() {
        ListaSequencial<Intervalo> intervalos = arvore.emOrdem();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < intervalos.comprimento(); i++)
            sb.append(intervalos.obtem(i)).append("\n");

        return sb.toString();
    }

    public static void main(String[] args) {

        if (args.length != 1)
            throw new RuntimeException("<ContadorDeNumerosEmIntervalos> Requer 1 argumento");

        ContadorDeNumerosEmIntervalos cni = new ContadorDeNumerosEmIntervalos(args[0]);
        cni.read();
        System.out.println(cni);

    }

}
