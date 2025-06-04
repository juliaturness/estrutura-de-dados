import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import esd.ListaSequencialOrdenada;

public class contadoDePalavras {
     public static void main(String[] args) {
        String arquivo = "src/atividades/teste.txt"; // ou use args[0] se quiser via linha de comando
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
                String[] palavras = linha.split(" ");

                for (String palavra : palavras) {
                    palavra = palavra.trim();
                    boolean achou = false;
                
                    for (int i = 0; i < lista.comprimento(); i++) {
                        PalavraFrequente p = lista.obtem(i);
                        if (p.getPalavra().equals(palavra)) {
                            p.incrementar();
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
            

    public static class PalavraFrequente implements Comparable<PalavraFrequente> {
    private String palavra;
    private int frequencia;

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

