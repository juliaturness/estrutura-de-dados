package atividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class Balanceador {

    public static void verificarArquivo(String caminho) throws IOException {
        Path path = Paths.get(caminho).toAbsolutePath();
        System.out.println("Verificando arquivo: " + path);

        if (!Files.exists(path)) {
            System.out.println("Erro: Arquivo não encontrado.");
            return;
        }

        BufferedReader reader = Files.newBufferedReader(path);
        String linha;
        int numLinha = 1;
        Stack<Character> pilha = new Stack<>();

        while ((linha = reader.readLine()) != null) {
            for (int i = 0; i < linha.length(); i++) {
                char c = linha.charAt(i);

                if (c == '{' || c == '[' || c == '(') {
                    pilha.push(c);
                }
                else if (c == '}' || c == ']' || c == ')') {
                    if (pilha.isEmpty()) {
                        System.out.println("[ERRO] Linha " + numLinha +
                                ": " + c + " sem abertura correspondente.");
                        reader.close();
                        return;
                    }

                    char topo = pilha.pop();
                    if (!corresponde(topo, c)) {
                        System.out.println("[ERRO] Linha " + numLinha +
                                ": " + "não fechou " + topo );
                        reader.close();
                        return;
                    }
                }
            }
            numLinha++;
        }

        if (!pilha.isEmpty()) {
            char naoFechado = pilha.pop();
            System.out.println("[ERRO] Faltou fechar " + naoFechado + " no arquivo");
        } else {
            System.out.println("OK! O arquivo está balanceado.");
        }

        reader.close();
    }

    private static boolean corresponde(char abertura, char fechamento) {
        return (abertura == '{' && fechamento == '}') ||
                (abertura == '[' && fechamento == ']') ||
                (abertura == '(' && fechamento == ')');
    }

    public static void main(String[] args) throws IOException {
        String arquivo = "src/teste.txt";
        verificarArquivo(arquivo);
    }
}