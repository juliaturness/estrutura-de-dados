package atividades.hash;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SumarizadorDeGastosDeClientes {


        public static void main(String[] args) {
            // Leitura do nome do cliente e mês via entrada padrão (teclado)
            Scanner input = new Scanner(System.in);
            String nomeCliente = input.nextLine().trim();  // Remove espaços extras
            String mes = input.nextLine().trim();  // Remove espaços extras
            input.close();

            // O arquivo de compras
            File arquivo = new File("teste.txt");
            Scanner scanner;

            double totalGasto = 0.0;

            try {
                // Lê o arquivo linha por linha
                scanner = new Scanner(arquivo);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();

                    // Divide a linha em partes (data, valor e nome)
                    String[] partes = linha.split(" ");
                    String data = partes[0];  // A data (dd/mm/yyyy)
                    String valorCompra = partes[1];  // O valor da compra
                    String nomeCompra = linha.substring(data.length() + valorCompra.length() + 2).trim();  // Nome do cliente

                    // Verifica se o nome e o mês são correspondentes (ignora maiúsculas/minúsculas)
                    if (nomeCompra.equalsIgnoreCase(nomeCliente) && data.contains("/" + mes + "/")) {
                        // Adiciona o valor da compra ao total
                        totalGasto += Double.parseDouble(valorCompra);
                    }
                }

                scanner.close();

                // Exibe o total gasto pelo cliente no mês
                System.out.printf("Total gasto por %s no mês %s: %.2f\n", nomeCliente, mes, totalGasto);

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo cliente.txt não encontrado.");
            }

        }
    }


