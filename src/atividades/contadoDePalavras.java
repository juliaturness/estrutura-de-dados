package atividades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import esd.ListaSequencialOrdenada;

public class contadoDePalavras {
     public static void main(String[] args) {
        String arquivo = args[0];
        try {
            contando(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void contando(String caminho) throws IOException {
        ListaSequencialOrdenada<PalavraFrequente> lista = new ListaSequencialOrdenada<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.split("\\s+");

                for (String palavra : palavras) {
                    palavra = palavra.replaceAll("[^\\p{L}]", "").toLowerCase().trim();

                    if (!palavra.isEmpty()) {
                        boolean achou = false;

                        for (int i = 0; i < lista.comprimento(); i++) {
                            PalavraFrequente p = lista.obtem(i);
                            if (p.getPalavra().equals(palavra)) {
                                lista.remove(lista.obtem(i));
                                p.incrementar();
                                lista.insere(p);
                                achou = true;
                                break;
                            }
                        }

                        if (!achou) {
                            lista.insere(new PalavraFrequente(palavra));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < lista.comprimento(); i++) {
            PalavraFrequente p = lista.obtem(i);
            System.out.println(p);
        }
    }


    public static class PalavraFrequente implements Comparable<PalavraFrequente> {
    public String palavra;
    public int frequencia;

    public PalavraFrequente(String palavra) {
        this.palavra = palavra.toLowerCase();
        this.frequencia = 1;
    }

    public String getPalavra() {
        return palavra;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void incrementar() {
        frequencia++;
    }

    @Override
    public int compareTo(PalavraFrequente outra) {
        if (this.frequencia != outra.frequencia) {
            return Integer.compare(outra.frequencia, this.frequencia);
        }
        return this.palavra.compareTo(outra.palavra);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PalavraFrequente)) return false;
        PalavraFrequente outra = (PalavraFrequente) o;
        return this.palavra.equals(outra.palavra);
    }

    @Override
    public int hashCode() {
        return palavra.hashCode();
    }

    @Override
    public String toString() {
        return palavra + " " + frequencia;
    }
}

}

