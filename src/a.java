import esd.Deque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class a {
    public static void main(String[] args) {
        Deque<String> urgentes = new Deque<>();
        Deque<String> avisos = new Deque<>();
        Deque<String> informativos = new Deque<>();

        String nomeArquivo = "src/teste.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                if (linha.startsWith("URGENTE:")) {
                    urgentes.adiciona(linha);
                } else if (linha.startsWith("AVISO:")) {
                    avisos.adiciona(linha);
                } else if (linha.startsWith("INFO:")) {
                    informativos.adiciona(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.println("Tickets Urgentes:");
        while (!urgentes.esta_vazia()){
            System.out.println(urgentes.extrai_inicio());
        }

        System.out.println("Tickets de Aviso:");
        while (!avisos.esta_vazia()){
            System.out.println( avisos.extrai_inicio());
        }

        System.out.println("Tickets Informativos:");
        while (!informativos.esta_vazia()){
            System.out.println( informativos.extrai_inicio());
        }
    }

}
