package atividades.arvore;
import java.io.*;
import java.util.Scanner;
public class ConsultaMatriculas {



        // Classe para representar um nó na árvore de pesquisa binária
        static class Nodo {
            int matricula;
            String nome;
            Nodo esquerda, direita;

            public Nodo(int matricula, String nome) {
                this.matricula = matricula;
                this.nome = nome;
                this.esquerda = this.direita = null;
            }
        }

        // Classe para representar a árvore binária
        static class ArvoreBinaria {
            private Nodo raiz;

            public ArvoreBinaria() {
                raiz = null;
            }

            // Método para inserir um nó na árvore
            public void inserir(int matricula, String nome) {
                raiz = inserirRecursivo(raiz, matricula, nome);
            }

            // Função recursiva para inserir um nó
            private Nodo inserirRecursivo(Nodo raiz, int matricula, String nome) {
                if (raiz == null) {
                    raiz = new Nodo(matricula, nome);
                    return raiz;
                }

                if (matricula < raiz.matricula) {
                    raiz.esquerda = inserirRecursivo(raiz.esquerda, matricula, nome);
                } else if (matricula > raiz.matricula) {
                    raiz.direita = inserirRecursivo(raiz.direita, matricula, nome);
                }

                return raiz;
            }

            // Método para buscar uma matrícula na árvore
            public String buscar(int matricula) {
                return buscarRecursivo(raiz, matricula);
            }

            // Função recursiva para buscar um nó
            private String buscarRecursivo(Nodo raiz, int matricula) {
                if (raiz == null) {
                    return null; // Matrícula não encontrada
                }

                if (matricula == raiz.matricula) {
                    return raiz.nome;
                }

                if (matricula < raiz.matricula) {
                    return buscarRecursivo(raiz.esquerda, matricula);
                }

                return buscarRecursivo(raiz.direita, matricula);
            }
        }

        public static void main(String[] args) {
            // Verifica se o arquivo foi fornecido
            if (args.length != 1) {
                System.out.println("Uso: java MatriculaSistema <caminho_do_arquivo>");
                return;
            }

            String arquivo = args[0];
            ArvoreBinaria arvore = new ArvoreBinaria();

            // Lê o arquivo e insere os dados na árvore
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    // Lê cada linha, separando número e nome
                    String[] partes = linha.split(",");
                    int matricula = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();

                    // Insere na árvore
                    arvore.inserir(matricula, nome);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                return;
            }

            // Interage com o usuário para procurar matrículas
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Digite matricula> ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    // Se o usuário apertar ENTER sem digitar nada, encerra o programa
                    System.out.println("Programa encerrado.");
                    break;
                }

                try {
                    int matriculaDigitada = Integer.parseInt(input);
                    String nome = arvore.buscar(matriculaDigitada);

                    if (nome != null) {
                        // Matrícula encontrada
                        System.out.println("Estudante: " + nome);
                    } else {
                        // Matrícula não encontrada
                        System.out.println("Matricula " + matriculaDigitada + " desconhecida");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite um número de matrícula válido.");
                }
            }

            scanner.close();
        }
    }

