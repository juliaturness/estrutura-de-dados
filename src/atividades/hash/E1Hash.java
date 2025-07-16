package atividades.hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class E1Hash {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java hashExercicios.E1Hash <nome_do_arquivo>");
            return;
        }

        String caminhoArquivo = args[0];
        retira(caminhoArquivo);
    }

    public static void retira(String caminho) {

        HashMap<String, Boolean> numero = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    numero.put(linha, true); // garante unicidade
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> matriculasOrdenadas = new ArrayList<>(numero.keySet());
        Collections.sort(matriculasOrdenadas);

        for (String matricula : matriculasOrdenadas) {
            System.out.println(matricula);
        }

    }
}


