package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CalculaAvaliacaoMedia {

    public class Produto implements Comparable<Produto> {
        public String nome;
        public int totalAvaliacoes = 0;
        public int quantidade = 0;

        public Produto(String nome) { this.nome = nome; }

        public void adicionarAvaliacao(int nota) {
            totalAvaliacoes += nota;
            quantidade++;
        }

        public double media() {
            return quantidade == 0 ? 0 : (double) totalAvaliacoes / quantidade;
        }

        @Override
        public int compareTo(Produto outro) {
            return this.nome.compareTo(outro.nome);
        }

        @Override
        public String toString() {
            return nome + ": " + String.format("%.2f", media());
        }
    }

    private Scanner arquivo;
    private APB<Produto> produtos;

    public CalculaAvaliacaoMedia(String caminho) {
        arquivo = scan(caminho);
        produtos = new APB<>();
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

            String linha = arquivo.nextLine();
            if (linha.trim().isEmpty()) continue;

            String[] colunas = linha.split(",", 2);
            String nome = colunas[0];
            int av = Integer.parseInt(colunas[1]);

            Produto temporario = new Produto(nome);
            Produto produto = produtos.procura(temporario);

            if (produto == null) {
                temporario.adicionarAvaliacao(av);
                produtos.adiciona(temporario);
                continue;
            }

            produto.adicionarAvaliacao(av);
        }

    }

    @Override
    public String toString() {

        ListaSequencial<Produto> lista = produtos.emOrdem();
        lista.ordena();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lista.comprimento(); i++)
            sb.append(lista.obtem(i)).append("\n");

        return sb.toString();
    }

    public static void main(String[] args) {

        if (args.length != 1)
            throw new IllegalArgumentException("<CalculaAvaliacaoMedia> Um argumento requerido.");

        CalculaAvaliacaoMedia cam = new CalculaAvaliacaoMedia(args[0]);
        cam.read();
        System.out.println(cam);

    }

}
