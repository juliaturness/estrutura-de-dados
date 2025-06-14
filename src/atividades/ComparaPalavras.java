package atividades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import esd.unidade1.ListaSequencialSimples;

public class ComparaPalavras {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Por favor, forneça dois arquivos como argumentos.");
            return;
        }

        String arquivo1 = args[0]; // Arquivo 1 passado por argumento
        String arquivo2 = args[1]; // Arquivo 2 passado por argumento

        try {
            if (compararArquivos(arquivo1, arquivo2)) {
                System.out.println("VERDADEIRO");
            } else {
                System.out.println("FALSO");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler os arquivos: " + e.getMessage());
            e.printStackTrace(); // Mostra a exceção completa
        }
    }


    public static boolean compararArquivos(String caminho1, String caminho2) throws IOException {
        ListaSequencialSimples<String> palavrasArquivo1 = lerArquivo(caminho1);
        ListaSequencialSimples<String> palavrasArquivo2 = lerArquivo(caminho2);

        boolean existe = false;

        for (int i = 0; i < palavrasArquivo1.comprimento(); i++) {
            String palavra = palavrasArquivo1.obtem(i);
            if (palavra.contains(palavrasArquivo2.toString().trim())) {
                existe = true;
                break;
            }
        }

        return palavrasArquivo1.equals(palavrasArquivo2);
    }

    public static ListaSequencialSimples<String> lerArquivo(String caminho) throws IOException {
        ListaSequencialSimples<String> palavras = new ListaSequencialSimples<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] palavrasDaLinha = linha.split("\\s+");
                for (String palavra : palavrasDaLinha) {
                    palavra = palavra.trim().toLowerCase();
                    if (palavras.procura(palavra) == -1) {
                        palavras.adiciona(palavra);
                    }
                }
            }
        }
        return palavras;
    }
}
