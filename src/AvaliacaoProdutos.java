import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import esd.ListaSequencial;

public class AvaliacaoProdutos {

    static class ProdutoAvaliacao implements Comparable<ProdutoAvaliacao> {
        String nome;
        double somaAvaliacoes;
        int quantidade;

        public ProdutoAvaliacao(String nome) {
            this.nome = nome;
            this.somaAvaliacoes = 0;
            this.quantidade = 0;
        }

        public void adicionarAvaliacao(double avaliacao) {
            this.somaAvaliacoes += avaliacao;
            this.quantidade++;
        }

        public double getMedia() {
            return quantidade > 0 ? somaAvaliacoes / quantidade : 0;
        }

        @Override
        public int compareTo(ProdutoAvaliacao outro) {
            return Double.compare(outro.getMedia(), this.getMedia());
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "%s: %.2f (%d avaliações)",
                    nome, getMedia(), quantidade);
        }
    }

    public static void main(String[] args) {
        ListaSequencial<ProdutoAvaliacao> produtos = new ListaSequencial<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/palavras.txt"))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                processarLinha(linha, produtos);
            }

            produtos.ordena();

            // Saída exatamente como no exemplo
            for (int i = 0; i < produtos.comprimento(); i++) {
                // Substitui ponto por vírgula na formatação
                String output = produtos.obtem(i).toString().replace('.', ',');
                System.out.println(output);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void processarLinha(String linha, ListaSequencial<ProdutoAvaliacao> produtos) {
        String[] partes = linha.split(",");
        if (partes.length == 2) {
            String nomeProduto = partes[0].trim();
            try {
                String avaliacaoStr = partes[1].trim().replace(',', '.');
                double avaliacao = Double.parseDouble(avaliacaoStr);

                if (avaliacao < 0 || avaliacao > 5) {
                    System.err.println("Avaliação fora do intervalo (0-5): " + linha);
                    return;
                }

                boolean encontrado = false;
                for (int i = 0; i < produtos.comprimento(); i++) {
                    ProdutoAvaliacao p = produtos.obtem(i);
                    if (p.nome.equalsIgnoreCase(nomeProduto)) {
                        p.adicionarAvaliacao(avaliacao);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    ProdutoAvaliacao novo = new ProdutoAvaliacao(nomeProduto);
                    novo.adicionarAvaliacao(avaliacao);
                    produtos.adiciona(novo);
                }
            } catch (NumberFormatException e) {
                System.err.println("Avaliação inválida: " + linha);
            }
        } else if (!linha.trim().isEmpty()) {
            System.err.println("Formato inválido: " + linha);
        }
    }
}