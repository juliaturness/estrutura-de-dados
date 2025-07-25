package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

import java.io.FileReader;
import java.util.Scanner;

public class AgruparPalavrasPorTamanho {

    static class Par<K extends Comparable<K>, V> implements Comparable<Par<K, V>> {
        K chave;
        V valor;

        Par(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        @Override
        public int compareTo(Par<K, V> outro) {
            return this.chave.compareTo(outro.chave);
        }
    }

    private Scanner arquivo;
    private APB<Par<Integer, ListaSequencial<String>>> arvore = new APB<>();

    public AgruparPalavrasPorTamanho(String caminho) {
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
            String linha = arquivo.nextLine().trim().toLowerCase();
            if (linha.isEmpty()) continue;

            int comprimento = linha.length();

            Par<Integer, ListaSequencial<String>> aux = new Par<>(comprimento, null);
            Par<Integer, ListaSequencial<String>> par = arvore.procura(aux);

            if (par == null) {
                ListaSequencial<String> novaLista = new ListaSequencial<>();
                novaLista.adiciona(linha);
                arvore.adiciona(new Par<>(comprimento, novaLista));
                continue;
            }

            par.valor.adiciona(linha);

        }
    }

    public void print() {
        ListaSequencial<Par<Integer, ListaSequencial<String>>> ordenados = arvore.emOrdem();
        for (int i = 0; i < ordenados.comprimento(); i++) {
            Par<Integer, ListaSequencial<String>> par = ordenados.obtem(i);
            ListaSequencial<String> lista = par.valor;

            for (int j = 0; j < lista.comprimento(); j++) {
                System.out.print(lista.obtem(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("<AgruparPalavrasPorTamanho> requer 1 argumento (caminho do arquivo)");

        AgruparPalavrasPorTamanho apt = new AgruparPalavrasPorTamanho(args[0]);

        apt.read();
        apt.print();
    }

}
