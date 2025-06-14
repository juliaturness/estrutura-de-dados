package atividades.hashExercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class a {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java hashExercicios.E1Hash <nome_do_arquivo>");
            return;
        }

        String caminhoArquivo = args[0];
        retiraDuplicatas(caminhoArquivo);
    }

    public static void retiraDuplicatas(String caminho) {
        HashMap<String, Boolean> mapa = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    mapa.put(linha, true); // garante unicidade
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Copiar chaves para uma lista e ordenar
        ArrayList<String> matriculasOrdenadas = new ArrayList<>(mapa.keySet());
        Collections.sort(matriculasOrdenadas);

        // Imprimir ordenadamente
        for (String matricula : matriculasOrdenadas) {
            System.out.println(matricula);
        }
    }
}
