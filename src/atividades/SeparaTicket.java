package atividades;

import esd.Deque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SeparaTicket {


    public static void main(String[] args) {

        // cria um deque para cada tipo de ticket
        Deque<String> urgentes = new Deque<>();
        Deque<String> avisos = new Deque<>();
        Deque<String> informativos = new Deque<>();

        // argumento
        String CaminhoArquivo = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(CaminhoArquivo))) {
            String linha;

            //enquanto estiver tendo linha para ler
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                // Se começar com URGENTE: vai adicionar ao deque de urgente
                // Se começar com AVISO: vai adicionar ao deque de aviso
                // Se começar com INFO: vai adicionar ao deque de info
                if (linha.startsWith("URGENTE:")) {
                    urgentes.adiciona(linha);
                } else if (linha.startsWith("AVISO:")) {
                    avisos.adiciona(linha);
                } else if (linha.startsWith("INFO:")) {
                    informativos.adiciona(linha);
                }
            }
            // exibe erro se não conseguir ler o arquivo
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // imprime na tela todos os tickets separados
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
