package atividades.hash;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class CalculaAvaliacaoMedia {


        public static void main(String[] args) {
            // Nome do arquivo CSV
            String arquivoCSV = "avaliacoes.csv"; // Substitua pelo caminho do seu arquivo CSV

            // Tabela Hash para armazenar as avaliações
            Map<String, List<Integer>> produtosAvaliacoes = new HashMap<>();

            // Ler o arquivo CSV
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
                String linha;

                while ((linha = reader.readLine()) != null) {
                    // Separar o produto e a avaliação
                    String[] partes = linha.split(",");
                    String produto = partes[0].trim();
                    int avaliacao = Integer.parseInt(partes[1].trim());

                    // Adicionar a avaliação à lista do produto
                    produtosAvaliacoes.computeIfAbsent(produto, k -> new ArrayList<>()).add(avaliacao);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Calcular a média das avaliações de cada produto
            List<Map.Entry<String, Double>> produtosOrdenados = produtosAvaliacoes.entrySet()
                    .stream()
                    .map(entry -> new AbstractMap.SimpleEntry<>(
                            entry.getKey(),
                            calcularMedia(entry.getValue())
                    ))
                    .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Ordena decrescentemente
                    .collect(Collectors.toList());

            // Exibir os produtos e suas avaliações médias
            System.out.println("Avaliações médias dos produtos (ordem decrescente):");
            for (Map.Entry<String, Double> entry : produtosOrdenados) {
                System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
            }
        }

        // Método para calcular a média das avaliações de um produto
        public static double calcularMedia(List<Integer> avaliacoes) {
            int soma = 0;
            for (int avaliacao : avaliacoes) {
                soma += avaliacao;
            }
            return (double) soma / avaliacoes.size();
        }
    }


