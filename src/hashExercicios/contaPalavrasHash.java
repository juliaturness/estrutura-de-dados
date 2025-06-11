package hashExercicios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class contaPalavrasHash {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java ContaPalavrasHash <nome_do_arquivo>");
            return;
        }

        String arquivo = args[0];
        try {
            contarPalavras(arquivo);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
    public static void contarPalavras(String caminho) throws IOException {
        HashMap<String, Integer> frequencias = new HashMap();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.split("\\s+");

                for (String palavra : palavras) {
                    palavra = palavra.replaceAll("[^\\p{L}]", "").toLowerCase().trim();

                    if (!palavra.isEmpty()) {
                        frequencias.put(palavra, frequencias.getOrDefault(palavra, 0) + 1);
                    }
                }
            }
        }
        for (String palavra : frequencias.keySet()) {
            System.out.println(palavra + " " + frequencias.get(palavra));
        }

    }
}
