import esd.Deque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RetiraPalavrasRepetidas {


    public static void main(String[] args) throws IOException {
        try {
            String arquivo = "src/teste.txt";
            retira(arquivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void retira (String caminho) throws IOException {
        Deque<String> palavras = new Deque<>();


        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] espaco = linha.split(" ");

                for (String palavra : espaco) {
                    palavra = palavra.trim();
                    boolean repetida = false;
                    for (int i = 0; i < palavras.comprimento(); i++) {
                        if (palavras.acessa(i).equals(palavra)) {
                            repetida = true;
                            break;
                        }
                    }
                    if (!repetida && !palavra.isEmpty()) {
                        palavras.adiciona(palavra);
                    }
                }
            }

            for (int i = 0; i < palavras.comprimento(); i++) {
                System.out.println(palavras.acessa(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
